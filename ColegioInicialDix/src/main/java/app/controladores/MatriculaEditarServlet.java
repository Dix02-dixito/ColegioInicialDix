package app.controladores;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import app.data.MatriculaDAO;
import app.modelos.Matricula;

@WebServlet("/MatriculaEditarServlet")
public class MatriculaEditarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// 🔹 Mostrar formulario (GET)
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("/WEB-INF/EditarMatricula/EditarMatricula.jsp")
		       .forward(request, response);
	}

	// 🔹 Procesar formulario (POST)
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String mensaje = "";

		try {
			int idMatricula = Integer.parseInt(request.getParameter("idMatricula"));
			int idNivel = Integer.parseInt(request.getParameter("idNivel"));
			int idApoderado = Integer.parseInt(request.getParameter("idApoderado"));
			String estado = request.getParameter("estado");
			String observacion = request.getParameter("observacion");

			Matricula m = new Matricula();
			m.setIdMatricula(idMatricula);
			m.setIdNivel(idNivel);
			m.setIdApoderado(idApoderado);
			m.setEstado(estado);
			m.setObservacion(observacion);

			MatriculaDAO dao = new MatriculaDAO();
			boolean resultado = dao.editarMatricula(m);

			if (resultado) {
				mensaje = "✅ Matrícula editada correctamente";
			} else {
				mensaje = "❌ No se pudo editar la matrícula";
			}

		} catch (Exception e) {
			e.printStackTrace();
			mensaje = "❌ Error en el servidor";
		}

		// 🔹 Enviar mensaje al JSP
		request.setAttribute("mensaje", mensaje);

		// 🔹 Volver al JSP
		request.getRequestDispatcher("/WEB-INF/EditarMatricula/EditarMatricula.jsp")
		       .forward(request, response);
	}
}