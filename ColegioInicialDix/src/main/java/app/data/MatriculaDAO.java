package app.data;

import java.sql.*;
import java.util.*;

import app.controladores.MySQLConexion;
import app.data.interfaces.IMatricula;
import app.modelos.Apoderado;
import app.modelos.Matricula;

public class MatriculaDAO implements IMatricula {

    private Connection cn;

    public MatriculaDAO() {
        cn = MySQLConexion.obtenerConexion();
    }


    @Override
    public Matricula buscarPorDniOCodigo(String dni, Integer id) {

        Matricula m = null;

        try {

            String sql =
                "SELECT m.*, " +
                "e.nombres, e.apellidos, e.dni, " +

                "a.nombres AS apoderado, " +
                "a.apellidos AS apellido_apoderado, " +
                "a.telefono, a.direccion, a.correo, " +

                "ea.relacion, " +
                "n.nombre AS nivel " +

                "FROM matricula m " +
                "INNER JOIN estudiante e ON m.id_estudiante = e.id_estudiante " +
                "INNER JOIN apoderado a ON m.id_apoderado = a.id_apoderado " +

                "INNER JOIN estudiante_apoderado ea " +
                "ON ea.id_estudiante = m.id_estudiante " +
                "AND ea.id_apoderado = m.id_apoderado " +

                "INNER JOIN nivel n ON m.id_nivel = n.id_nivel " +
                "WHERE e.dni = ?";

            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, dni);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                m = mapearMatricula(rs);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return m;
    }


    @Override
    public boolean editarMatricula(Matricula m) {

        try {

            String sql =
                "UPDATE matricula " +
                "SET id_nivel=?	, id_apoderado=?, estado=?, observacion=? " +
                "WHERE id_matricula=?";

            PreparedStatement ps = cn.prepareStatement(sql);

            ps.setInt(1, m.getIdNivel());
            ps.setInt(2, m.getIdApoderado());
            ps.setString(3, m.getEstado());
            ps.setString(4, m.getObservacion());
            ps.setInt(5, m.getIdMatricula());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }


    // buscar apoderado solo si tiene alguna relacion con el estudiante si no tiene no saldra la alerta de no tiene relacion
    @Override
    public Apoderado buscarApoderadoPorDni(String dni, int idEstudiante) {

        Apoderado a = null;

        try {

            String sql =
                "SELECT a.*, ea.relacion " +
                "FROM apoderado a " +
                "INNER JOIN estudiante_apoderado ea " +
                "ON a.id_apoderado = ea.id_apoderado " +
                "WHERE ea.id_estudiante = ? AND a.dni = ?";

            PreparedStatement ps = cn.prepareStatement(sql);

            ps.setInt(1, idEstudiante);
            ps.setString(2, dni);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                a = new Apoderado();

                a.setIdApoderado(rs.getInt("id_apoderado"));
                a.setDni(rs.getString("dni"));
                a.setNombres(rs.getString("nombres"));
                a.setApellidos(rs.getString("apellidos"));
                a.setTelefono(rs.getString("telefono"));
                a.setDireccion(rs.getString("direccion"));
                a.setCorreo(rs.getString("correo"));

                a.setRelacion(rs.getString("relacion"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return a;
    }


    // validar relacion con el estudiante
    @Override
    public boolean existeRelacion(int idEstudiante, int idApoderado) {

        try {

            String sql =
                "SELECT * FROM estudiante_apoderado " +
                "WHERE id_estudiante=? AND id_apoderado=?";

            PreparedStatement ps = cn.prepareStatement(sql);

            ps.setInt(1, idEstudiante);
            ps.setInt(2, idApoderado);

            ResultSet rs = ps.executeQuery();

            return rs.next();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }


    // cambiar apoderado
    @Override
    public boolean cambiarApoderado(int idMatricula, int idEstudiante, int idNuevoApoderado) {

        try {

            String sql =
                "UPDATE matricula SET id_apoderado=? WHERE id_matricula=?";

            PreparedStatement ps = cn.prepareStatement(sql);

            ps.setInt(1, idNuevoApoderado);
            ps.setInt(2, idMatricula);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }


    @Override
    public Matricula mapearMatricula(ResultSet rs) throws Exception {

        Matricula m = new Matricula();

        m.setIdMatricula(rs.getInt("id_matricula"));
        m.setIdEstudiante(rs.getInt("id_estudiante"));
        m.setIdApoderado(rs.getInt("id_apoderado"));
        m.setIdNivel(rs.getInt("id_nivel"));
        m.setAnio(rs.getInt("anio"));
        m.setEstado(rs.getString("estado"));
        m.setObservacion(rs.getString("observacion"));

        m.setDni(rs.getString("dni"));
        m.setNombresEstudiante(rs.getString("nombres"));
        m.setApellidosEstudiante(rs.getString("apellidos"));

        m.setNombreApoderado(rs.getString("apoderado"));
        m.setRelacion(rs.getString("relacion"));
        m.setNombreNivel(rs.getString("nivel"));

        return m;
    }


    @Override
    public boolean renovarMatricula(int idMatricula) {

        try {

            PreparedStatement ps = cn.prepareStatement(
                "UPDATE matricula SET estado='ACTIVO' WHERE id_matricula=?"
            );

            ps.setInt(1, idMatricula);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }


    @Override
    public boolean cancelarMatricula(int idMatricula) {

        try {

            PreparedStatement ps = cn.prepareStatement(
                "UPDATE matricula SET estado='CANCELADO' WHERE id_matricula=?"
            );

            ps.setInt(1, idMatricula);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }


    @Override
    public List<String> listarNiveles() {

        List<String> lista = new ArrayList<>();

        try {

            ResultSet rs = cn.prepareStatement(
                "SELECT nombre FROM nivel"
            ).executeQuery();

            while (rs.next()) {
                lista.add(rs.getString("nombre"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }


    @Override
    public List<Matricula> listarRelacionApoderadoEstudiante(int idEstudiante) {

        List<Matricula> lista = new ArrayList<>();

        try {

            String sql =
                "SELECT ea.relacion, a.nombres " +
                "FROM estudiante_apoderado ea " +
                "INNER JOIN apoderado a ON ea.id_apoderado = a.id_apoderado " +
                "WHERE ea.id_estudiante=?";

            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, idEstudiante);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Matricula m = new Matricula();

                m.setNombreApoderado(rs.getString("nombres"));
                m.setRelacion(rs.getString("relacion"));

                lista.add(m);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }
}