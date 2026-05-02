package app.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import app.conectores.MySQLConexion;
import app.data.interfaces.IMatricula;
import app.modelos.Matricula;

public class MatriculaDAO implements IMatricula {

    private Connection cn;
    
    public MatriculaDAO() {
        cn = MySQLConexion.obtenerConexion();
    }

    @Override
    public List<Matricula> listarMatriculas() {

        List<Matricula> lista = new ArrayList<>();

        try {

            CallableStatement cs =
                cn.prepareCall("{CALL sp_listar_matriculas()}");

            ResultSet rs = cs.executeQuery();

            while (rs.next()) {

                Matricula m = new Matricula();

                m.setIdMatricula(rs.getInt("id_matricula"));
                m.setIdEstudiante(rs.getInt("id_estudiante"));
                m.setIdApoderado(rs.getInt("id_apoderado"));
                m.setIdNivel(rs.getInt("id_nivel"));
                m.setEstado(rs.getString("estado"));
                m.setObservacion(rs.getString("observacion"));
                m.setAnio(rs.getInt("anio"));

                m.setNombresEstudiante(rs.getString("estudiante"));
                m.setApellidosEstudiante(rs.getString("apellidos"));
                m.setNombreApoderado(rs.getString("apoderado"));
                m.setRelacion(rs.getString("relacion"));
                m.setNombreNivel(rs.getString("nivel"));

                lista.add(m);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    @Override
    public Matricula buscarMatriculaActivaPorDni(String dni) {

        Matricula m = null;

        try {

            CallableStatement cs =
                cn.prepareCall("{CALL sp_buscar_matricula_activa_por_dni(?)}");

            cs.setString(1, dni);

            ResultSet rs = cs.executeQuery();

            if (rs.next()) {

                m = new Matricula();

                m.setIdMatricula(rs.getInt("id_matricula"));
                m.setIdEstudiante(rs.getInt("id_estudiante"));
                m.setIdApoderado(rs.getInt("id_apoderado"));
                m.setIdNivel(rs.getInt("id_nivel"));

                m.setNombreApoderado(rs.getString("apoderado"));
                m.setRelacion(rs.getString("relacion"));
                m.setNombreNivel(rs.getString("nivel"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return m;
    }

    @Override
    public List<String[]> listarApoderadosPorEstudiante(int idEstudiante) {

        List<String[]> lista = new ArrayList<>();

        try {

            CallableStatement cs =
                cn.prepareCall("{CALL sp_listar_relaciones(?)}");

            cs.setInt(1, idEstudiante);

            ResultSet rs = cs.executeQuery();

            while (rs.next()) {

                String[] fila = new String[3];

                fila[0] = String.valueOf(rs.getInt("id_apoderado"));
                fila[1] = rs.getString("nombres");
                fila[2] = rs.getString("relacion");

                lista.add(fila);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    @Override
    public List<String[]> listarNiveles() {

        List<String[]> lista = new ArrayList<>();

        try {

            CallableStatement cs =
                cn.prepareCall("{CALL sp_listar_niveles()}");

            ResultSet rs = cs.executeQuery();

            while (rs.next()) {

                String[] fila = new String[2];

                fila[0] = String.valueOf(rs.getInt("id_nivel"));
                fila[1] = rs.getString("nombre");

                lista.add(fila);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
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
    public boolean cambiarEstado(int idMatricula, String estado) {

        try {

            CallableStatement cs =
                cn.prepareCall("{CALL sp_editar_matricula(?,?,?,?,?)}");

            cs.setInt(1, idMatricula);
            cs.setInt(2, 0);
            cs.setInt(3, 0);
            cs.setString(4, estado);
            cs.setString(5, "");

            return cs.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
    public Matricula buscarPorDniEstudiante(String dni) {

        Matricula m = null;

        String sql = "SELECT a.dni AS dni_apoderado, " +
         "a.nombres, a.apellidos, " +
         "a.telefono, a.correo, a.direccion, " +
         "ea.relacion " +
         "FROM estudiante e " +
         "INNER JOIN estudiante_apoderado ea ON e.id_estudiante = ea.id_estudiante " +
         "INNER JOIN apoderado a ON ea.id_apoderado = a.id_apoderado " +
         "WHERE e.dni = ?";

    try (Connection con = MySQLConexion.obtenerConexion();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setString(1, dni);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {

            m = new Matricula();

            m.setDniApoderado(rs.getString("dni_apoderado"));
    m.setNombreApoderado(rs.getString("nombres") + " " + rs.getString("apellidos"));
    m.setRelacion(rs.getString("relacion"));
    m.setTelefonoApoderado(rs.getString("telefono"));
    m.setCorreoApoderado(rs.getString("correo"));
    m.setDireccionApoderado(rs.getString("direccion"));
               
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return m;
    }
    
    public boolean existeMatriculaActiva(int idEstudiante) {

        String sql = "SELECT COUNT(*) FROM matricula WHERE id_estudiante=? AND estado='ACTIVO'";

    try (Connection con = MySQLConexion.obtenerConexion();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setInt(1, idEstudiante);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            return rs.getInt(1) > 0;
        }

    } catch (Exception e) {
        throw new RuntimeException("Error al validar matrícula activa: " + e.getMessage());
        }

        return false;
    }


    public boolean generar(Matricula m) {
    	String sql = "INSERT INTO matricula (id_estudiante, id_apoderado, id_nivel, anio, estado, observacion) VALUES (?, ?, ?, ?, ?, ?)";

    try (Connection con = MySQLConexion.obtenerConexion();
         PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

        ps.setInt(1, m.getIdEstudiante());
        ps.setInt(2, m.getIdApoderado());
        ps.setInt(3, m.getIdNivel());
        ps.setInt(4, m.getAnio());
        ps.setString(5, m.getEstado());
        ps.setString(6, m.getObservacion());

        int filas = ps.executeUpdate();

        if (filas > 0) {
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                m.setIdMatricula(rs.getInt(1));
            }
            return true;
        }

    } catch (Exception e) {
    	e.printStackTrace();
    	return false;
    	    }

    	    return false;
    	}

}