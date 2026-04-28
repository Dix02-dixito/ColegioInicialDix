package app.data;

import java.sql.*;


import app.data.interfaces.IUsuario;
import app.modelos.Usuario;
import app.conectores.MySQLConexion;

public class UsuarioDAO implements IUsuario {

    @Override
    public Usuario validar(String usuario, String contrasena) {

        Usuario u = null;

        String sql = "SELECT * FROM usuario WHERE correo = ? AND contrasena = ?";

        try {
            Connection cn = MySQLConexion.obtenerConexion();
            PreparedStatement ps = cn.prepareStatement(sql);

            ps.setString(1, usuario);
            ps.setString(2, contrasena);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                u = new Usuario();
                u.setId(rs.getInt("id_usuario"));
                u.setCodigo(rs.getString("codigo"));
                u.setNombre(rs.getString("nombre"));
                u.setUsuario(rs.getString("correo"));
                u.setRol(rs.getString("rol"));
            }

            rs.close();
            ps.close();
            cn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return u;
    }
}