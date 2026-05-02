package app.data;

import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

import java.util.ArrayList;
import java.util.List;

import app.conectores.MySQLConexion;
import app.data.interfaces.IEstudiante;
import app.modelos.Estudiante;

public class EstudianteDAO implements IEstudiante {

    private Connection cn;

    public EstudianteDAO(){
        cn = MySQLConexion.obtenerConexion();
    }

    @Override
    public Estudiante buscarPorDni(String dni) {

        Estudiante e = null;

        try {

            CallableStatement cs =
                cn.prepareCall("{CALL sp_buscar_estudiante_relacion(?)}");

            cs.setString(1, dni);

            ResultSet rs = cs.executeQuery();

            if (rs.next()) {

                e = new Estudiante();

                e.setIdEstudiante(rs.getInt("id_estudiante"));
                e.setDni(rs.getString("dni"));
                e.setNombres(rs.getString("nombres"));
                e.setApellidos(rs.getString("apellidos"));
                e.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
                e.setSexo(rs.getString("sexo"));

                // 🔥 ESTO TE FALTABA
                e.setNombreApoderado(rs.getString("nombre_apoderado"));
                e.setRelacion(rs.getString("relacion"));
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return e;
    }

    @Override
    public boolean registrarEstudiante(Estudiante e) {

        try {

            CallableStatement cs =
                cn.prepareCall("{CALL sp_registrar_estudiante(?,?,?,?,?)}");

            cs.setString(1, e.getDni());
            cs.setString(2, e.getNombres());
            cs.setString(3, e.getApellidos()); // ✔ consistente
            cs.setDate(4, e.getFechaNacimiento());
            cs.setString(5, e.getSexo());

            cs.execute();
            return true;

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean editarEstudiante(Estudiante e) {

        try {

            CallableStatement cs =
                cn.prepareCall("{CALL sp_editar_estudiante(?,?,?,?,?)}");

            cs.setString(1, e.getDni());
            cs.setString(2, e.getNombres());
            cs.setString(3, e.getApellidos()); // ✔ consistente
            cs.setDate(4, e.getFechaNacimiento());
            cs.setString(5, e.getSexo());

            cs.execute();
            return true;

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean eliminarEstudiante(int idEstudiante) {

        try {

            CallableStatement cs =
                cn.prepareCall("{CALL sp_eliminar_estudiante(?)}");

            cs.setInt(1, idEstudiante);

            return cs.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public List<Estudiante> listarEstudiantes() {

        List<Estudiante> lista = new ArrayList<>();

        try {

            CallableStatement cs =
                cn.prepareCall("{CALL sp_listar_estudiantes()}");

            ResultSet rs = cs.executeQuery();

            while(rs.next()){
                lista.add(mapear(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    @Override
    public boolean existeDni(String dni) {

        try {

            CallableStatement cs =
                cn.prepareCall("{CALL sp_existe_dni(?)}");

            cs.setString(1, dni);

            ResultSet rs = cs.executeQuery();

            if(rs.next()){
                return rs.getInt("existe") > 0;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean tieneMatricula(int idEstudiante) {

        try {

            CallableStatement cs =
                cn.prepareCall("{CALL sp_tiene_matricula_por_id(?)}");

            cs.setInt(1, idEstudiante);

            ResultSet rs = cs.executeQuery();

            if(rs.next()){
                return rs.getInt("tiene") > 0;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public int obtenerIdPorDni(String dni) {
        try {
            CallableStatement cs =
                cn.prepareCall("{CALL sp_buscar_estudiante_dni(?)}");

            cs.setString(1, dni);
            ResultSet rs = cs.executeQuery();

            if (rs.next()) {
                return rs.getInt("id_estudiante");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int obtenerIdApoderadoPorDni(String dni) {
        try {
            CallableStatement cs =
                cn.prepareCall("{CALL sp_buscar_apoderado_dni(?)}");

            cs.setString(1, dni);

            ResultSet rs = cs.executeQuery();

            if (rs.next()) {
                return rs.getInt("id_apoderado");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public boolean insertarRelacion(int idEstudiante, int idApoderado, String relacion){

        try{

            CallableStatement cs =
                cn.prepareCall("{CALL sp_insertar_relacion(?,?,?)}");

            cs.setInt(1, idEstudiante);
            cs.setInt(2, idApoderado);
            cs.setString(3, relacion);

            return cs.executeUpdate() > 0;

        }catch(Exception e){
            e.printStackTrace();
        }

        return false;
    }

    // 🔥 MÉTODO CORREGIDO
    private Estudiante mapear(ResultSet rs) throws Exception {

        Estudiante e = new Estudiante();

        e.setIdEstudiante(rs.getInt("id_estudiante"));
        e.setDni(rs.getString("dni"));
        e.setNombres(rs.getString("nombres"));
        e.setApellidos(rs.getString("apellidos")); // 👈 CLAVE
        e.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
        e.setSexo(rs.getString("sexo"));

        return e;
    }

    public int registrarYRetornarId(Estudiante e) {

        try {

            PreparedStatement ps = cn.prepareStatement(
                "INSERT INTO estudiante(dni, nombres, apellidos, fecha_nacimiento, sexo) VALUES (?,?,?,?,?)",
                PreparedStatement.RETURN_GENERATED_KEYS
            );

            ps.setString(1, e.getDni());
            ps.setString(2, e.getNombres());
            ps.setString(3, e.getApellidos());
            ps.setDate(4, e.getFechaNacimiento());
            ps.setString(5, e.getSexo());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return 0;
    }
}