package Dominio;
import Consulta.Busqueda;
import Consulta.Consultar;

import java.util.*;

public class Usuario {
    private String nombre;
    private String mail;
    private String paisOrigen;
    private List<Vuelo> vuelosFiltrados;
    private Busqueda busqueda;
    private Vuelo prototipo;

    public void ConsultarVueloExistente() throws Exception {
        this.vuelosFiltrados = busqueda.buscarVuelos();
    }
    public void setBusqueda(Consultar estrategy, String des, String date, String aero){
        Busqueda b = new Busqueda(estrategy, des, date, aero);
        this.busqueda = b;
    }

    public List<Vuelo> getVuelosFiltrados(){
        return this.vuelosFiltrados;
    }

    public Usuario(String n, String m, String p, List<Vuelo> v, Busqueda b){
        this.nombre = n;
        this.mail = m;
        this.paisOrigen = p;
        this.vuelosFiltrados = v;
        this.busqueda = b;
    }

    public void cargarVueloNuevo(){
        System.out.println(vuelosFiltrados.size());
        Vuelo prototipo = vuelosFiltrados.get(0);
        Vuelo vueloClonado = (Vuelo) prototipo.clonar();

        if(vueloClonado != null){
            System.out.println(vueloClonado);
            System.out.println(vueloClonado.getAirline().get("name").asText());
        }
    }

}
