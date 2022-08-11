package Dominio;

import java.util.ArrayList;

public class PremiumAdapter extends Categoria {

    private Premium catePremium = new Premium();
    Integer cantMax = null;
    String nombre = "PremiumAdapter";
    Integer id;

    public void consultarVueloExistente(Usuario user) throws Exception {
        if (catePremium.verificarPagoAlDia(user)) {
            catePremium.consultarVuelos(user);
        }
        else{
            //user.setVuelosFiltrados(new ArrayList<>());
            System.out.println("No se ha efectuado el pago para acceder a esta funcionalidad");
        }
    }

    public String getNombre(){
        return nombre;
    }

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public Integer getCantMax(){
        return cantMax;
    }

    public void setNombre(String n){
        this.nombre = n;
    }

    public void setCantMax(Integer cant){
        this.cantMax = cant;
    }
}
