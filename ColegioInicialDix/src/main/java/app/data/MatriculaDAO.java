package app.data;

import java.sql.Connection;
import java.sql.PreparedStatement;

import app.controladores.MySQLConexion;
import app.modelos.Matricula;

public class MatriculaDAO {

    Connection cn;

    public MatriculaDAO() {
        cn = MySQLConexion.obtenerConexion(); // ✔️ corregido
    }

    public boolean editarMatricula(Matricula matricula) {
        boolean estado = false;

        try {
            String sql = "UPDATE matricula SET id_nivel=?, id_apoderado=?, estado=?, observacion=? WHERE id_matricula=?";
            PreparedStatement ps = cn.prepareStatement(sql);

            ps.setInt(1, matricula.getIdNivel());
            ps.setInt(2, matricula.getIdApoderado());
            ps.setString(3, matricula.getEstado());
            ps.setString(4, matricula.getObservacion());
            ps.setInt(5, matricula.getIdMatricula());

            estado = ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return estado;
    }

    public boolean renovarMatricula(int idMatricula) {
        boolean estado = false;

        try {
            String sql = "UPDATE matricula SET estado='ACTIVO' WHERE id_matricula=?";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, idMatricula);

            estado = ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return estado;
    }

    public boolean cancelarMatricula(int idMatricula) {
        boolean estado = false;

        try {
            String sql = "UPDATE matricula SET estado='CANCELADO' WHERE id_matricula=?";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, idMatricula);

            estado = ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return estado;
    }
}