package app.controladores;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import app.data.EstudianteDAO;
import app.data.ActividadDAO;      // 🔥 LOGS
import app.modelos.Estudiante;
import app.modelos.Usuario;        // 🔥 LOGS

@WebServlet("/Mantenimiento/Estudiante")
public class MantenimientoEstudianteServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private EstudianteDAO dao = new EstudianteDAO();

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("lista", dao.listarEstudiantes());

        request.getRequestDispatcher(
                "/WEB-INF/Estudiante/mantenimientoEstudiante.jsp"
        ).forward(request, response);
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");

        if ("buscar".equals(accion)) {
            buscar(request);
        }
        else if ("registrar".equals(accion)) {
            registrar(request);
        }
        else if ("editar".equals(accion)) {
            editar(request);
        }
        else if ("eliminar".equals(accion)) {
            eliminar(request);
        }

        request.setAttribute("lista", dao.listarEstudiantes());

        request.getRequestDispatcher(
                "/WEB-INF/Estudiante/mantenimientoEstudiante.jsp"
        ).forward(request, response);
    }

    private void buscar(HttpServletRequest request) {

        String dni = request.getParameter("dni");

        if (dni == null || !dni.matches("\\d{8}")) {
            request.setAttribute("mensaje", "DNI invalido");
            return;
        }

        Estudiante e = dao.buscarPorDni(dni);

        if (e != null) {
            request.setAttribute("estudiante", e);
        } else {
            request.setAttribute("mensaje", "No se encontro estudiante");
        }
    }

    private void registrar(HttpServletRequest request) {

        try {

            String dni = request.getParameter("dni");

            if (dao.existeDni(dni)) {
                request.setAttribute("mensaje", "El DNI ya existe");
                return;
            }

            // 🔥 VALIDAR NOMBRES ANTES
            String nombres = request.getParameter("nombres");
            String apellidos = request.getParameter("apellidos");

            if (!nombres.matches("[A-Za-zÁÉÍÓÚáéíóúñÑ ]+") ||
                !apellidos.matches("[A-Za-zÁÉÍÓÚáéíóúñÑ ]+")) {

                request.setAttribute("mensaje", "Solo se permiten letras");
                return;
            }

            Estudiante e = obtenerDatos(request);

            int idEstudiante = dao.registrarYRetornarId(e);

            if (idEstudiante == 0) {
                request.setAttribute("mensaje", "Error al registrar");
                return;
            }

            String dniApoderado = request.getParameter("dniApoderado");
            String relacion = request.getParameter("relacion");

            if (dniApoderado != null && !dniApoderado.isEmpty()) {

                int idApoderado = dao.obtenerIdApoderadoPorDni(dniApoderado);

                if (idApoderado == 0) {
                    request.setAttribute("mensaje", "Apoderado no existe");
                    return;
                }

                boolean okRel = dao.insertarRelacion(idEstudiante, idApoderado, relacion);

                if (!okRel) {
                    request.setAttribute("mensaje", "Error al guardar relacion");
                    return;
                }
            }

            request.setAttribute("mensaje", "Estudiante registrado correctamente");

            // 🔥 LOG
            Usuario u = (Usuario) request.getSession().getAttribute("usuario");
            if (u != null) {
                new ActividadDAO().registrar(
                        u.getId(),
                        "CREAR ESTUDIANTE",
                        "Registró estudiante DNI " + e.getDni()
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("mensaje", "Error en registro");
        }
    }

    private void editar(HttpServletRequest request) {

        try {

            // 🔥 VALIDAR ANTES
            String nombres = request.getParameter("nombres");
            String apellidos = request.getParameter("apellidos");

            if (!nombres.matches("[A-Za-zÁÉÍÓÚáéíóúñÑ ]+") ||
                !apellidos.matches("[A-Za-zÁÉÍÓÚáéíóúñÑ ]+")) {

                request.setAttribute("mensaje", "Solo se permiten letras");
                return;
            }

            Estudiante e = obtenerDatos(request);

            String idStr = request.getParameter("idEstudiante");

            if (idStr == null || idStr.isEmpty()) {
                request.setAttribute("mensaje", "Falta id del estudiante");
                return;
            }

            e.setIdEstudiante(Integer.parseInt(idStr));

            boolean ok = dao.editarEstudiante(e);

            request.setAttribute("mensaje",
                    ok ? "Estudiante actualizado"
                       : "Error al actualizar");

            request.setAttribute("estudiante", e);

            // 🔥 LOG
            if (ok) {
                Usuario u = (Usuario) request.getSession().getAttribute("usuario");

                if (u != null) {
                    new ActividadDAO().registrar(
                            u.getId(),
                            "EDITAR ESTUDIANTE",
                            "Actualizó estudiante ID " + e.getIdEstudiante()
                    );
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute("mensaje", "Error en edicion");
        }
    }

    private void eliminar(HttpServletRequest request) {

        try {

            String idStr = request.getParameter("idEstudiante");

            if (idStr == null || idStr.isEmpty()) {
                request.setAttribute("mensaje", "Datos incompletos");
                return;
            }

            int idEstudiante = Integer.parseInt(idStr);

            if (dao.tieneMatricula(idEstudiante)) {
                request.setAttribute("mensaje", "No se puede eliminar");
                return;
            }

            boolean ok = dao.eliminarEstudiante(idEstudiante);

            request.setAttribute("mensaje",
                    ok ? "Estudiante eliminado"
                       : "No se pudo eliminar");

            // 🔥 LOG
            if (ok) {
                Usuario u = (Usuario) request.getSession().getAttribute("usuario");

                if (u != null) {
                    new ActividadDAO().registrar(
                            u.getId(),
                            "ELIMINAR ESTUDIANTE",
                            "Eliminó estudiante ID " + idEstudiante
                    );
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("mensaje", "Error al eliminar");
        }
    }

    private Estudiante obtenerDatos(HttpServletRequest request) {

        Estudiante e = new Estudiante();

        e.setDni(request.getParameter("dni"));
        e.setNombres(request.getParameter("nombres"));
        e.setApellidos(request.getParameter("apellidos"));
        e.setSexo(request.getParameter("sexo"));

        String fechaStr = request.getParameter("fecha");

        if (fechaStr != null && !fechaStr.isEmpty()) {
            e.setFechaNacimiento(Date.valueOf(fechaStr));
        }

        return e;
    }
}