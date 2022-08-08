package Dominio;

import java.util.ArrayList;

public class PremiumAdapter implements Categoria {

    private Premium p = new Premium();


    public void consultarVueloExistente(Usuario user) throws Exception {
        if (p.verificarPagoAlDia(user)) {
            p.consultarVuelos(user);
        }
        else{
            user.setVuelosFiltrados(new ArrayList<>());
            System.out.println("No se ha efectuado el pago para acceder a esta funcionalidad");
        }
    }
}
