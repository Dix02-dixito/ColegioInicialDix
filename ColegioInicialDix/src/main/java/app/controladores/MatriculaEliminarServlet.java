package app.controladores;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import app.data.MatriculaDAO;
import app.modelos.Matricula;

@WebServlet("/Matricula/Eliminar")
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

        if (dni == null || !dni.matches("\\d{8}")) {
            request.setAttribute("mensaje", "DNI inválido");
            return;
        }

        Matricula m = dao.buscarMatriculaActiva(dni);

        if (m != null) {
            request.setAttribute("matricula", m);
        } else {
            request.setAttribute("mensaje", "No se encontró matrícula ACTIVA");
        }
    }


    // cancelar / inactivar matrícula
    private void cancelarMatricula(HttpServletRequest request) {

        try {

            String idMatriculaStr = request.getParameter("idMatricula");
            String estado = request.getParameter("estado");
            String observacion = request.getParameter("observacion");
            String dni = request.getParameter("dni");

            if (idMatriculaStr == null || estado == null) {
                request.setAttribute("mensaje", "Datos incompletos");
                return;
            }

            int idMatricula = Integer.parseInt(idMatriculaStr);

            boolean ok = false;

            if ("INACTIVO".equals(estado)) {

                ok = dao.editarEstadoMatricula(
                        idMatricula,
                        "INACTIVO",
                        observacion
                );

            } else if ("CANCELADO".equals(estado)) {

                ok = dao.eliminarMatricula(idMatricula); // elimina de verdad

            } else {

                request.setAttribute("mensaje", "Estado no permitido");
                return;
            }

            request.setAttribute(
                "mensaje",
                ok ? "Operación realizada correctamente"
                   : "No se pudo realizar la operación"
            );

            if (dni != null) {
                request.setAttribute(
                    "matricula",
                    dao.buscarMatriculaActiva(dni)
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("mensaje", "Error en la operación");
        }
    }
}