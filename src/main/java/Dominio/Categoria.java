package Dominio;

import Database.UsuarioDb;

import java.util.List;
import java.util.Scanner;

public abstract class Categoria {

    Integer cantMax;
    String nombre;
    Integer id;

    public void consultarVueloExistente(Usuario user) throws Exception{

        List<Vuelo> vuelos;
        if (cantMax == 1) {
            vuelos = user.getBusqueda().buscarVuelos();

            if (vuelos.size() == 0) {
                UserService.mostrarMensajeDeError("La solicitud ingresada no es compatible con los vuelos de este sistema.");
            }
            else{
                UserService.mostrarListadoVuelos(vuelos);
            }
            cantMax--;
            UsuarioDb.actualizarCategoria(user.getCategoria());

        } else {
            UserService.mostrarMensajeDeError("\nSe supero la cantidad maxima de consultas posibles! Hagase premium para mas consultas\n");
            Scanner sc = new Scanner(System.in);
            String opcion;
            UserService.mostrarMensajeConsulta("Desea cambiarse a premium?: (S/N)");
            opcion = sc.nextLine();
            switch (opcion) {
                case "S":
                    cambiarAPremium(user);
                    break;
                case "N":
                    break;
                default:
                    UserService.mostrarMensajeDeError("Opcion invalida");
                    break;
            }
            System.out.println();
        }
    }
    public static void cambiarAPremium (Usuario user) throws Exception{
        Integer id_viejo = user.getCategoria().getId();
        user.setCategoria(new PremiumAdapter());
        user.getCategoria().setId(id_viejo);
        UsuarioDb.actualizarCategoria(user.getCategoria());
    }

    public String getNombre() {
        return nombre;
    }

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public Integer getCantMax(){return cantMax;};

    public void setNombre(String n){this.nombre = n;}

    public void setCantMax(Integer cant){this.cantMax = cant;}

}
