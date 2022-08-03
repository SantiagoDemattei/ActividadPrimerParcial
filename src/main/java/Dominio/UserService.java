package Dominio;

import Consulta.*;
import java.sql.SQLException;
import java.util.Scanner;

import Consulta.ConsultarPorAeropuertoDestino;
import Database.UsuarioDb;

public class UserService {

    public static Usuario registrarUser() {
        Scanner console = new Scanner(System.in);

        System.out.println("\nIngrese email: ");
        String mail = console.nextLine();

        System.out.println("\nIngrese contrasena: ");
        String pass = console.nextLine();

        Usuario user = new Usuario(mail, pass);

        System.out.println("\nIngrese nombre: ");
        user.setNombre(console.nextLine());

        System.out.println("\nIngrese apellido: ");
        user.setApellido(console.nextLine());

        System.out.println("\nIngrese pais de origen (respetando tildes y caracteres especiales como la 'ñ'): ");
        user.setPaisOrigen(console.nextLine());

        return user;
    }

    public static void registrarUsuario(Usuario user) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        UsuarioDb.registrarUsuario(user);
    }

    public static Usuario buscarLoginEnDb(String mail, String pass) throws SQLException {
        Usuario user = UsuarioDb.buscarEnDb(mail);
        if(user == null){
            System.out.println("No se ha encontrado el email ingresado");
        }else{
            if(user.getPassword().equals(pass)){
                System.out.println("Bienvenido " + user.getNombre());
            }else{
                System.out.println("Contraseña incorrecta");
            }
        }
        return user;
    }

    public static void menuConsultarVuelo(Usuario user) throws Exception {
        String option = "K";
        Scanner sc = new Scanner(System.in);
        Scanner sc2 = new Scanner(System.in);
        while (option != "quit") {
            System.out.println("Ingrese la forma de busqueda: A: aerolinea, D: destino, F: fecha, S: salir");
            option = sc.nextLine();
            switch (option) {
                case "S":
                    System.out.println("Fin busqueda");
                    option = "quit";
                    break;
                case "A":
                    ConsultarPorAerolinea consultaA = new ConsultarPorAerolinea();
                    System.out.println("Ingrese la aerolinea: ");
                    String aerolinea = sc2.nextLine();
                    user.setBusqueda(consultaA, null, null, aerolinea);
                    System.out.println("Buscando los vuelos que pertenecen a la aerolinea: " + aerolinea);
                    user.consultarVueloExistente();
                    if(user.getVuelosFiltrados().size() == 0){
                        System.out.println("La solicitud ingresada no es compatible con los vuelos de este sistema. Por favor intentelo de nuevo!");
                        break;
                    }
                    mostrarVuelosFiltrados(user);
                    break;
                case "D":
                    ConsultarPorAeropuertoDestino consultaD = new ConsultarPorAeropuertoDestino();
                    System.out.println("Ingrese el aeropuerto de destino: ");
                    String destino = sc2.nextLine();
                    user.setBusqueda(consultaD, destino, null, null);
                    System.out.println("Buscando los vuelos con destino al aeropuerto: " + destino);
                    user.consultarVueloExistente();
                    if(user.getVuelosFiltrados().size() == 0){
                        System.out.println("La solicitud ingresada no es compatible con los vuelos de este sistema. Por favor intentelo de nuevo!");
                        break;
                    }
                    mostrarVuelosFiltrados(user);
                    break;
                case "F":
                    ConsultarPorFecha consultaF = new ConsultarPorFecha();
                    System.out.println("Ingrese la fecha en el siguiente formato: YYYY-MM-DDThh:mm:ss+hh:ss (Ejemplo: 2022-08-01T14:50:00+00:00)");
                    String fecha = sc2.nextLine();
                    user.setBusqueda(consultaF, null, fecha, null);
                    System.out.println("Buscando los vuelos con fecha: " + fecha);
                    user.consultarVueloExistente();
                    if(user.getVuelosFiltrados().size() == 0){
                        System.out.println("La solicitud ingresada no es compatible con los vuelos de este sistema. Por favor intentelo de nuevo!");
                        break;
                    }
                    mostrarVuelosFiltrados(user);
                    break;

            }
        }
    }

    public static void mostrarVuelosFiltrados(Usuario user) throws Exception{
        for(int i=0; i < user.getVuelosFiltrados().size(); i++){
            Vuelo vuelo = user.getVuelosFiltrados().get(i);
            System.out.println("                                VUELO: " + i);
            System.out.println();
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

    public static void menuCargarVuelo(Usuario user) throws Exception {
            Scanner sc4 = new Scanner(System.in);
            System.out.println("Ingrese el aeropuerto de destino del vuelo que quiere cargar: ");
            String destino = sc4.nextLine();
            ConsultarPorAeropuertoDestino consultaD = new ConsultarPorAeropuertoDestino();
            user.setBusqueda(consultaD, destino, null, null);
            System.out.println("Aguarde un momento por favor...");
            user.consultarVueloExistente();
            if(user.getVuelosFiltrados().size() == 0) {
                System.out.println("Nuestro sistema no ha podido encontrar vuelos existentes para el destino ingresado");
                System.out.println("A continuacion, ingrese los datos del nuevo vuelo");
                user.cargarDatosNuevoVuelo(destino);
            }
            else{
                System.out.println("Los vuelos que usted puede utilizar como plantilla son los siguientes: ");
                Thread.sleep(1500);
                mostrarVuelosFiltrados(user);
                Scanner sc5 = new Scanner(System.in);
                System.out.println("Seleccione el ID del vuelo que desea usar como plantilla: ");
                Integer indice = sc5.nextInt();
                sc5.nextLine();
                Vuelo vuelo = user.getVuelosFiltrados().get(indice);
                System.out.println("Seleccione el numero del vuelo nuevo: ");
                String numero = sc5.nextLine();
                System.out.println("Seleccione la puerta de embarque del vuelo nuevo: ");
                String puerta = sc5.nextLine();
                user.setPrototipo(vuelo);
                user.cargarVueloNuevo(numero, puerta);
            }

        }
}

