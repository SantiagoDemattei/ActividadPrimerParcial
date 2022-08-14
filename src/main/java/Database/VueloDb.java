package Database;

import Dominio.*;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;

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
}
