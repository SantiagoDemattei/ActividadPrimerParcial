package Dominio;

import Carga.RepoVuelosNuevo;
import Consulta.*;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Consulta.ConsultarPorAeropuertoDestino;
import Database.UsuarioDb;

public class UserService {

    public static Usuario registrarUser() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        Scanner console = new Scanner(System.in);
        Integer option;

        System.out.println("\nIngrese email: ");
        String mail = console.nextLine();
        if(buscarMailEnDb(mail)){
            System.out.println("\nEl email ya existe, intente con otro");
            return registrarUser();
        }
        System.out.println("\nIngrese contrasena: ");
        String pass = console.nextLine();

        Usuario user = new Usuario(mail, pass);

        user.setPagaMembresia(false);

        System.out.println("\nIngrese nombre: ");
        user.setNombre(console.nextLine());

        System.out.println("\nIngrese apellido: ");
        user.setApellido(console.nextLine());

        System.out.println("\nIngrese pais de origen (respetando tildes y caracteres especiales como la 'ñ'): ");
        user.setPaisOrigen(console.nextLine());

        System.out.println("\n Ingrese categoria de usuario: 1-Estandar 2-Intermedio 3-Premium");
        option = console.nextInt();
        console.nextLine();
        instanciarCategoria(option, user);

