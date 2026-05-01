package app.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;

import app.modelos.Actividad;
import app.conectores.MySQLConexion;

public class ActividadDAO {

    // 🔍 LISTAR ACTIVIDADES
    public List<Actividad> listar() {
        List<Actividad> lista = new ArrayList<>();

        try {
            Connection con = MySQLConexion.obtenerConexion();

            String sql = "SELECT u.nombre AS usuario, a.accion, a.detalle, a.fecha, a.hora " +
                         "FROM actividad a " +
                         "INNER JOIN usuario u ON a.id_usuario = u.id_usuario " +
                         "ORDER BY a.fecha DESC, a.hora DESC";

            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Actividad a = new Actividad();
                a.setUsuario(rs.getString("usuario"));
                a.setAccion(rs.getString("accion"));
                a.setDetalle(rs.getString("detalle"));
                a.setFecha(rs.getString("fecha"));
                a.setHora(rs.getString("hora"));

                lista.add(a);
            }

            rs.close();
            ps.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    // 🔥 REGISTRAR LOG (LO NUEVO)
    public void registrar(int idUsuario, String accion, String detalle) {
        try {
            Connection con = MySQLConexion.obtenerConexion();

            String sql = "INSERT INTO actividad (id_usuario, accion, detalle, fecha, hora) " +
                         "VALUES (?, ?, ?, CURDATE(), CURTIME())";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idUsuario);
            ps.setString(2, accion);
            ps.setString(3, detalle);

            ps.executeUpdate();

            ps.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
