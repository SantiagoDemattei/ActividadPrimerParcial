package Consulta;

import Api.ApiCall;
import Dominio.Vuelo;

import java.util.List;

public class Consultar {

    public List<Vuelo> filtrar(Busqueda b) throws Exception {
        List<Vuelo> vuelos;

        ApiCall api = new ApiCall();
        vuelos = api.consultarVuelos();

        return vuelos;
    }
}
