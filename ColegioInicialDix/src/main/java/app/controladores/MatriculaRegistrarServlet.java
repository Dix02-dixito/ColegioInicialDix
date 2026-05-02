package app.controladores;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import app.data.ApoderadoDAO;
import app.data.EstudianteDAO;
import app.data.MatriculaDAO;
import app.modelos.Apoderado;
import app.modelos.Estudiante;
import app.modelos.Matricula;
import app.data.EstudianteApoderadoDAO;
import app.modelos.EstudianteApoderado;



@WebServlet("/MatriculaRegistrar")
public class MatriculaRegistrarServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private MatriculaDAO matriculaDAO;
    private EstudianteDAO estudianteDAO;
    private ApoderadoDAO apoderadoDAO;

    public MatriculaRegistrarServlet() {
        matriculaDAO = new MatriculaDAO();
        estudianteDAO = new EstudianteDAO();
        apoderadoDAO = new ApoderadoDAO();
    }

   
 protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("WEB-INF/Matricula/matriculaRegistrar.jsp")
               .forward(request, response);
    }

   
 protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

      
String dniEstudiante = request.getParameter("txtDniEstudiante");
String dniApoderado = request.getParameter("txtDniApoderado");
String observacion = request.getParameter("txtObservacion");
int idNivel = Integer.parseInt(request.getParameter("txtNivel"));
int anio = Integer.parseInt(request.getParameter("txtAnio"));
String relacion = request.getParameter("txtRelacion");
  
 
        Estudiante estudiante = estudianteDAO.buscarPorDni(dniEstudiante);
        Apoderado apoderado = apoderadoDAO.buscarPorDni(dniApoderado);

        boolean ok = false;
        String mensaje = "";

if (estudiante == null) {
    mensaje = "El estudiante no existe";
} else if (apoderado == null) {
    mensaje = "El apoderado no existe";
} else if (matriculaDAO.existeMatriculaActiva(estudiante.getIdEstudiante())) {
    mensaje = "El estudiante ya tiene matrícula activa";
} else {
	 // generar matricula
	Matricula m = new Matricula();
	m.setIdEstudiante(estudiante.getIdEstudiante());
	m.setIdApoderado(apoderado.getIdApoderado());
	m.setIdNivel(idNivel);
	m.setAnio(anio);
	m.setEstado("ACTIVO"); 
	m.setObservacion(observacion);

	
    ok = matriculaDAO.generar(m);

    if (ok) {

        // crear relacion
        EstudianteApoderadoDAO daoEA = new EstudianteApoderadoDAO();

        if (!daoEA.existeRelacion(estudiante.getIdEstudiante(), apoderado.getIdApoderado())) {

            EstudianteApoderado ea = new EstudianteApoderado();
            ea.setIdEstudiante(estudiante.getIdEstudiante());
            ea.setIdApoderado(apoderado.getIdApoderado());
            ea.setRelacion(relacion);

            daoEA.insertarRelacion(ea);
        }
        

        mensaje = "Matrícula registrada correctamente";
        request.setAttribute("idG", m.getIdMatricula());

    } else {
        mensaje = "Error al registrar matrícula";
    }
}
        request.setAttribute("ok", ok);
        request.setAttribute("mensaje", mensaje);
        request.setAttribute("dniEstudiante", dniEstudiante);
        request.setAttribute("dniApoderado", dniApoderado);
        request.getRequestDispatcher("WEB-INF/Matricula/matriculaRegistrar.jsp")
                       .forward(request, response);
         
    }
	
}
