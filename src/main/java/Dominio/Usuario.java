package Dominio;
import Consulta.Busqueda;
import Consulta.Consultar;

import java.util.*;

public class Usuario {
    private Number id;
    private String nombre;
    private String apellido;
    private String mail;
    private String paisOrigen;
    private List<Vuelo> vuelosFiltrados;
    private Busqueda busqueda;
    private Vuelo prototipo;
    private String password;


    //SETTERS
    public void setNombre(String nombre){this.nombre = nombre;}
    public void setApellido(String a){this.apellido = a;}
    public void setMail(String mail){this.mail = mail;}
    public void setPaisOrigen(String pais){this.paisOrigen = pais;}
    public void setPassword(String password){this.password = password;}
    public void setId(Number id){this.id = id;}
    public void setBusqueda(Consultar estrategy, String des, String date, String aero){
        Busqueda b = new Busqueda(estrategy, des, date, aero);
        this.busqueda = b;
    }


    //GETTERS
    public List<Vuelo> getVuelosFiltrados(){
        return this.vuelosFiltrados;
    }

    public String getNombre(){ return this.nombre; }

    public String getApellido(){ return this.apellido; }

    public String getMail(){ return this.mail; }

    public String getPaisOrigen(){ return this.paisOrigen; }

    public String getPassword(){ return this.password; }

    public Number getId(){ return this.id; }

    //CONSTRUCTORES

    public Usuario(String n, String a, String m, String pass, String p){
        this.nombre = n;
        this.apellido = a;
        this.mail = m;
        this.paisOrigen = p;
        this.password = pass;
    }

    public Usuario(String m, String p){
        this.mail = m;
        this.password = p;
    }

    //METODOS
    public void consultarVueloExistente() throws Exception {
        this.vuelosFiltrados = busqueda.buscarVuelos();
    }

    public void cargarVueloNuevo(Vuelo prototipo, String numVuelo, String puertaEmbarque){
        Vuelo vueloClonado = prototipo.clonar();
        vueloClonado.getFlight().setFlight_number(numVuelo);
        vueloClonado.getDeparture().setDeparture_gate(puertaEmbarque);
    }

}
