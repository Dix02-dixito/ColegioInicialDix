package app.controladores;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import app.data.MatriculaDAO;
import app.modelos.Apoderado;
import app.modelos.Matricula;

@WebServlet("/MatriculaEditarServlet")
public class MatriculaEditarServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private MatriculaDAO dao = new MatriculaDAO();


    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher(
                "/WEB-INF/EditarMatricula/EditarMatricula.jsp"
        ).forward(request, response);
    }


    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");

        if ("buscarMatricula".equals(accion)) {
            buscarMatricula(request);
        }

        else if ("editar".equals(accion)) {
            editarMatricula(request);
        }

        else if ("buscarApoderado".equals(accion)) {
            buscarApoderado(request);
        }

        else if ("cambiarApoderado".equals(accion)) {
            cambiarApoderado(request);
        }

        request.getRequestDispatcher(
                "/WEB-INF/EditarMatricula/EditarMatricula.jsp"
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


    // editar matrícula
    private void editarMatricula(HttpServletRequest request) {

        try {

            Matricula m = new Matricula();

            m.setIdMatricula(
                Integer.parseInt(request.getParameter("idMatricula"))
            );

            m.setIdNivel(
                Integer.parseInt(request.getParameter("idNivel"))
            );

            m.setIdApoderado(
                Integer.parseInt(request.getParameter("idApoderado"))
            );

            m.setEstado(request.getParameter("estado"));
            m.setObservacion(request.getParameter("observacion"));

            boolean ok = dao.editarMatricula(m);

            request.setAttribute(
                "mensaje",
                ok ? "Matrícula actualizada" : "No se pudo actualizar"
            );

            String dni = request.getParameter("dni");

            if (dni != null) {
                request.setAttribute(
                    "matricula",
                    dao.buscarPorDniOCodigo(dni, null)
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("mensaje", "Error al editar");
        }
    }


    // buscar apoderado
    private void buscarApoderado(HttpServletRequest request) {

        try {

            String dniApoderado = request.getParameter("dniApoderado");
            String dniAlumno = request.getParameter("dniAlumno");
            String idEstudianteStr = request.getParameter("idEstudiante");

            if (dniAlumno != null) {
                request.setAttribute(
                    "matricula",
                    dao.buscarPorDniOCodigo(dniAlumno, null)
                );
            }

            if (idEstudianteStr == null || idEstudianteStr.isEmpty()) {
                request.setAttribute("mensaje", "Error: estudiante no identificado");
                return;
            }

            int idEstudiante = Integer.parseInt(idEstudianteStr);

            Apoderado apo =
                dao.buscarApoderadoPorDni(dniApoderado, idEstudiante);

            if (apo != null) {
                request.setAttribute("apoderadoBuscado", apo);
            } else {
                request.setAttribute(
                    "mensaje",
                    "El apoderado no tiene relación con el estudiante"
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("mensaje", "Error al buscar apoderado");
        }
    }


    // cambiar apoderado
    private void cambiarApoderado(HttpServletRequest request) {

        try {

            int idMatricula =
                Integer.parseInt(request.getParameter("idMatricula"));

            int idEstudiante =
                Integer.parseInt(request.getParameter("idEstudiante"));

            int idNuevoApoderado =
                Integer.parseInt(request.getParameter("idNuevoApoderado"));

            String dniAlumno = request.getParameter("dniAlumno");


            // validar relación antes de cambiar
            boolean existe =
                dao.existeRelacion(idEstudiante, idNuevoApoderado);

            if (!existe) {

                request.setAttribute(
                    "mensaje",
                    "No se puede vincular: no existe relación con el estudiante"
                );

            } else {

                boolean ok =
                    dao.cambiarApoderado(
                        idMatricula,
                        idEstudiante,
                        idNuevoApoderado
                    );

                request.setAttribute(
                    "mensaje",
                    ok ? "Apoderado cambiado correctamente"
                       : "No se pudo cambiar"
                );
            }


            if (dniAlumno != null) {
                request.setAttribute(
                    "matricula",
                    dao.buscarPorDniOCodigo(dniAlumno, null)
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("mensaje", "Error al cambiar apoderado");
        }
    }
}