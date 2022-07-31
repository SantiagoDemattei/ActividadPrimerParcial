package Api;
import Consulta.ConsultarPorAerolinea;
import Consulta.ConsultarPorAeropuertoDestino;
import Dominio.*;
public class Application {

    public static void main(String[] args) throws Exception{
        ApiCall api = new ApiCall();
        api.consultarVuelos();

    }
}
