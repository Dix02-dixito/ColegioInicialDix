package app.data;

import java.sql.*;
import java.util.*;

import app.conectores.MySQLConexion;
import app.data.interfaces.IMatricula;
import app.modelos.Apoderado;
import app.modelos.Matricula;

public class MatriculaDAO implements IMatricula {

    private Connection cn;

    public MatriculaDAO() {
        cn = MySQLConexion.obtenerConexion();
    }

    @Override
    public Matricula buscarMatriculaActiva(String dni) {

        Matricula m = null;

        try {

            CallableStatement cs =
                cn.prepareCall("{CALL sp_buscar_matricula_activa_por_dni(?)}");

            cs.setString(1, dni);

            ResultSet rs = cs.executeQuery();

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

            CallableStatement cs =
                cn.prepareCall("{CALL sp_editar_matricula(?,?,?,?,?)}");

            cs.setInt(1, m.getIdMatricula());
            cs.setInt(2, m.getIdNivel());
            cs.setInt(3, m.getIdApoderado());
            cs.setString(4, m.getEstado());
            cs.setString(5, m.getObservacion());

            return cs.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }


    @Override
    public Apoderado buscarApoderadoPorDni(String dni, int idEstudiante) {

        Apoderado a = null;

        try {

            CallableStatement cs =
                cn.prepareCall("{CALL sp_buscar_apoderado_relacion(?,?)}");

            cs.setString(1, dni);
            cs.setInt(2, idEstudiante);

            ResultSet rs = cs.executeQuery();

            if (rs.next()) {

                a = new Apoderado();

                a.setIdApoderado(rs.getInt("id_apoderado"));
                a.setDni(rs.getString("dni"));
                a.setNombres(rs.getString("nombres"));
                a.setApellidos(rs.getString("apellidos"));
                a.setTelefono(rs.getString("telefono"));
                a.setDireccion(rs.getString("direccion"));
                a.setCorreo(rs.getString("correo"));

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return a;
    }


    @Override
    public boolean existeRelacion(int idEstudiante, int idApoderado) {

        try {

            CallableStatement cs =
                cn.prepareCall("{CALL sp_existe_relacion(?,?)}");

            cs.setInt(1, idEstudiante);
            cs.setInt(2, idApoderado);

            ResultSet rs = cs.executeQuery();

            if (rs.next()) {
                return rs.getInt("existe") > 0;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }


    @Override
    public boolean cambiarApoderado(int idMatricula, int idEstudiante, int idNuevoApoderado) {

        try {

            CallableStatement cs =
                cn.prepareCall("{CALL sp_cambiar_apoderado(?,?)}");

            cs.setInt(1, idMatricula);
            cs.setInt(2, idNuevoApoderado);

            return cs.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }


    @Override
    public boolean renovarMatricula(int idMatricula) {

        try {

            CallableStatement cs =
                cn.prepareCall("{CALL sp_renovar_matricula(?)}");

            cs.setInt(1, idMatricula);

            return cs.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }


    @Override
    public boolean eliminarMatricula(int idMatricula) {

        try {

            PreparedStatement ps = cn.prepareStatement(
                "DELETE FROM matricula WHERE id_matricula=?"
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

            CallableStatement cs =
                cn.prepareCall("{CALL sp_listar_niveles()}");

            ResultSet rs = cs.executeQuery();

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

            CallableStatement cs =
                cn.prepareCall("{CALL sp_listar_relaciones(?)}");

            cs.setInt(1, idEstudiante);

            ResultSet rs = cs.executeQuery();

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

    @Override
    public boolean existeMatriculaActiva(int idEstudiante) {

        try {

            CallableStatement cs =
                cn.prepareCall("{CALL sp_existe_matricula_activa(?)}");

            cs.setInt(1, idEstudiante);

            ResultSet rs = cs.executeQuery();

            if (rs.next()) {
                return rs.getInt("total") > 0;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean generar(Matricula m) {

        try {

            CallableStatement cs =
                cn.prepareCall("{CALL sp_generar_matricula(?,?,?,?,?,?)}");

            cs.setInt(1, m.getIdEstudiante());
            cs.setInt(2, m.getIdApoderado());
            cs.setInt(3, m.getIdNivel());
            cs.setInt(4, m.getAnio());
            cs.setString(5, m.getEstado());
            cs.setString(6, m.getObservacion());

            return cs.executeUpdate() > 0;

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
    
    public boolean editarEstadoMatricula(int idMatricula, String estado, String observacion) {

        try {

            CallableStatement cs =
                cn.prepareCall("{CALL sp_editar_estado_matricula(?,?,?)}");

            cs.setInt(1, idMatricula);
            cs.setString(2, estado);
            cs.setString(3, observacion);

            return cs.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
    
    public Matricula buscarMatriculaInactiva(String dni) {

        Matricula m = null;

        try {

            CallableStatement cs =
                cn.prepareCall("{CALL sp_buscar_matricula_inactiva_por_dni(?)}");

            cs.setString(1, dni);

            ResultSet rs = cs.executeQuery();

            if (rs.next()) {
                m = mapearMatricula(rs);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return m;
    }
}