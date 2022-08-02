package Database;

import Dominio.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class UsuarioDb {

    public static void registrarUsuario(Usuario user) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        Connection conn = Init.initDb();
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO usuario (Nombre, Apellido, Mail, Contraseña, PaisOrigen) VALUES (?, ?, ?, ?, ?)");
        stmt.setString(1, user.getNombre());
        stmt.setString(2, user.getApellido());
        stmt.setString(3, user.getMail());
        stmt.setString(4, user.getPassword());
        stmt.setString(5, user.getPaisOrigen());
        stmt.executeUpdate();
        conn.close();
    }

    public static Usuario buscarEnDb(String email) throws SQLException {
        Connection conn = Init.initDb();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM usuario WHERE Mail = ?");
        stmt.setString(1, email);
        ResultSet rs = stmt.executeQuery();
        Usuario user = null;
        if (rs.next()) {
            user = new Usuario(rs.getString("Nombre"), rs.getString("Apellido"), rs.getString("Mail"), rs.getString("Contraseña"), rs.getString("PaisOrigen"));
        }
        conn.close();
        return user;
    }
}
