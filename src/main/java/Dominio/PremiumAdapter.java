package Dominio;

import java.util.ArrayList;

public class PremiumAdapter implements Categoria {

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
}
