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
        Vuelo vuelo = prototipo.clonar();
        vuelo.getFlight().setFlight_number(numVuelo);
        vuelo.getDeparture().setDeparture_gate(puertaEmbarque);

        System.out.println("DATA: ");
        System.out.println(vuelo.getFlight_date() + " " + vuelo.getFlight_status());

        if(vuelo.getDeparture() != null) {
            System.out.println("DEPARTURE: ");
            System.out.println(vuelo.getDeparture().getDeparture_airport() + " " + vuelo.getDeparture().getDeparture_timezone() + " " + vuelo.getDeparture().getDeparture_iata() + " " + vuelo.getDeparture().getDeparture_iaco() + " " + vuelo.getDeparture().getDeparture_terminal() + " " + vuelo.getDeparture().getDeparture_gate() + " " + vuelo.getDeparture().getDeparture_delay() + " " + vuelo.getDeparture().getDeparture_scheduled() + " " + vuelo.getDeparture().getDeparture_estimated() + " " + vuelo.getDeparture().getDeparture_actual() + " " + vuelo.getDeparture().getDeparture_estimated_runway() + " " + vuelo.getDeparture().getDeparture_actual_runway());
        }

        if(vuelo.getArrival() != null){
            System.out.println("ARRIVAL: ");
            System.out.println(vuelo.getArrival().getArrival_airport() + " " + vuelo.getArrival().getArrival_timezone() + " " + vuelo.getArrival().getArrival_iata() + " " + vuelo.getArrival().getArrival_iaco() + " " + vuelo.getArrival().getArrival_terminal() + " " + vuelo.getArrival().getArrival_gate() + " " + vuelo.getArrival().getArrival_baggage() + " " + vuelo.getArrival().getArrival_delay() + " " + vuelo.getArrival().getArrival_scheduled() + " " + vuelo.getArrival().getArrival_estimated() + " " + vuelo.getArrival().getArrival_actual() + " " + vuelo.getArrival().getArrival_estimated_runway() + " " + vuelo.getArrival().getArrival_actual_runway());
        }

        if(vuelo.getAirline() != null) {
            System.out.println("AIRLINE: ");
            System.out.println(vuelo.getAirline().getAirline_name() + " " + vuelo.getAirline().getAirline_iata() + " " + vuelo.getAirline().getAirline_icao());
        }

        if(vuelo.getFlight() != null) {
            System.out.println("FLIGHT: ");
            System.out.println(vuelo.getFlight().getFlight_number() + " " + vuelo.getFlight().getFlight_iata() + " " + vuelo.getFlight().getFlight_icao() + " " + vuelo.getFlight().getFlight_codeshared());
        }

        if(vuelo.getAircraft() != null) {
            System.out.println("AIRCRAFT: ");
            System.out.println(vuelo.getAircraft().getAircraft_registration() + " " + vuelo.getAircraft().getAircraft_iata() + " " + vuelo.getAircraft().getAircraft_icao() + " " + vuelo.getAircraft().getAircraft_icao24());
        }

        if(vuelo.getLive() != null) {
            System.out.println("LIVE: ");
            System.out.println(vuelo.getLive().getLive_updated() + " " + vuelo.getLive().getLive_latitude() + " " + vuelo.getLive().getLive_longitude() + " " + vuelo.getLive().getLive_altitude() + " " + vuelo.getLive().getLive_direction() + " " + vuelo.getLive().getLive_speed_horizontal() + " " + vuelo.getLive().getLive_speed_vertical() + " " + vuelo.getLive().getLive_is_ground());
        }
    }

}
