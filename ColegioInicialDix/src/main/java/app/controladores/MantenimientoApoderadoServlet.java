package app.controladores;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import app.data.ApoderadoDAO;
import app.data.EstudianteDAO;
import app.data.MatriculaDAO;
import app.modelos.Apoderado;

@WebServlet("/MantenimientoApoderado")
public class MantenimientoApoderadoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public MantenimientoApoderadoServlet() {
        super();
        new MatriculaDAO();
        new EstudianteDAO();
        new ApoderadoDAO();
    }

    ApoderadoDAO dao = new ApoderadoDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");

        if (accion == null) accion = "listar";

        switch (accion) {

            case "listar":
                List<Apoderado> lista = dao.listarApoderado();
                request.setAttribute("lista", lista);
                request.getRequestDispatcher("WEB-INF/MantenimientoApoderado/mantenimientoApoderado.jsp")
                       .forward(request, response);
                break;

            case "nuevo":
                request.getRequestDispatcher("WEB-INF/MantenimientoApoderado/mantenimientoApoderado.jsp")
                       .forward(request, response);
                break;

            case "editar":
                int dni = Integer.parseInt(request.getParameter("id"));
                Apoderado a = dao.buscarPorDni(String.valueOf(dni));
                request.setAttribute("apoderado", a);
                request.getRequestDispatcher("/WEB-INF/EditarMatricula/EditarMatricula.jsp")
                       .forward(request, response);
                break;

            case "eliminar":
                int idE = Integer.parseInt(request.getParameter("id"));
                dao.eliminarApoderado(idE);
                response.sendRedirect("MantenimientoApoderado?accion=listar");
                break;
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");

        Apoderado a = new Apoderado();

        a.setDni(request.getParameter("dni"));
        a.setNombres(request.getParameter("nombres"));
        a.setApellidos(request.getParameter("apellidos"));
        a.setDireccion(request.getParameter("direccion"));
        a.setCorreo(request.getParameter("correo"));
        a.setTelefono(request.getParameter("telefono"));
        a.setSexo(request.getParameter("sexo"));
        

        if (accion.equals("guardar")) {
            dao.guardar(a);

        } else if (accion.equals("actualizar")) {
            a.setIdApoderado(Integer.parseInt(request.getParameter("dni")));
            dao.actualizarApoderado(a);
        }

        response.sendRedirect("MantenimientoApoderado?accion=listar");
    }
}
