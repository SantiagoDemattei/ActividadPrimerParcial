package Dominio;

public class AptoParaDespegar extends Estado{

    public String name = "AptoParaDespegar";

    public void cargarNafta(){
        this.vuelo.cargarTanque(0);
        UserService.mostrarMensajeConsulta(this.chequearEstado());
    }
    public String chequearEstado(){
        return "Ya se esta listo para despegar";
    }
}
