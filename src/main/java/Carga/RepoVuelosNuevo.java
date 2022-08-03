package Carga;
import Dominio.*;
import java.util.*;

public class RepoVuelosNuevo {
    List<Vuelo> vuelosNuevos;
    private static RepoVuelosNuevo instance;

    public static RepoVuelosNuevo getInstance() {
        if (instance != null) {
            return instance;
        } else {
            instance = new RepoVuelosNuevo(); // instanciar el Singleton si no hay todavía
            return instance;
        }
    }

    public void cargarVuelo(Vuelo v) throws Exception{
        v
        //vuelosNuevos.add(v);
    }
}
