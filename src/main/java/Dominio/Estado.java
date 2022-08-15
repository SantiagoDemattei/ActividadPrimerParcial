package Dominio;

import java.sql.SQLException;

public abstract class Estado {
    public Vuelo vuelo;

    public void cargarNafta() throws SQLException {}
    public void setVuelo(Vuelo v){
        vuelo = v;
    }
}
