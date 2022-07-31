package Api;
import Consulta.ConsultarPorAerolinea;
import Consulta.ConsultarPorAeropuertoDestino;
import Dominio.*;
public class Application {

    public static void main(String[] args) throws Exception{
        ConsultarPorAerolinea consul = new ConsultarPorAerolinea();
        Usuario u = new Usuario("Daniela", "ingrattadaniela1c@gmail.com", "Argentina", null , null);
        u.setBusqueda(consul, "Miami International Airport", "2022-08-01T14:50:00+00:00", "American Airlines");
        u.ConsultarVueloExistente();
        for(int i=0; i < u.getVuelosFiltrados().size(); i++){
            Vuelo vuelo = u.getVuelosFiltrados().get(i);
            System.out.println("                                VUELO: " + i);
            System.out.println();
            System.out.println("DATA: ");
            System.out.println(vuelo.getFlight_date() + " " + vuelo.getFlight_status());

            System.out.println("DEPARTURE: ");
            if(!vuelo.getDeparture().isNull()) {
                System.out.println(vuelo.getDeparture().get("airport").asText() + " " + vuelo.getDeparture().get("timezone").asText() + " " + vuelo.getDeparture().get("iata").asText() + " " + vuelo.getDeparture().get("icao").asText() + " " + vuelo.getDeparture().get("terminal").asText() + " " + vuelo.getDeparture().get("gate").asText() + " " + vuelo.getDeparture().get("delay").asText() + " " + vuelo.getDeparture().get("scheduled").asText() + " " + vuelo.getDeparture().get("estimated").asText() + " " + vuelo.getDeparture().get("actual").asText() + " " + vuelo.getDeparture().get("estimated_runway").asText() + " " + vuelo.getDeparture().get("actual_runway").asText());
            }

            System.out.println("ARRIVAL: ");
            if(!vuelo.getArrival().isNull()){
                System.out.println(vuelo.getArrival().get("airport").asText() + " " + vuelo.getArrival().get("timezone").asText() + " " + vuelo.getArrival().get("iata").asText() + " " + vuelo.getArrival().get("icao").asText() + " " + vuelo.getArrival().get("terminal").asText() + " " + vuelo.getArrival().get("gate").asText() + " " + vuelo.getArrival().get("baggage").asText() + " " + vuelo.getArrival().get("delay").asText() + " " + vuelo.getArrival().get("scheduled").asText() + " " + vuelo.getArrival().get("estimated").asText() + " " + vuelo.getArrival().get("actual").asText() + " " + vuelo.getArrival().get("estimated_runway").asText() + " " + vuelo.getArrival().get("actual_runway").asText());
            }

            System.out.println("AIRLINE: ");
            if(!vuelo.getAirline().isNull()) {
                System.out.println(vuelo.getAirline().get("name").asText() + " " + vuelo.getAirline().get("iata").asText() + " " + vuelo.getAirline().get("icao").asText());
            }

            System.out.println("FLIGHT: ");
            if(!vuelo.getFlight().isNull()) {
                System.out.println(vuelo.getFlight().get("number").asText() + " " + vuelo.getFlight().get("iata").asText() + " " + vuelo.getFlight().get("icao").asText() + " " + vuelo.getFlight().get("codeshared").asText());
            }

            System.out.println("AIRCRAFT: ");
            if(!vuelo.getAircraft().isNull()) {
                System.out.println(vuelo.getAircraft().get("registration").asText() + " " + vuelo.getAircraft().get("iata").asText() + " " + vuelo.getAircraft().get("icao").asText() + " " + vuelo.getAircraft().get("icao24").asText());
            }

            System.out.println("LIVE: ");
            if(!vuelo.getLive().isNull()) {
                System.out.println(vuelo.getLive().get("updated").asText() + " " + vuelo.getLive().get("latitude").asText() + " " + vuelo.getLive().get("longitude").asText() + " " + vuelo.getLive().get("altitude").asText() + " " + vuelo.getLive().get("direction").asText() + " " + vuelo.getLive().get("speed_horizontal").asText() + " " + vuelo.getLive().get("speed_vertical").asText() + " " + vuelo.getLive().get("is_ground").asText());
            }
        }

        u.cargarVueloNuevo();

    }
}
