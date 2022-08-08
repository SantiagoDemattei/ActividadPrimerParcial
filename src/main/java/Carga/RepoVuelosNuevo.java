package Carga;
import Dominio.*;
import java.util.*;

public class RepoVuelosNuevo {
    List<Vuelo> vuelosNuevos = new ArrayList<>();
    private static RepoVuelosNuevo instance;

    private RepoVuelosNuevo() {
    }

    public static RepoVuelosNuevo getInstance() {
        if (instance != null) {
            return instance;
        } else {
            instance = new RepoVuelosNuevo(); // instanciar el Singleton si no hay todavía
            return instance;
        }
    }

    public void cargarVuelo(Vuelo v) throws Exception {
        vuelosNuevos.add(v);
        UserService.mostrarVuelosFiltrados(vuelosNuevos);
    }

}