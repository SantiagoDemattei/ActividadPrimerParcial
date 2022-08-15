package Database;

import Dominio.*;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.*;

public class VueloDb {
    public static void insertarVuelo(Vuelo v) throws SQLException{
        Connection conn = Init.initDb();
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO vuelo (Flight_date, Flight_status, Departure, Arrival, Flight, Aircraft, Airline, Tanque, Estado, Comida) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
        stmt.setString(1, v.getFlight_date());
        stmt.setString(2, v.getFlight_status());
        stmt.setInt(3, insertarDeparture(conn, v.getDeparture()));
        stmt.setInt(4, insertarArrival(conn, v.getArrival()));
        stmt.setInt(5, insertarFlight(conn, v.getFlight()));
        stmt.setInt(6, insertarAircraft(conn, v.getAircraft()));
        stmt.setInt(7, insertarAirline(conn, v.getAirline()));
        stmt.setString(8, v.getTanque().toString());
        stmt.setString(9, v.getEstado().getClass().getSimpleName());
        stmt.setString(10, v.getComida());
        stmt.execute();
        int generatedKey = 0;
        ResultSet rs = stmt.getGeneratedKeys();
        if (rs.next()) {
            generatedKey = rs.getInt(1);
            v.setId(generatedKey);
        }

        conn.close();
    }

