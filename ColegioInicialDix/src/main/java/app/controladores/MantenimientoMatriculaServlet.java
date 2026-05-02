package app.controladores;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import app.data.MatriculaDAO;
import app.data.EstudianteDAO;
import app.modelos.Matricula;
import app.modelos.Estudiante;

@WebServlet("/Mantenimiento/Matricula")
public class MantenimientoMatriculaServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private MatriculaDAO dao = new MatriculaDAO();
    private EstudianteDAO estudianteDAO = new EstudianteDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        cargarDatos(request);
        forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");

        if ("buscar".equals(accion)) buscar(request);
        else if ("editar".equals(accion)) editar(request);
        else if ("cancelar".equals(accion)) cambiarEstado(request, "CANCELADO");
        else if ("inactivar".equals(accion)) cambiarEstado(request, "INACTIVO");

        cargarDatos(request);
        forward(request, response);
    }

    private void buscar(HttpServletRequest request) {

        String dni = request.getParameter("dni");

        Estudiante e = estudianteDAO.buscarPorDni(dni);

        if (e != null) {

            request.setAttribute("estudiante", e);

            Matricula m = dao.buscarMatriculaActivaPorDni(dni);
            request.setAttribute("matricula", m);

            request.setAttribute("apoderados",
                dao.listarApoderadosPorEstudiante(e.getIdEstudiante()));

        } else {
            request.setAttribute("mensaje", "Estudiante no encontrado");
        }
    }

    private void editar(HttpServletRequest request) {

        try {

            Matricula m = new Matricula();

            m.setIdMatricula(Integer.parseInt(request.getParameter("idMatricula")));
            m.setIdNivel(Integer.parseInt(request.getParameter("idNivel")));
            m.setIdApoderado(Integer.parseInt(request.getParameter("idApoderado")));
            m.setEstado(request.getParameter("estado"));
            m.setObservacion(request.getParameter("observacion"));

            dao.editarMatricula(m);

            request.setAttribute("mensaje", "Actualizado correctamente");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void cambiarEstado(HttpServletRequest request, String estado) {

        try {

            int id = Integer.parseInt(request.getParameter("idMatricula"));

            dao.cambiarEstado(id, estado);

            request.setAttribute("mensaje", "Estado cambiado correctamente");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void cargarDatos(HttpServletRequest request) {

        request.setAttribute("lista", dao.listarMatriculas());
        request.setAttribute("niveles", dao.listarNiveles());
    }

    private void forward(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher(
            "/WEB-INF/MantenimientoMatricula/mantenimientoMatricula.jsp"
        ).forward(request, response);
    }
}