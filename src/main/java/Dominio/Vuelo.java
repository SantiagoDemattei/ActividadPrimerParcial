package Dominio;

import com.fasterxml.jackson.databind.JsonNode;

public class Vuelo {
    private String flight_date;
    private String flight_status;
    JsonNode departure;
    JsonNode arrival;
    JsonNode airline;
    JsonNode flight;
    JsonNode aircraft;
    JsonNode live;

    public Vuelo clonar(){
        Vuelo vuelo = null;
        try {
            vuelo = (Vuelo) clone();
        } catch (CloneNotSupportedException e){
            e.printStackTrace();
        }
        return vuelo;
    }

    public String getFlight_date(){return flight_date;}
    public String getFlight_status(){return flight_status;}
    public JsonNode getDeparture(){return departure;}
    public JsonNode getArrival(){return arrival;}
    public JsonNode getAirline(){return airline;}
    public JsonNode getFlight(){return flight;}
    public JsonNode getAircraft(){return aircraft;}
    public JsonNode getLive(){return live;}

    public void setFlight_date(String f){this.flight_date = f;}
    public void setFlight_status(String f){this.flight_status = f;}
    public void setDeparture(JsonNode n){this.departure = n;}
    public void setArrival(JsonNode n){this.arrival = n;}
    public void setAirline(JsonNode n){this.airline = n;}
    public void setFlight(JsonNode n){this.flight = n;}
    public void setAircraft(JsonNode n){this.aircraft = n;}
    public void setLive(JsonNode n){this.live = n;}



}
