package Dominio;

public class Aeropuerto {
    private String id;
    private String airport_id;
    private String airport_name;
    private String iata_code;
    private String icao_code;
    private String latitude;
    private String longitude;
    private String geoname_id;
    private String timezone;
    private String gmt;
    private String phone_number;
    private String country_name;
    private String country_iso2;
    private String city_iata_code;

    public String getId(){return id;}
    public String getAirportId(){return airport_id;}
    public String getAirportName(){return airport_name;}
    public String getIataCode(){return iata_code;}
    public String getIcaoCode(){return icao_code;}
    public String getLatitude(){return latitude;}
    public String getLongitude(){return longitude;}
    public String getTimezone(){return timezone;}
    public String getGmt(){return gmt;}
    public String getCountryName(){return country_name;}
    public String getCityIataCode(){return city_iata_code;}
    public String getGeoNameId(){ return geoname_id;}
    public String getPhoneNumber(){ return phone_number;}
    public String getCountryIso2(){ return country_iso2;}


    public void  setAirport_name(String name){this.airport_name = name;}
    public void  setIata_code(String code){this.iata_code = code;}
    public void  setIcao_code(String code){this.icao_code = code;}
    public void  setLatitude(String lat){this.latitude = lat;}
    public void  setLongitude(String lon){this.longitude = lon;}
    public void  setTimezone(String timez){this.timezone = timez;}
    public void  setGmt(String gmt){this.gmt = gmt;}
    public void  setCountry_name(String country_name){this.country_name = country_name;}
    public void  setCity_iata_code(String city_iata_code){this.city_iata_code = city_iata_code;}
    public void  setGeoname_id(String nameId){this.geoname_id = nameId;}
    public void  setPhone_number(String num){this.phone_number = num;}
    public void  setCountry_iso2(String iso){this.country_iso2 = iso;}
    public void  setAirport_id(String airport_id){this.airport_id = airport_id;}
    public void  setId(String id){this.id = id;}
}


