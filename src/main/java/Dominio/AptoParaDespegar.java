package Dominio;

public class AptoParaDespegar extends Estado{

    public void cargarNafta(){
        this.vuelo.cargarTanque(0);
        this.vuelo.setComida("Arroz con pollo");
       // UserService.mostrarMensaje(this.chequearEstado());
    }
    public String chequearEstado(){
        return "Ya se esta listo para despegar";
    }
}
