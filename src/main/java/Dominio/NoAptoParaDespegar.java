package Dominio;

import Database.VueloDb;

import java.sql.SQLException;

public class NoAptoParaDespegar extends Estado {

    public String name = "NoAptoParaDespegar";

    public void cargarNafta() throws SQLException {
        this.vuelo.cargarTanque(300);
        this.chequearEstado();
        UserService.mostrarMensajeAccion("Se han cargado 300 litros de combustible\n");
    }
    public void chequearEstado() throws SQLException {
        if(this.vuelo.getTanque() == 1000){ // la capacidad maxima de nafta de todos los aviones es 1000
            Estado e = new AptoParaDespegar();
            this.vuelo.setEstado(e);
            e.setVuelo(this.vuelo);
            this.vuelo.setComida("Arroz con pollo");
        }
        VueloDb.actualizarVuelo(this.vuelo);
    }

}