    public static Integer insertarAirline(Connection conn, Airline a) throws SQLException{
        if(a != null) {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO airline (Name, Iata, Icao) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, a.getAirline_name());
            stmt.setString(2, a.getAirline_iata());
            stmt.setString(3, a.getAirline_icao());
            stmt.execute();
            int generatedKey = 0;
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                generatedKey = rs.getInt(1);
                a.setAirline_id(generatedKey);
                return generatedKey;
            }
            return null;
        }
        else{
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO airline (Name, Iata, Icao) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, null);
            stmt.setString(2, null);
            stmt.setString(3, null);
            stmt.execute();
            int generatedKey = 0;
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                generatedKey = rs.getInt(1);
                return generatedKey;
            }
            return null;
        }
    }

    public static Integer insertarAircraft(Connection conn, Aircraft a) throws SQLException{
        if(a != null){
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO aircraft (Registration, Iata, Icao, Icao24) VALUES (?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
        stmt.setString(1, a.getAircraft_registration());
        stmt.setString(2, a.getAircraft_iata());
        stmt.setString(3, a.getAircraft_icao());
        stmt.setString(4, a.getAircraft_icao24());
        stmt.execute();
        int generatedKey = 0;
        ResultSet rs = stmt.getGeneratedKeys();
        if (rs.next()) {
            generatedKey = rs.getInt(1);
            a.setAircraft_id(generatedKey);
            return generatedKey;
        }
        return null;
        }
        else {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO aircraft (Registration, Iata, Icao, Icao24) VALUES (?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, null);
            stmt.setString(2, null);
            stmt.setString(3, null);
            stmt.setString(4, null);
            stmt.execute();
            int generatedKey = 0;
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                generatedKey = rs.getInt(1);
                return generatedKey;
            }
            return null;
        }
    }

    public static Integer insertarFlight(Connection conn, Flight f) throws SQLException{
        if(f != null) {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO flight (Number, Iata, Icao, Codeshared) VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, f.getFlight_number());
            stmt.setString(2, f.getFlight_iata());
            stmt.setString(3, f.getFlight_icao());
            stmt.setString(4, f.getFlight_codeshared());
            stmt.execute();
            int generatedKey = 0;
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                generatedKey = rs.getInt(1);
                f.setFlight_id(generatedKey);
                return generatedKey;
            }
            return null;
        } else {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO flight (Number, Iata, Icao, Codeshared) VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, null);
            stmt.setString(2, null);
            stmt.setString(3, null);
            stmt.setString(4, null);
            stmt.execute();
            int generatedKey = 0;
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                generatedKey = rs.getInt(1);
                return generatedKey;
            }
            return null;
        }
    }


    public static Integer insertarArrival(Connection conn, Arrival a) throws SQLException{
        if(a != null) {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO arrival (Airport, Timezone, Iata, Icao, Terminal, Gate, Baggage, Delay, Scheduled, Estimated, Actual, Estimated_runway, Actual_runway) VALUES (?, ?,?, ?,?,?, ?,?, ?,?,?, ?,?)", Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, a.getArrival_airport());
            stmt.setString(2, a.getArrival_timezone());
            stmt.setString(3, a.getArrival_iata());
            stmt.setString(4, a.getArrival_iaco());
            stmt.setString(5, a.getArrival_terminal());
            stmt.setString(6, a.getArrival_gate());
            stmt.setString(7, a.getArrival_baggage());
            stmt.setString(8, a.getArrival_delay());
            stmt.setString(9, a.getArrival_scheduled());
            stmt.setString(10, a.getArrival_estimated());
            stmt.setString(11, a.getArrival_actual());
            stmt.setString(12, a.getArrival_estimated_runway());
            stmt.setString(13, a.getArrival_actual_runway());
            stmt.execute();
            int generatedKey = 0;
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                generatedKey = rs.getInt(1);
                a.setArrival_id(generatedKey);
                return generatedKey;
            }
            return null;
        }
        else {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO arrival (Airport, Timezone, Iata, Icao, Terminal, Gate, Baggage, Delay, Scheduled, Estimated, Actual, Estimated_runway, Actual_runway) VALUES (?, ?,?, ?,?,?, ?,?, ?,?,?, ?,?)", Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, null);
            stmt.setString(2, null);
            stmt.setString(3, null);
            stmt.setString(4, null);
            stmt.setString(5, null);
            stmt.setString(6, null);
            stmt.setString(7, null);
            stmt.setString(8, null);
            stmt.setString(9, null);
            stmt.setString(10, null);
            stmt.setString(11, null);
            stmt.setString(12, null);
            stmt.setString(13, null);
            stmt.execute();
            int generatedKey = 0;
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                generatedKey = rs.getInt(1);
                return generatedKey;
            }
            return null;
        }
    }
    public static Integer insertarDeparture(Connection conn, Departure d) throws SQLException{
        if(d != null) {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO departure (Airport, Timezone, Iata, Icao, Terminal, Gate, Delay, Scheduled, Estimated, Actual, Estimated_runway, Actual_runway) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, d.getDeparture_airport());
            stmt.setString(2, d.getDeparture_timezone());
            stmt.setString(3, d.getDeparture_iata());
            stmt.setString(4, d.getDeparture_iaco());
            stmt.setString(5, d.getDeparture_terminal());
            stmt.setString(6, d.getDeparture_gate());
            stmt.setString(7, d.getDeparture_delay());
            stmt.setString(8, d.getDeparture_scheduled());
            stmt.setString(9, d.getDeparture_estimated());
            stmt.setString(10, d.getDeparture_actual());
            stmt.setString(11, d.getDeparture_estimated_runway());
            stmt.setString(12, d.getDeparture_actual_runway());
            stmt.execute();
            int generatedKey = 0;
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                generatedKey = rs.getInt(1);
                d.setDeparture_id(generatedKey);
                return generatedKey;
            }
            return null;
        } else {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO departure (Airport, Timezone, Iata, Icao, Terminal, Gate, Delay, Scheduled, Estimated, Actual, Estimated_runway, Actual_runway) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, null);
            stmt.setString(2, null);
            stmt.setString(3, null);
            stmt.setString(4, null);
            stmt.setString(5, null);
            stmt.setString(6, null);
            stmt.setString(7, null);
            stmt.setString(8, null);
            stmt.setString(9, null);
            stmt.setString(10, null);
            stmt.setString(11, null);
            stmt.setString(12, null);
            stmt.execute();
            int generatedKey = 0;
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                generatedKey = rs.getInt(1);
                return generatedKey;
            }
        }
        return null;
    }

    public static void actualizarVuelo(Vuelo v) throws SQLException {
        Connection conn = Init.initDb();
        PreparedStatement stmt = null;
        try {
            // solo tengo que actualizar Estado, Tanque y Comida
            stmt = conn.prepareStatement("UPDATE vuelo SET Estado = ?, Tanque = ?, Comida = ? WHERE Id = ?");
            stmt.setString(1, v.getEstado().getClass().getSimpleName());
            stmt.setInt(2, v.getTanque());
            stmt.setString(3, v.getComida());
            stmt.setInt(4, v.getId());
            stmt.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void borrarVuelo(Vuelo v) throws SQLException {
        Connection conn = Init.initDb();
        PreparedStatement stmt = null;
        try {
            // eliminar fila correspondiente en tabla aircraft, airline, arrival, departure, flight
            stmt = conn.prepareStatement("DELETE aircraft,airline,arrival,departure,flight FROM aircraft INNER JOIN airline ON airline.Id = ? INNER JOIN arrival ON arrival.Id = ? INNER JOIN departure ON departure.Id = ? INNER JOIN flight ON flight.Id = ? WHERE aircraft.Id = ?");
            stmt.setInt(1, v.getAirline().getAirline_id());
            stmt.setInt(2, v.getArrival().getArrival_id());
            stmt.setInt(3, v.getDeparture().getDeparture_id());
            stmt.setInt(4, v.getFlight().getFlight_id());
            stmt.setInt(5, v.getAircraft().getAircraft_id());
            int deleted = stmt.executeUpdate();;
            if(deleted > 0)
                UserService.mostrarMensajeAccion("Vuelo borrado");
            else
                UserService.mostrarMensajeAccion("Vuelo no encontrado");
        }
        catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
                return;
            } catch (SQLException e) {
                e.printStackTrace();
                return;
            }
        }
    }
    public static List<Vuelo> obtenerVuelosDb() throws SQLException {
        List<Vuelo> vuelos = new ArrayList<>();
        Connection conn = Init.initDb();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("SELECT * FROM vuelo");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Vuelo v = new Vuelo();
                v.setId(rs.getInt("Id"));
                v.setFlight_date(rs.getString("Flight_date"));
                v.setFlight_status(rs.getString("Flight_status"));
                Class<?> clazz = Class.forName("Dominio." + rs.getString("Estado"));
                Estado date = (Estado) clazz.newInstance();
                v.setEstado(date);
                v.setTanque(rs.getInt("Tanque"));
                v.setComida(rs.getString("Comida"));
                v.setDeparture2(cargarDeparture(conn, rs.getInt("Departure")));
                v.setArrival2(cargarArrival(conn, rs.getInt("Arrival")));
                v.setFlight2(cargarFlight(conn, rs.getInt("Flight")));
                v.setAircraft2(cargarAircraft(conn, rs.getInt("Aircraft")));
                v.setAirline2(cargarAirline(conn, rs.getInt("Airline")));
                vuelos.add(v);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        finally {
            try {
                conn.close();
                return vuelos;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return vuelos;
    }

    public static Departure cargarDeparture(Connection conn, Integer id){
        try{
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM departure WHERE Id = ?");
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                Departure d = new Departure();
                d.setDeparture_id(rs.getInt("Id"));
                d.setDeparture_airport(rs.getString("Airport"));
                d.setDeparture_timezone(rs.getString("Timezone"));
                d.setDeparture_iata(rs.getString("Iata"));
                d.setDeparture_iaco(rs.getString("Icao"));
                d.setDeparture_terminal(rs.getString("Terminal"));
                d.setDeparture_gate(rs.getString("Gate"));
                d.setDeparture_delay(rs.getString("Delay"));
                d.setDeparture_scheduled(rs.getString("Scheduled"));
                d.setDeparture_estimated(rs.getString("Estimated"));
                d.setDeparture_actual(rs.getString("Actual"));
                d.setDeparture_estimated_runway(rs.getString("Estimated_runway"));
                d.setDeparture_actual_runway(rs.getString("Actual_runway"));
            return d;
            } else return null;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static Arrival cargarArrival(Connection conn, Integer id){
        try{
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM arrival WHERE Id = ?");
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                Arrival a = new Arrival();
                a.setArrival_id(rs.getInt("Id"));
                a.setArrival_airport(rs.getString("Airport"));
                a.setArrival_timezone(rs.getString("Timezone"));
                a.setArrival_iata(rs.getString("Iata"));
                a.setArrival_iaco(rs.getString("Icao"));
                a.setArrival_terminal(rs.getString("Terminal"));
                a.setArrival_gate(rs.getString("Gate"));
                a.setArrival_baggage(rs.getString("Baggage"));
                a.setArrival_delay(rs.getString("Delay"));
                a.setArrival_scheduled(rs.getString("Scheduled"));
                a.setArrival_estimated(rs.getString("Estimated"));
                a.setArrival_actual(rs.getString("Actual"));
                a.setArrival_estimated_runway(rs.getString("Estimated_runway"));
                a.setArrival_actual_runway(rs.getString("Actual_runway"));
                return a;
            } else return null;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static Flight cargarFlight(Connection conn, Integer id){
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM flight WHERE Id = ?");
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
            Flight f = new Flight();
            f.setFlight_id(rs.getInt("Id"));
            f.setFlight_number(rs.getString("Number"));
            f.setFlight_iata(rs.getString("Iata"));
            f.setFlight_icao(rs.getString("Icao"));
            f.setFlight_codeshared(rs.getString("Codeshared"));
            return f;
            } else return null;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static Aircraft cargarAircraft(Connection conn, Integer id){
        try{
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM aircraft WHERE Id = ?");
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                Aircraft a = new Aircraft();
                a.setAircraft_id(rs.getInt("Id"));
                a.setAircraft_registration(rs.getString("Registration"));
                a.setAircraft_iata(rs.getString("Iata"));
                a.setAircraft_icao(rs.getString("Icao"));
                a.setAircraft_icao24(rs.getString("Icao24"));
                return a;
            } else return null;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    public static Airline cargarAirline(Connection conn, Integer id){
        try{
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM airline WHERE Id = ?");
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                Airline a = new Airline();
                a.setAirline_id(rs.getInt("Id"));
                a.setAirline_name(rs.getString("Name"));
                a.setAirline_iata(rs.getString("Iata"));
                a.setAirline_icao(rs.getString("Icao"));
                return a;
            } else return null;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}


