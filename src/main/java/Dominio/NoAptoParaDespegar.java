package Dominio;

public class NoAptoParaDespegar extends Estado {
    public void cargarNafta(){
        this.vuelo.cargarTanque(300);
        this.chequearEstado();

    }
    public void chequearEstado(){
        if(this.vuelo.getTanque() == 1000){ // la capacidad maxima de nafta de todos los aviones es 1000
            this.vuelo.setEstado(new AptoParaDespegar());
        }
    }
}
