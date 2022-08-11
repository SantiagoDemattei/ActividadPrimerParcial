package Carga;
import Api.ApiCallClima;
import Dominio.*;
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

    public void cargarVuelo(Vuelo v) throws Exception {
        vuelosNuevos.add(v);
        UserService.mostrarVuelosFiltrados(vuelosNuevos);
    }

    public List<Vuelo> getVuelosNuevos() {return vuelosNuevos;}

    public static void controlarTemperaturaParaDespegue() throws Exception{
        if(vuelosNuevos.size() != 0) {
            for (int i = 0; i < vuelosNuevos.size(); i++) {
                Vuelo vuelo = vuelosNuevos.get(i);

                if(vuelo.getEstado().getClass().getSimpleName().equals("AptoParaDespegar")){
                    String ciudadOrigen = vuelo.getCiudadOrigen();
                    ApiCallClima api = new ApiCallClima();
                    api.setParametro(ciudadOrigen);
                    Float temp = api.consultarClima();
                    if (!(temp > 0 && temp < 30)) {
                        System.out.println("El vuelo con destino al aeropuerto: " + vuelo.getArrival().getArrival_airport() + "queda suspendido por temperatura actual de: " + temp + "grados,  fuera del rango permitido (0 a 30 grados Celsius) para el despegue");
                        vuelosNuevos.remove(vuelo);
                    }
                    else{
                        System.out.println("La temperatura actual es de" + temp + " grados Celsius y se encuentra dentro del rango permitido (0 a 30 grados Celsius). DESPEGUE ACEPTADO");
                    }
                    Thread.sleep(500);
                }
                else{
                    System.out.println("El vuelo no está apto para despegar. No se puede controlar la temperatura");
                }
            }
        }
        else {
            System.out.println("No hay vuelos para controlar");
        }
    }

}