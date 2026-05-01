package app.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import app.conectores.MySQLConexion;
import app.modelos.Apoderado;

public class ApoderadoDAO {

public boolean guardar(Apoderado a) {

    String sql = "INSERT INTO apoderado (dni, nombres, apellidos, direccion, correo, telefono, sexo) VALUES (?, ?, ?, ?, ?, ?, ?)";

try (Connection con = MySQLConexion.obtenerConexion();
     PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

	ps.setString(1, a.getDni());
	ps.setString(2, a.getNombres());
    ps.setString(3, a.getApellidos());
    ps.setString(4, a.getDireccion());
    ps.setString(5, a.getCorreo());
    ps.setString(6, a.getTelefono());
    ps.setString(7, a.getSexo());

    int filas = ps.executeUpdate();

    if (filas > 0) {
        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) {
            a.setIdApoderado(rs.getInt(1));
        }
        return true;
    }

    return false;

} catch (Exception e) {
    throw new RuntimeException("Error al guardar apoderado: " + e.getMessage());
    }
}



public Apoderado buscarPorDni(String dni) {

    String sql = "SELECT * FROM apoderado WHERE dni=?";

try (Connection con = MySQLConexion.obtenerConexion();
     PreparedStatement ps = con.prepareStatement(sql)) {

    ps.setString(1, dni);
    ResultSet rs = ps.executeQuery();

    if (rs.next()) {
        Apoderado a = new Apoderado();
        a.setIdApoderado(rs.getInt("id_apoderado"));
    a.setDni(rs.getString("dni"));
    a.setNombres(rs.getString("nombres"));
    a.setApellidos(rs.getString("apellidos"));
    a.setDireccion(rs.getString("direccion"));
    a.setCorreo(rs.getString("correo"));
    a.setTelefono(rs.getString("telefono"));
    a.setSexo(rs.getString("sexo"));
        return a;
    }

} catch (Exception e) {
    throw new RuntimeException("Error al buscar por DNI: " + e.getMessage());
    }

    return null;
}


public Apoderado buscarPorId(int id) {
    String sql = "SELECT * FROM apoderado WHERE id_apoderado=?";

try (Connection con = MySQLConexion.obtenerConexion();
     PreparedStatement ps = con.prepareStatement(sql)) {

    ps.setInt(1, id);
    ResultSet rs = ps.executeQuery();

    if (rs.next()) {
        Apoderado a = new Apoderado();
        a.setIdApoderado(rs.getInt("id_apoderado"));
    a.setDni(rs.getString("dni"));
    a.setNombres(rs.getString("nombres"));
    a.setApellidos(rs.getString("apellidos"));
    a.setDireccion(rs.getString("direccion"));
    a.setCorreo(rs.getString("correo"));
    a.setTelefono(rs.getString("telefono"));
    a.setSexo(rs.getString("sexo"));
        return a;
    }

} catch (Exception e) {
    throw new RuntimeException("Error buscarPorId: " + e.getMessage());
    }

    return null;
}


public List<Apoderado> listarApoderado() {

	List<Apoderado> lista = new ArrayList<>();
    String sql = "SELECT * FROM apoderado";

try (Connection con = MySQLConexion.obtenerConexion();
     PreparedStatement ps = con.prepareStatement(sql);
     ResultSet rs = ps.executeQuery()) {

    while (rs.next()) {
        Apoderado a = new Apoderado();
        a.setIdApoderado(rs.getInt("id_apoderado"));
    a.setDni(rs.getString("dni"));
    a.setNombres(rs.getString("nombres"));
    a.setApellidos(rs.getString("apellidos"));
    a.setDireccion(rs.getString("direccion"));
    a.setCorreo(rs.getString("correo"));
    a.setTelefono(rs.getString("telefono")); 
    a.setSexo(rs.getString("sexo"));
        lista.add(a);
    }

} catch (Exception e) {
    throw new RuntimeException("Error al listar apoderados");
    }

    return lista;
}

public boolean actualizarApoderado(Apoderado a) {

	String sql = "UPDATE apoderado SET dni=?, nombres=?, apellidos=?, direccion=?, correo=?, telefono=?, sexo=? WHERE id_apoderado=?";

try (Connection con = MySQLConexion.obtenerConexion();
     PreparedStatement ps = con.prepareStatement(sql)) {

    ps.setString(1, a.getDni());
    ps.setString(2, a.getNombres());
    ps.setString(3, a.getApellidos());
    ps.setString(4, a.getDireccion());
    ps.setString(5, a.getCorreo());
    ps.setString(6, a.getTelefono());
    ps.setString(7, a.getSexo());
    ps.setInt(8, a.getIdApoderado());

    int filas = ps.executeUpdate();
    return filas > 0;

} catch (Exception e) {
    throw new RuntimeException("Error al actualizar apoderado: " + e.getMessage());
    }
}

public boolean eliminarApoderado(int id) {

    String sql = "DELETE FROM apoderado WHERE id_apoderado=?";

try (Connection con = MySQLConexion.obtenerConexion();
     PreparedStatement ps = con.prepareStatement(sql)) {

    ps.setInt(1, id);

    int filas = ps.executeUpdate();
    return filas > 0;

} catch (Exception e) {
    throw new RuntimeException("Error al eliminar apoderado: " + e.getMessage());
    }
    
}
}
    
