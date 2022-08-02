package Dominio;


import Database.UsuarioDb;
import sun.text.normalizer.UBiDiProps;
import java.sql.*;
import java.util.Scanner;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        Integer option = -1;
        Scanner sc = new Scanner(System.in);

        while (option != 0) {

            System.out.println("Ingresa un numero segun la operacion a realizar \n " +
                    "0. Salir\n " +
                    "1. Registrarse \n " +
                    "2. Iniciar sesion \n ");

            option = sc.nextInt();

            switch (option) {
                case 0:
                    System.out.println("Saliendo...");
             break;
                case 1:
                    System.out.println("Registrando...");
                    Usuario user = UserService.registrarUser();
                    UserService.registrarUsuario(user);
                    break;

                case 2:
                    Scanner sc3 = new Scanner(System.in);
                    Integer option3 = -1;
                    System.out.println("Iniciando sesion...");
                    System.out.println("\nIngrese email: ");
                    String mail = sc3.nextLine();

                    System.out.println("\nIngrese contrasena: ");
                    String pass = sc3.nextLine();

                    Usuario loggedUser = UserService.buscarLoginEnDb(mail, pass);
                    if(loggedUser == null)
                        option3 = 0;
                    while (option3 != 0) {
                        System.out.println("Ingresa un numero segun la operacion a realizar \n " +
                                "0. Salir\n " +
                                "1. Consultar vuelos \n " +
                                "2. Cargar nuevo vuelo \n ");
                        option3 = sc.nextInt();
                        switch (option3) {
                            case 0:
                                System.out.println("Cerrando Sesion...");
                                option3 = 0;
                                break;
                            case 1:
                                System.out.println("Consultando vuelos...");
                                UserService.menuConsultarVuelo(loggedUser);
                                break;
                            case 2:
                                System.out.println("Cargando nuevo vuelo...");
                                UserService.menuCargarVuelo(loggedUser);
                                break;
                            default:
                                System.out.println("Opcion invalida");
                                break;
                        }
                    }
                    break;
                default:
                    System.out.println("Opcion invalida");
                    break;
            }
        }
    }
}
