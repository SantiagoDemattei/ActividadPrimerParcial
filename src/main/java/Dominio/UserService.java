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
            mostrarMensajeDeError("\nEl email ya existe, intente con otro");
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
        System.out.println();
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
                    mostrarMensajeDeError("Categoria no valida");
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
            mostrarMensajeDeError("\nNo se ha encontrado el email ingresado\n");
        }else{
            if(UsuarioDb.coincideContrasenia(pass, user.getPassword())){
                System.out.println();
                UserService.clearScreen();
                UserService.mostrarMensajeConsulta("Bienvenido/a " + user.getNombre());
            }else{
                UserService.mostrarMensajeDeError("\nContraseña incorrecta\n");
                System.out.println();
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
            Boolean bandera = true;
            System.out.println("Ingrese la forma de busqueda: A: aerolinea, D: destino, F: fecha, S: salir");
            option = sc.nextLine();
            switch (option) {
                case "S":
                    System.out.println("Fin busqueda");
                    option = "quit";
                    bandera = false;
                    break;
                case "A":
                    ConsultarPorAerolinea consultaA = new ConsultarPorAerolinea();
                    System.out.println("Ingrese la aerolinea: ");
                    String aerolinea = sc2.nextLine();
                    user.setBusqueda(consultaA, null, null, aerolinea);
                    System.out.println();
                    UserService.mostrarMensajeAccion("Buscando los vuelos que pertenecen a la aerolinea: " + aerolinea);
                    if(user.getCategoria().getClass().getSimpleName().equals("PremiumAdapter") && !user.getPagaMembresia()){
                        option = "quit";
                    }

                    if(!user.getCategoria().getClass().getSimpleName().equals("PremiumAdapter")){
                        option = "quit";
                    }
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
                    break;
                case "F":
                    ConsultarPorFecha consultaF = new ConsultarPorFecha();
                    // formato requerido por la api: YYYY-MM-DDThh:mm:ss+hh:ss (Ejemplo: 2022-08-01T14:50:00+00:00)
                    System.out.println("Ingrese la hora del vuelo en el siguiente formato: hora:minuto");
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
                    break;
                default:
                    System.out.println("Opcion invalida");
                    break;
            }
            if(bandera){
                user.getCategoria().consultarVueloExistente(user);
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

            System.out.println("\nESTADO: " + vuelo.getEstado().getClass().getSimpleName());

            if(vuelo.getComida() != null) {
                System.out.println("\nCOMIDA: " + vuelo.getComida());
            }
            else
            {
                System.out.println("\nCOMIDA: No dispone.");
            }

            System.out.println("\nTANQUE: " + vuelo.getTanque() + " litros. ");

            System.out.println("--------------------------------------------------------------------------------------------------------");
        }

    }

    public static void menuCargarVuelo(Usuario user) throws Exception {
            Scanner sc4 = new Scanner(System.in);
            System.out.println("Ingrese el aeropuerto de destino del vuelo que quiere cargar: ");
            String destino = sc4.nextLine();
            ConsultarPorAeropuertoDestino consultaD = new ConsultarPorAeropuertoDestino();
            user.setBusqueda(consultaD, destino, null, null);
            System.out.println("Aguarde un momento por favor...");
            user.getCategoria().consultarVueloExistente(user);
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
                System.out.println();
                user.setPrototipo(vuelo);
                user.setearDatosVueloClonado(numero, puerta);
            }

        }

    public static Integer mostrarMenuPremium(Usuario user, Scanner sc) throws Exception {
        Integer option = -1;
        mostrarMenuIntermedio(user, sc);
        System.out.println(" 6. Pagar");
        option =  sc.nextInt();
        mostrarOpciones(user, option, true);
        return option;
    }

    public static void mostrarMenuEstandar(Usuario user, Scanner sc) throws Exception {
        System.out.println("Ingresa un numero segun la operacion a realizar \n " +
                "0. Salir\n " +
                "1. Consultar vuelos \n " +
                "2. Cargar nuevo vuelo \n " +
                "3. Mostrar menus cargados en el sistema \n" +
                "4. Cargar tanque del avion de un vuelo cargado");
    }
    public static void mostrarMenuIntermedio(Usuario user, Scanner sc) throws Exception {
        System.out.println("Ingresa un numero segun la operacion a realizar \n " +
                "0. Salir\n " +
                "1. Consultar vuelos \n " +
                "2. Cargar nuevo vuelo \n" +
                "3. Mostrar menus cargados en el sistema \n" +
                "4. Cargar tanque del avion de un vuelo cargado\n" +
                "5. Controlar temperatura para el despegue de los vuelos cargados ");
    }

    public static void mostrarOpciones(Usuario user, Integer option, Boolean esPremium) throws Exception {
        switch (option) {
            case 0:
                mostrarMensajeAccion("Cerrando Sesion...");
                clearScreen();
                break;
            case 1:
                mostrarMensajeAccion("Consultando vuelos...");
                menuConsultarVuelo(user);
                break;
            case 2:
                mostrarMensajeAccion("Cargando nuevo vuelo...");
                menuCargarVuelo(user);
                break;
            case 3:
                System.out.println("Controlando temperatura para el despegue de vuelos cargados...");
                RepoVuelosNuevo.controlarTemperaturaParaDespegue();
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

