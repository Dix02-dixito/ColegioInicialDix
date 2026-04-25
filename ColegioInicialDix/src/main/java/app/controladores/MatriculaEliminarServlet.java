package app.controladores;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import app.data.MatriculaDAO;
import app.modelos.Matricula;

@WebServlet("/MatriculaEliminarServlet")
public class MatriculaEliminarServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private MatriculaDAO dao = new MatriculaDAO();


    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher(
                "/WEB-INF/EliminarMatricula/EliminarMatricula.jsp"
        ).forward(request, response);
    }


    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");

        if ("buscarMatricula".equals(accion)) {
            buscarMatricula(request);
        }
        
        else if ("cancelarMatricula".equals(accion)) {
        	cancelarMatricula(request);
        }

        request.getRequestDispatcher(
        		"/WEB-INF/EliminarMatricula/EliminarMatricula.jsp"
        ).forward(request, response);
    }


   


	// buscar matrícula
    private void buscarMatricula(HttpServletRequest request) {

        String dni = request.getParameter("dni");

        Matricula m = dao.buscarPorDniOCodigo(dni, null);

        if (m != null) {
            request.setAttribute("matricula", m);
        } else {
            request.setAttribute("mensaje", "No se encontró matrícula");
        }
    }
    
    //EliminarMatricula
    
    private void cancelarMatricula(HttpServletRequest request) {
    	
    	int idMatricula = request.get
    		
		
		
   	}


   
}