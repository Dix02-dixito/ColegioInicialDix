package app.data;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import app.conectores.MySQLConexion;
import app.modelos.EstudianteApoderado;

public class EstudianteApoderadoDAO {

    // insertar relacion
    public boolean insertarRelacion(EstudianteApoderado ea) {

        String sql = "INSERT INTO estudiante_apoderado (id_estudiante, id_apoderado, relacion) VALUES (?, ?, ?)";

        try (Connection con = MySQLConexion.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, ea.getIdEstudiante());
            ps.setInt(2, ea.getIdApoderado());
            ps.setString(3, ea.getRelacion());

            int filas = ps.executeUpdate();
            return filas > 0;

        } catch (Exception e) {
            throw new RuntimeException("Error al insertar relación: " + e.getMessage());
        }
    }

    // validar relacion
    public boolean existeRelacion(int idEstudiante, int idApoderado) {

        String sql = "SELECT COUNT(*) FROM estudiante_apoderado WHERE id_estudiante=? AND id_apoderado=?";

        try (Connection con = MySQLConexion.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idEstudiante);
            ps.setInt(2, idApoderado);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }

        } catch (Exception e) {
            throw new RuntimeException("Error al validar relación");
        }

        return false;
    }

    // lista
    public List<EstudianteApoderado> listarRelacion() {

        List<EstudianteApoderado> lista = new ArrayList<>();
        String sql = "SELECT * FROM estudiante_apoderado";

        try (Connection con = MySQLConexion.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                EstudianteApoderado ea = new EstudianteApoderado();
                ea.setId(rs.getInt("id"));
                ea.setIdEstudiante(rs.getInt("id_estudiante"));
                ea.setIdApoderado(rs.getInt("id_apoderado"));
                ea.setRelacion(rs.getString("relacion"));

                lista.add(ea);
            }

        } catch (Exception e) {
            throw new RuntimeException("Error al listar relaciones");
        }

        return lista;
    }

    // eliminar relacion
    public boolean eliminarRelacion(int id) {

        String sql = "DELETE FROM estudiante_apoderado WHERE id=?";

        try (Connection con = MySQLConexion.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            int filas = ps.executeUpdate();
            return filas > 0;

        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar relación");
        }
    }

    // lista por estudiante
    public List<EstudianteApoderado> listarPorEstudiante(int idEstudiante) {

        List<EstudianteApoderado> lista = new ArrayList<>();
        String sql = "SELECT * FROM estudiante_apoderado WHERE id_estudiante=?";

        try (Connection con = MySQLConexion.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idEstudiante);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                EstudianteApoderado ea = new EstudianteApoderado();
                ea.setId(rs.getInt("id"));
                ea.setIdEstudiante(rs.getInt("id_estudiante"));
                ea.setIdApoderado(rs.getInt("id_apoderado"));
                ea.setRelacion(rs.getString("relacion"));

                lista.add(ea);
            }

        } catch (Exception e) {
            throw new RuntimeException("Error al listar por estudiante");
        }

        return lista;
    }
}