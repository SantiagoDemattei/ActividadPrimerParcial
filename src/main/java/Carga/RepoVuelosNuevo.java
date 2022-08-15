package Carga;
import Api.ApiCallClima;
import Database.VueloDb;
import Dominio.*;

import javax.jws.soap.SOAPBinding;
import java.sql.SQLException;
import java.util.*;

public class RepoVuelosNuevo {
    static List<Vuelo> vuelosNuevos = new ArrayList<>();

    private static RepoVuelosNuevo instance;

    private RepoVuelosNuevo() {
    }

    public static RepoVuelosNuevo getInstance() {
        if (instance != null) {
            return instance;
        } else {
            instance = new RepoVuelosNuevo(); // instanciar el Singleton si no hay todavía
            return instance;
        }
    }

    public void cargarRepo() throws SQLException {
        vuelosNuevos = VueloDb.obtenerVuelosDb();
    }

    public void cargarVuelo(Vuelo v) throws Exception {
        vuelosNuevos.add(v);
        VueloDb.insertarVuelo(v);
    }

    public List<Vuelo> getVuelosNuevos() {return vuelosNuevos;}

    public static void controlarTemperaturaParaDespegue() throws Exception{
        if(vuelosNuevos.size() != 0) {
            Iterator<Vuelo> itr = vuelosNuevos.iterator();
            while(itr.hasNext()){
                Vuelo vuelo = itr.next();
                System.out.println(vuelosNuevos.size());
                if(vuelo.getEstado().getClass().getSimpleName().equals("AptoParaDespegar")){
                    String ciudadOrigen = vuelo.getCiudadOrigen();
                    ApiCallClima api = new ApiCallClima();
                    api.setParametro(ciudadOrigen);
                    Float temp = api.consultarClima();
                    if (!(temp > 0 && temp < 30)) {
                        String mensaje = "El vuelo con destino al aeropuerto: " + vuelo.getArrival().getArrival_airport() + " queda suspendido por temperatura actual de: " + temp + " grados, fuera del rango permitido (0 a 30 grados Celsius) para el despegue\n";
                        UserService.mostrarMensajeDeError(mensaje);
                        vuelosNuevos.remove(vuelo);
                    }
                    else{
                        String mensaje = "El aeropuerto de origen del vuelo numero " + vuelo.getFlight().getFlight_number()  + " es: " + vuelo.getDeparture().getDeparture_airport() + " y la temperatura actual es de " + temp + " grados Celsius. DESPEGUE ACEPTADO (La temperatura se encuentra dentro del rango (0 a 30 grados Celsius)\n";
                        UserService.mostrarMensajeConsulta(mensaje);
                    }
                    Thread.sleep(500);
                }
                else{
                    String mensaje = "El vuelo numero " + vuelo.getFlight().getFlight_number() + " no está apto para despegar. No se puede controlar la temperatura\n";
                    UserService.mostrarMensajeDeError(mensaje);
                }
            }
        }
        else {
            UserService.mostrarMensajeDeError("No hay vuelos para controlar");
        }
    }
}