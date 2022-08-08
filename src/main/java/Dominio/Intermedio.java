package Dominio;

import Database.UsuarioDb;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Intermedio implements Categoria {

    Integer cantMax = 1;

    public void consultarVueloExistente(Usuario user) throws Exception {
        List<Vuelo> vuelos;
        if (cantMax == 1) {
            vuelos = user.getBusqueda().buscarVuelos();
            //user.setVuelosFiltrados(vuelo);
            if (vuelos.size() == 0) {
                System.out.println("La solicitud ingresada no es compatible con los vuelos de este sistema ");
            }
            else{
                UserService.mostrarVuelosFiltrados(vuelos);
            }
            cantMax--;
        } else {
            //user.setVuelosFiltrados(new ArrayList<>());
            System.out.println("Se supero la cantidad maxima de consultas posibles! Hagase premium para mas consultas");
            Scanner sc = new Scanner(System.in);
            String opcion;
            System.out.println("Desea cambiarse a premium? (S/N)");
            opcion = sc.nextLine();
            switch (opcion) {
                case "S":
                    cambiarAPremium(user);
                    break;
                case "N":
                    break;
                default:
                    System.out.println("Opcion  invalida");
                    break;
            }
        }
    }
    public static void cambiarAPremium (Usuario user) throws Exception{
        Integer id_viejo = user.getCategoria().getId();
        user.setCategoria(new PremiumAdapter());
        user.getCategoria().setId(id_viejo);
        UsuarioDb.actualizarCategoria(user.getCategoria());
    }
}


