package app.data;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import app.conectores.MySQLConexion;
import app.modelos.Estudiante;

public class EstudianteDAO {

    public boolean guardar(Estudiante e) {

        String sql = "INSERT INTO estudiante (dni, nombres, apellidos, fecha_nacimiento, sexo) VALUES (?, ?, ?, ?, ?)";

        try (Connection con = MySQLConexion.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

        	ps.setString(0, e.getDni());
        	ps.setString(1, e.getNombres());
            ps.setString(2, e.getApellidos());
            ps.setDate(3, e.getFechaNacimiento());
            ps.setString(4, e.getSexo());

            int filas = ps.executeUpdate();

            if (filas > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    e.setDni(rs.getString(1)); 
                }
                return true;
            }

            return false;

        } catch (Exception ex) {
            throw new RuntimeException("Error al guardar estudiante: " + ex.getMessage());
        }
    }

    public Estudiante buscarPorDni(String dni) {

    	String sql = "SELECT * FROM estudiante WHERE dni = ?";

        try (Connection con = MySQLConexion.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, dni);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Estudiante e = new Estudiante();

                e.setIdEstudiante(rs.getInt("id_estudiante"));
                e.setDni(rs.getString("dni"));
                e.setNombres(rs.getString("nombres"));
                e.setApellidos(rs.getString("apellidos"));

                return e;
            }

        } catch (Exception e) {
            e.printStackTrace(); // 🔥 IMPORTANTE PARA VER ERROR REAL
            throw new RuntimeException("Error al buscar estudiante");
        }

        return null;
    }

    public List<Estudiante> listar() {

        List<Estudiante> lista = new ArrayList<>();
        String sql = "SELECT * FROM estudiante";

        try (Connection con = MySQLConexion.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(new Estudiante(
                ));
            }

        } catch (Exception e) {
            throw new RuntimeException("Error al listar estudiantes");
        }

        return lista;
    }

	
}
