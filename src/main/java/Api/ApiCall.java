package Api;

import Dominio.Aeropuerto;
import org.apache.cxf.jaxrs.client.WebClient;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.ws.rs.core.Response;
import java.util.*;

public class ApiCall {

    String TOKEN = "";
    String PARAMETRO = "airports";

    private void setPARAMETRO(String param){
        this.PARAMETRO = param;
    }

    public void consultarAeropuertos() throws Exception {

        WebClient client = WebClient.create("http://api.aviationstack.com/v1/" + PARAMETRO + "?access_key=" + TOKEN);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        Response response = client
                .header("Content-Type", "application/json")
                .get();

        int Status = response.getStatus();
        System.out.println("Status: " + Status);
        String responseBody = response.readEntity(String.class);
        if (Status == 200)
        {
            responseBody = responseBody.substring(71);
            System.out.println("response = " + responseBody);
            Aeropuerto[] aeropuertos = mapper.readValue(responseBody, Aeropuerto[].class);

            for (int i=0; i < aeropuertos.length; i++) {
                Aeropuerto aeropuerto = aeropuertos[i];
                System.out.println(aeropuerto.getId() + " " + aeropuerto.getGmt() + " " + aeropuerto.getAirportId() + " " + aeropuerto.getIataCode() + " " + aeropuerto.getCityIataCode() + " " + aeropuerto.getIcaoCode() + " " + aeropuerto.getCountryIso2() + " " + aeropuerto.getGeoNameId() + " " + aeropuerto.getLatitude() + " " + aeropuerto.getLongitude() + " " + aeropuerto.getAirportName() + " " + aeropuerto.getCountryName() + " " + aeropuerto.getPhoneNumber() + " " + aeropuerto.getTimezone());
            }
        }
        else
        {
            System.out.println("Error response = " + responseBody);
            throw new Exception("Error en la llamada a la API");
        }

      }
}
