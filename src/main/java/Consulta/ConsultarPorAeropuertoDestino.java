package Consulta;

import Dominio.Vuelo;

import java.util.ArrayList;
import java.util.List;

public class ConsultarPorAeropuertoDestino extends Consultar{

    public List<Vuelo> filtrar(Busqueda b) throws Exception {
        List<Vuelo> vuelos;
        List<Vuelo> vuelosFiltrados;
        vuelos = super.filtrar(b);
        vuelosFiltrados = filtrarPorAeropuertoDestino(vuelos, b.getAeropuertoDestino());

        return vuelosFiltrados;
    }

    public List<Vuelo> filtrarPorAeropuertoDestino(List<Vuelo> vuelos, String a){
        List<Vuelo> vuelosFiltrados = new ArrayList<>();
        for(int i = 0; i < vuelos.size(); i++){
            Vuelo v = vuelos.get(i);
            if(v.getArrival().getArrival_airport().equals(a)){
                vuelosFiltrados.add(v);
            }
        }
        return vuelosFiltrados;
    }
}
