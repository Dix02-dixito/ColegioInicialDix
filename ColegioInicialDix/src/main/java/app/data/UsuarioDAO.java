package app.data;

import java.sql.*;


import app.data.interfaces.IUsuario;
import app.modelos.Usuario;
import app.conectores.MySQLConexion;

public class UsuarioDAO implements IUsuario {

    @Override
    public Usuario validar(String usuario, String contrasena) {

        Usuario u = null;

        String sql = "SELECT * FROM usuario WHERE codigo = ? AND contrasena = ?";

        try {
            Connection cn = MySQLConexion.obtenerConexion();
            PreparedStatement ps = cn.prepareStatement(sql);

            ps.setString(1, usuario); // usuario = codigo
            ps.setString(2, contrasena);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                u = new Usuario();
                u.setId(rs.getInt("id_usuario"));
                u.setCodigo(rs.getString("codigo"));      // código (login)
                u.setNombre(rs.getString("nombre"));
                u.setUsuario(rs.getString("codigo"));     // usuario = código
                u.setCorreo(rs.getString("correo"));      // 🔥 ESTE FALTABA
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