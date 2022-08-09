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

    public static void controlarEstadoVuelos() throws Exception{
        if(vuelosNuevos.size() != 0) {
            for (int i = 0; i < vuelosNuevos.size(); i++) {
                Vuelo vuelo = vuelosNuevos.get(i);
                String ciudadDestino = vuelo.getCiudadDestino();
                ApiCallClima api = new ApiCallClima();
                api.setParametro(ciudadDestino);
                Float temp = api.consultarClima();
                if (!(temp > 0 && temp < 30)) {
                    vuelo.setFlight_status("Cancelado");
                    System.out.println("Se ha cambiado el estado del vuelo: " + vuelo.getFlight().getFlight_number() + " a CANCELADO porque la temperatura de la ciudad destino es de " + temp + " grados");
                }
                else{
                    System.out.println("El estado del vuelo esta OK. La temperatura de la ciudad destino es de " + temp + " grados Celsius y se encuentra dentro del rango exigido (0 a 30 grados Celsius)");
                }
                Thread.sleep(500);
            }
        }
        else {
            System.out.println("No hay vuelos para controlar");
        }
    }

}