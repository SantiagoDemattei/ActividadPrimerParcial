package Consulta;

import Dominio.Vuelo;

import java.util.*;

public class ConsultarPorFecha extends Consultar{
    public List<Vuelo> filtrar(Busqueda b) throws Exception {
        List<Vuelo> vuelos;
        List<Vuelo> vuelosFiltrados;
        vuelos = super.filtrar(b);
        vuelosFiltrados = filtrarPorFecha(vuelos, b.getFecha());

        return vuelosFiltrados;
    }

    public List<Vuelo> filtrarPorFecha(List<Vuelo> vuelos, String date){
        List<Vuelo> vuelosFiltrados = new ArrayList<>();
        for(int i = 0; i < vuelos.size(); i++){
            Vuelo v = vuelos.get(i);
            if(v.getArrival().get("scheduled").asText().equals(date)){
                vuelosFiltrados.add(v);
            }
        }
        return vuelosFiltrados;
    }

}
