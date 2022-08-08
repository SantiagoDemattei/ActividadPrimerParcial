package Dominio;
import Dominio.*;

import java.util.ArrayList;
import java.util.List;

public class Premium {

    public void consultarVuelos (Usuario user) throws Exception {
        List<Vuelo> vuelo;
        vuelo = user.getBusqueda().buscarVuelos();
        user.setVuelosFiltrados(vuelo);
        if(user.getVuelosFiltrados().size() == 0){
            System.out.println("La solicitud ingresada no es compatible con los vuelos de este sistema. Por favor intentelo de nuevo!");
        }
    }

    public Boolean verificarPagoAlDia(Usuario user) throws Exception {
        if(user.getPagaMembresia()){
            return true;
        }
        else{
            user.setVuelosFiltrados(new ArrayList<>());
            return false;
        }

    }

}
