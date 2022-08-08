package Dominio;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Intermedio implements Categoria {

    Integer cantMax = 1;

    public void consultarVueloExistente(Usuario user) throws Exception {
        List<Vuelo> vuelo;
        if (cantMax == 1) {
            vuelo = user.getBusqueda().buscarVuelos();
            user.setVuelosFiltrados(vuelo);
            if (user.getVuelosFiltrados().size() == 0) {
                System.out.println("La solicitud ingresada no es compatible con los vuelos de este sistema ");
            }
            cantMax--;
        } else {
            user.setVuelosFiltrados(new ArrayList<>());
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
    public static void cambiarAPremium (Usuario user){
        user.setCategoria(new PremiumAdapter());
    }
}


