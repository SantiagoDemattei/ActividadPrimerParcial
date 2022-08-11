package Api;

import Dominio.UserService;
import Dominio.Vuelo;
import org.apache.cxf.jaxrs.client.WebClient;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.ws.rs.core.Response;
import java.util.*;

public class ApiCallVuelos {
    String TOKEN = "c2553274269dc42f5c72309039eeb5b3";
    String PARAMETRO = "flights";

    public List<Vuelo> consultarVuelos() throws Exception {
        List<Vuelo> vuelosTotales = new ArrayList<>();

        for(int i = 0; i < 8; i++) {

            Random r = new Random();
            int low = 10;
            int high = 100;
            int result = r.nextInt(high-low) + low;

            Vuelo[] vuelos;
            String incremento = String.valueOf(result);
            Integer inc = incremento.length();

            WebClient client = WebClient.create("http://api.aviationstack.com/v1/" + PARAMETRO + "?offset=" + result + "&access_key=" + TOKEN);
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            Response response = client
                    .header("Content-Type", "application/json")
                    .get();

            int Status = response.getStatus();
            //System.out.println("Status: " + Status);
            String responseBody = response.readEntity(String.class);

            if (Status == 200) {
                responseBody = responseBody.substring(72 + inc);
                //System.out.println("response = " + responseBody);

                vuelos = mapper.readValue(responseBody, Vuelo[].class);
                for(int j = 0 ; j < vuelos.length; j++){
                    if(!vueloYaExiste(vuelosTotales, vuelos[j])){
                        vuelosTotales.add(vuelos[j]);
                    }
                }
            } else {
                System.out.println("Error response = " + responseBody);
                throw new Exception("Error en la llamada a la API");
            }
        }
        UserService.mostrarVuelosFiltrados(vuelosTotales);
        return vuelosTotales;
    }

    Boolean vueloYaExiste(List<Vuelo> vuelos, Vuelo vuelo) {
        for (int i = 0; i < vuelos.size(); i++) {
            if (vuelo.getFlight().getFlight_icao().equals(vuelos.get(i).getFlight().getFlight_icao())) {
                return true;
            }
        }
        return false;
    }
}


