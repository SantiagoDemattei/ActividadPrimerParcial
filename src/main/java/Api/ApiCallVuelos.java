package Api;

import Dominio.UserService;
import Dominio.Vuelo;
import org.apache.cxf.jaxrs.client.WebClient;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.ws.rs.core.Response;
import java.util.*;

public class ApiCallVuelos {
    String TOKEN = "edbf2c38809a6c2b46f696bf829475fb";
    String PARAMETRO = "flights";

    public List<Vuelo> consultarVuelos() throws Exception {
        List<Vuelo> vuelosTotales = new ArrayList<>();
        try {
            for (int i = 0; i < 8; i++) {

                Random r = new Random();
                int low = 10;
                int high = 100;
                int result = r.nextInt(high - low) + low;

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
                String responseBody = response.readEntity(String.class);

                if (Status == 200) {
                    responseBody = responseBody.substring(72 + inc);

                    vuelos = mapper.readValue(responseBody, Vuelo[].class);
                    for (int j = 0; j < vuelos.length; j++) {
                        if (!vueloYaExiste(vuelosTotales, vuelos[j])) {
                            vuelosTotales.add(vuelos[j]);
                        }
                    }
                } else {
                    UserService.mostrarMensajeDeError("Error response = " + responseBody + "\n");
                }
            }
            return vuelosTotales;
        }
        catch (Exception e){
            UserService.mostrarMensajeDeError("Error: " + e.getMessage() + "\n");
            UserService.mostrarMensajeDeError("API CAIDA, CONTACTAR CON SOPORTE");
            return vuelosTotales;
        }
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


