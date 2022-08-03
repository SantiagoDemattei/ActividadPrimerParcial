package Dominio;
import Carga.RepoVuelosNuevo;
import Carga.VueloInternacionalAdapter;
import Carga.VueloNacional;
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
    public void setNombre(String nombre) {this.nombre = nombre;}
    public void setApellido(String a) {this.apellido = a;}
    public void setMail(String mail) {this.mail = mail;}
    public void setPaisOrigen(String pais) {this.paisOrigen = pais;}
    public void setPassword(String password) {this.password = password;}
    public void setId(Number id) {this.id = id;}

    public void setBusqueda(Consultar estrategy, String des, String date, String aero) {
        Busqueda b = new Busqueda(estrategy, des, date, aero);
        this.busqueda = b;
    }


    //GETTERS
    public List<Vuelo> getVuelosFiltrados() {return this.vuelosFiltrados;}
    public String getNombre() {return this.nombre;}
    public String getApellido() {return this.apellido;}
    public String getMail() {return this.mail;}
    public String getPaisOrigen() {return this.paisOrigen;}
    public String getPassword() {return this.password;}
    public Number getId() {return this.id;}
    public Vuelo getPrototipo(){return this.prototipo;}


    //CONSTRUCTORES
    public Usuario(String n, String a, String m, String pass, String p) {
        this.nombre = n;
        this.apellido = a;
        this.mail = m;
        this.paisOrigen = p;
        this.password = pass;
    }

    public Usuario(String m, String p) {
        this.mail = m;
        this.password = p;
    }

    //METODOS
    public void consultarVueloExistente() throws Exception {
        this.vuelosFiltrados = busqueda.buscarVuelos();
    }

    public void cargarVueloNuevo(String numVuelo, String puertaEmbarque) throws Exception{

        if(prototipo.esNacional()) {
            VueloNacional vueloClonado = (VueloNacional) this.getPrototipo().clonar();
            setearDatosVueloClonado(vueloClonado, numVuelo, puertaEmbarque);
        }
        else{
            VueloInternacionalAdapter vueloClonado = (VueloInternacionalAdapter) this.getPrototipo().clonar();
            setearDatosVueloClonado(vueloClonado, numVuelo, puertaEmbarque);
        }

    }
    public void setearDatosVueloClonado(Vuelo vueloClonado, String numVuelo, String puertaEmbarque) throws Exception{
        RepoVuelosNuevo repo = new RepoVuelosNuevo();
        vueloClonado.getFlight().setFlight_number(numVuelo);
        vueloClonado.getDeparture().setDeparture_gate(puertaEmbarque);
        vueloClonado.cargarVuelo(vueloClonado);
    }

    public void cargarDatosNuevoVuelo(String aeropuertoDestino){
        Scanner sc4 = new Scanner(System.in);
        Vuelo vueloNuevo = new Vuelo();
        vueloNuevo.getArrival().setArrival_airport(aeropuertoDestino);
        System.out.println("Ingrese la fecha del vuelo: ");
        vueloNuevo.setFlight_date(sc4.nextLine());
        System.out.println("Ingrese el status del vuelo: ");
        vueloNuevo.setFlight_status(sc4.nextLine());
        this.cargarDatosDeparture(sc4, vueloNuevo);
        this.cargarDatosArrival(sc4, vueloNuevo);
        this.cargarDatosAirline(sc4, vueloNuevo);
        this.cargarDatosFlight(sc4, vueloNuevo);
        this.cargarDatosAircraft(sc4, vueloNuevo);
        //repo.cargarVuelo(vueloNuevo);
    }
    public void cargarDatosDeparture(Scanner sc4, Vuelo vueloNuevo){
        System.out.println("Ingrese aeropuerto de origen: ");
        vueloNuevo.getDeparture().setDeparture_airport(sc4.nextLine());
        System.out.println("Ingrese timezone de origen: ");
        vueloNuevo.getDeparture().setDeparture_timezone(sc4.nextLine());
        System.out.println("Ingrese codigo iata de origen: ");
        vueloNuevo.getDeparture().setDeparture_iata(sc4.nextLine());
        System.out.println("Ingrese codigo icao de origen: ");
        vueloNuevo.getDeparture().setDeparture_iaco(sc4.nextLine());
        System.out.println("Ingrese terminal de origen: ");
        vueloNuevo.getDeparture().setDeparture_terminal(sc4.nextLine());
        System.out.println("Ingrese gate de origen: ");
        vueloNuevo.getDeparture().setDeparture_gate(sc4.nextLine());
        System.out.println("Ingrese delay de origen: ");
        vueloNuevo.getDeparture().setDeparture_delay(sc4.nextLine());
        System.out.println("Ingrese tiempo scheduled de origen: ");
        vueloNuevo.getDeparture().setDeparture_scheduled(sc4.nextLine());
        System.out.println("Ingrese tiempo estimated de origen: ");
        vueloNuevo.getDeparture().setDeparture_estimated(sc4.nextLine());
        System.out.println("Ingrese tiempo actual de origen: ");
        vueloNuevo.getDeparture().setDeparture_actual(sc4.nextLine());
        System.out.println("Ingrese pista estimada de origen: ");
        vueloNuevo.getDeparture().setDeparture_estimated_runway(sc4.nextLine());
        System.out.println("Ingrese pista actual de origen: ");
        vueloNuevo.getDeparture().setDeparture_actual_runway(sc4.nextLine());
    }
    public void cargarDatosArrival(Scanner sc4, Vuelo vueloNuevo){
        System.out.println("Ingrese timezone destino: ");
        vueloNuevo.getArrival().setArrival_timezone(sc4.nextLine());
        System.out.println("Ingrese codigo iata de destino: ");
        vueloNuevo.getArrival().setArrival_iata(sc4.nextLine());
        System.out.println("Ingrese codigo icao de destino: ");
        vueloNuevo.getArrival().setArrival_iaco(sc4.nextLine());
        System.out.println("Ingrese terminal de destino: ");
        vueloNuevo.getArrival().setArrival_terminal(sc4.nextLine());
        System.out.println("Ingrese gate de destino: ");
        vueloNuevo.getDeparture().setDeparture_gate(sc4.nextLine());
        System.out.println("Ingrese baggage de destino: ");
        vueloNuevo.getArrival().setArrival_baggage(sc4.nextLine());
        System.out.println("Ingrese delay de destino: ");
        vueloNuevo.getArrival().setArrival_delay(sc4.nextLine());
        System.out.println("Ingrese tiempo scheduled de destino: ");
        vueloNuevo.getArrival().setArrival_scheduled(sc4.nextLine());
        System.out.println("Ingrese tiempo estimated de destino: ");
        vueloNuevo.getArrival().setArrival_estimated(sc4.nextLine());
        System.out.println("Ingrese tiempo actual de destino: ");
        vueloNuevo.getArrival().setArrival_actual(sc4.nextLine());
        System.out.println("Ingrese pista estimada de destino: ");
        vueloNuevo.getArrival().setArrival_estimated_runway(sc4.nextLine());
        System.out.println("Ingrese pista actual de destino: ");
        vueloNuevo.getArrival().setArrival_actual_runway(sc4.nextLine());
    }
    public void cargarDatosAirline(Scanner sc4, Vuelo vueloNuevo){
        System.out.println("Ingrese nombre de aerolinea: ");
        vueloNuevo.getAirline().setAirline_name(sc4.nextLine());
        System.out.println("Ingrese codigo iata de aerolinea: ");
        vueloNuevo.getAirline().setAirline_iata(sc4.nextLine());
        System.out.println("Ingrese codigo icao de aerolinea: ");
        vueloNuevo.getAirline().setAirline_icao(sc4.nextLine());
    }
    public void cargarDatosFlight(Scanner sc4, Vuelo vueloNuevo){
        System.out.println("Ingrese numero de vuelo: ");
        vueloNuevo.getFlight().setFlight_number(sc4.nextLine());
        System.out.println("Ingrese codigo iata de vuelo: ");
        vueloNuevo.getFlight().setFlight_iata(sc4.nextLine());
        System.out.println("Ingrese codigo icao de vuelo: ");
        vueloNuevo.getFlight().setFlight_icao(sc4.nextLine());
        System.out.println("Ingrese codigo de compartido: ");
        vueloNuevo.getFlight().setFlight_codeshared(sc4.nextLine());
    }


}

