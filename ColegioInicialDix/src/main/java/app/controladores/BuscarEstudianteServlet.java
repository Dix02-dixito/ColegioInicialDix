package app.controladores;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import app.data.EstudianteDAO;
import app.data.MatriculaDAO;
import app.modelos.Estudiante;
import app.modelos.Matricula;


@WebServlet("/BuscarEstudianteServlet")
public class BuscarEstudianteServlet extends HttpServlet {

private static final long serialVersionUID = 1L;

EstudianteDAO estudianteDAO = new EstudianteDAO();
MatriculaDAO matriculaDAO = new MatriculaDAO();

protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException {

    String dni = request.getParameter("dni");

Estudiante e = estudianteDAO.buscarPorDni(dni);

response.setContentType("application/json");
PrintWriter out = response.getWriter();

if (e != null) {

	Matricula m = matriculaDAO.buscarPorDniEstudiante(dni);

	String nombreApo = "";
	String dniApo = "";
	String relacion = "";

	if (m != null) {
	    nombreApo = m.getNombreApoderado();
	    dniApo = m.getDniApoderado();
	    relacion = m.getRelacion();
	}
	java.sql.Date fechaNacimiento = e.getFechaNacimiento();

	String fechaFormateada = "";

	if (fechaNacimiento != null) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		fechaFormateada = sdf.format(e.getFechaNacimiento());
	}
	out.print("{"
	    + "\"dni\":\"" + e.getDni() + "\","
	    + "\"nombres\":\"" + e.getNombres() + "\","
	    + "\"apellidos\":\"" + e.getApellidos() + "\","
	    + "\"fecha\":\"" + fechaFormateada + "\","
	    + "\"sexo\":\"" + e.getSexo() + "\","
	    + "\"nombreApoderado\":\"" + nombreApo + "\","
	    + "\"dniApoderado\":\"" + dniApo + "\","
	    + "\"relacion\":\"" + relacion + "\","
	    + "\"telefono\":\"" + (m != null ? m.getTelefonoApoderado() : "") + "\","
	    + "\"correo\":\"" + (m != null ? m.getCorreoApoderado() : "") + "\","
	    + "\"direccion\":\"" + (m != null ? m.getDireccionApoderado() : "") + "\""
	    + "}"); } 
else {
    out.print("{}");
        }
    }
}
