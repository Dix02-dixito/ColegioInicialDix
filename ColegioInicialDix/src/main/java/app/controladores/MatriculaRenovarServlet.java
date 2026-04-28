package app.controladores;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import app.data.MatriculaDAO;
import app.modelos.Matricula;

@WebServlet("/MatriculaRenovarServlet")
public class MatriculaRenovarServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private MatriculaDAO dao = new MatriculaDAO();


    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher(
                "/WEB-INF/RenovarMatricula/RenovarMatricula.jsp"
        ).forward(request, response);
    }


    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");

        if ("buscarMatricula".equals(accion)) {
        	buscarMatriculaInactiva(request);
        }
        else if ("renovarMatricula".equals(accion)) {
        	renovarMatricula(request);
        }

        request.getRequestDispatcher(
        		"/WEB-INF/RenovarMatricula/RenovarMatricula.jsp"
        ).forward(request, response);
    }


    // buscar matricula inactiva
    private void buscarMatriculaInactiva(HttpServletRequest request) {

        String dni = request.getParameter("dni");

        if (dni == null || !dni.matches("\\d{8}")) {
            request.setAttribute("mensaje", "DNI inválido");
            return;
        }

        Matricula m = dao.buscarMatriculaInactiva(dni);

        if (m != null) {
            request.setAttribute("matricula", m);
        } else {
            request.setAttribute("mensaje", "No se encontro matricula inactvia con ese dni");
        }
    }


    // ActivarMatricula
    private void renovarMatricula(HttpServletRequest request) {

        try {

            String idMatriculaStr = request.getParameter("idMatricula");
            String dni = request.getParameter("dni");

            if (idMatriculaStr == null) {
                request.setAttribute("mensaje", "Datos incompletos");
                return;
            }

            int idMatricula = Integer.parseInt(idMatriculaStr);

            boolean ok = dao.renovarMatricula(idMatricula);

            request.setAttribute(
                "mensaje",
                ok ? "Matrícula renovada correctamente"
                   : "No se pudo renovar"
            );

            // ya no debería aparecer porque ya es ACTIVA
            if (dni != null) {
                request.setAttribute(
                    "matricula",
                    dao.buscarMatriculaInactiva(dni)
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("mensaje", "Error al renovar");
        }
    }
}