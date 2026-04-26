package app.conectores;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConexion {
	private static final String URL = "jdbc:mysql://localhost:3306/colegio_db";
	private static final String USUARIO = "root";
	private static final String CLAVE = "mysql";
	
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection obtenerConexion() {
		Connection conexion = null;
		try {
			conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return conexion;
	}
}