        return user;
    }

    public static void instanciarCategoria(Integer c, Usuario user){
        Categoria categoria;
        while(true) {
            switch (c) {
                case 1:
                    categoria = new Estandar();
                    user.setCategoria(categoria);
                    return;
                case 2:
                    categoria = new Intermedio();
                    user.setCategoria(categoria);
                    return;
                case 3:
                    categoria = new PremiumAdapter();
                    user.setCategoria(categoria);
                    return;
                default:
                    System.out.println("Categoria no valida");

            }
        }
    }

    public static void registrarUsuario(Usuario user) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        UsuarioDb.registrarUsuario(user);
    }

    public static Boolean buscarMailEnDb(String mail) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        Usuario user = UsuarioDb.buscarEnDb(mail);
        return user != null;
    }
    public static Usuario buscarLoginEnDb(String mail, String pass) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        Usuario user = UsuarioDb.buscarEnDb(mail);
        if(user == null){
            System.out.println("No se ha encontrado el email ingresado");
        }else{
            if(user.getPassword().equals(pass)){
                System.out.println("Bienvenido " + user.getNombre());
            }else{
                System.out.println("Contraseña incorrecta");
                user = null;
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
                    user.getCategoria().consultarVueloExistente(user);

                    if(user.getCategoria().getClass().getSimpleName().equals("PremiumAdapter") && !user.getPagaMembresia()){
                        option = "quit";
                    }

                    if(!user.getCategoria().getClass().getSimpleName().equals("PremiumAdapter")){
                        option = "quit";
                    }
                    // if(user.getVuelosFiltrados().size() == 0){
                    //   break;
                    //}
                    //mostrarVuelosFiltrados(user);
                    //System.out.println();
                    break;
                case "D":
                    ConsultarPorAeropuertoDestino consultaD = new ConsultarPorAeropuertoDestino();
                    System.out.println("Ingrese el aeropuerto de destino: ");
                    String destino = sc2.nextLine();
                    user.setBusqueda(consultaD, destino, null, null);
                    System.out.println("Buscando los vuelos con destino al aeropuerto: " + destino);
                    user.getCategoria().consultarVueloExistente(user);

                    if(user.getCategoria().getClass().getSimpleName().equals("PremiumAdapter") && !user.getPagaMembresia()){
                        option = "quit";
                    }

                    if(!user.getCategoria().getClass().getSimpleName().equals("PremiumAdapter")){
                        option = "quit";
                    }
                  //  if(user.getVuelosFiltrados().size() == 0){
                    //    break;
                    //}
                    //mostrarVuelosFiltrados(user);
                    break;
                case "F":
                    ConsultarPorFecha consultaF = new ConsultarPorFecha();
                    System.out.println("Ingrese la fecha en el siguiente formato: YYYY-MM-DDThh:mm:ss+hh:ss (Ejemplo: 2022-08-01T14:50:00+00:00)");
                    String fecha = sc2.nextLine();
                    user.setBusqueda(consultaF, null, fecha, null);
                    System.out.println("Buscando los vuelos con fecha: " + fecha);
                    user.getCategoria().consultarVueloExistente(user);
                    if(user.getCategoria().getClass().getSimpleName().equals("PremiumAdapter") && !user.getPagaMembresia()){
                        option = "quit";
                    }

                    if(!user.getCategoria().getClass().getSimpleName().equals("PremiumAdapter")){
                        option = "quit";
                    }
                    //if(user.getVuelosFiltrados().size() == 0){
                      //  break;
                    //}
                    //mostrarVuelosFiltrados(user);
                    break;
                default:
                    System.out.println("Opcion invalida");
                    break;
            }
        }
    }

    public static void mostrarVuelosFiltrados(List<Vuelo> vuelos) {
        for(int i=0; i < vuelos.size(); i++){
            Vuelo vuelo = vuelos.get(i);
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
            System.out.println();
        }

    }

    public static void menuCargarVuelo(Usuario user) throws Exception {
            Scanner sc4 = new Scanner(System.in);
            System.out.println("Ingrese el aeropuerto de destino del vuelo que quiere cargar: ");
            String destino = sc4.nextLine();
            ConsultarPorAeropuertoDestino consultaD = new ConsultarPorAeropuertoDestino();
            user.setBusqueda(consultaD, destino, null, null);
            System.out.println("Aguarde un momento por favor...");
            //user.getCategoria().consultarVueloExistente(user);
            List<Vuelo> vuelos;
            vuelos = user.getBusqueda().buscarVuelos();

            if(vuelos.size() == 0) {
                System.out.println("Nuestro sistema no ha podido encontrar vuelos existentes para el destino ingresado");
                System.out.println("A continuacion, ingrese los datos del nuevo vuelo");
                user.cargarDatosNuevoVuelo(destino);
            }
            else{
                System.out.println("Los vuelos que usted puede utilizar como plantilla son los siguientes: ");
                Thread.sleep(1500);
                mostrarVuelosFiltrados(vuelos);
                Scanner sc5 = new Scanner(System.in);
                System.out.println("Seleccione el ID del vuelo que desea usar como plantilla: ");
                Integer indice = sc5.nextInt();
                sc5.nextLine();
                Vuelo vuelo = vuelos.get(indice);
                System.out.println("Seleccione el numero del vuelo nuevo: ");
                String numero = sc5.nextLine();
                System.out.println("Seleccione la puerta de embarque del vuelo nuevo: ");
                String puerta = sc5.nextLine();
                user.setPrototipo(vuelo);
                user.setearDatosVueloClonado(numero, puerta);
            }

        }

    public static Integer mostrarMenuPremium(Usuario user, Scanner sc) throws Exception {
        Integer option = -1;
        mostrarMenuIntermedio(user, sc);
        System.out.println(" 4. Pagar");
        option =  sc.nextInt();
        mostrarOpciones(user, option, true);
        return option;
    }

    public static void mostrarMenuEstandar(Usuario user, Scanner sc) throws Exception {
        System.out.println("Ingresa un numero segun la operacion a realizar \n " +
                "0. Salir\n " +
                "1. Consultar vuelos \n " +
                "2. Cargar nuevo vuelo ");
    }

    public static void mostrarOpciones(Usuario user, Integer option, Boolean esPremium) throws Exception {
        switch (option) {
            case 0:
                System.out.println("Cerrando Sesion...");
                break;
            case 1:
                System.out.println("Consultando vuelos...");
                menuConsultarVuelo(user);
                break;
            case 2:
                System.out.println("Cargando nuevo vuelo...");
                menuCargarVuelo(user);
                break;
            case 3:
                System.out.println("Controlando estado de vuelos cargados...");
                RepoVuelosNuevo.controlarEstadoVuelos();
                break;
            case 4:
                if (esPremium) {
                    user.pagar();
                }
                else {
                    System.out.println("Opcion invalida");
                }
                break;

            default:
                System.out.println("Opcion invalida");
                break;
        }
    }
}

