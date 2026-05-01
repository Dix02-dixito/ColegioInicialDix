package app.controladores;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import app.data.ApoderadoDAO;
import app.modelos.Apoderado;

@WebServlet("/MantenimientoApoderado")
public class MantenimientoApoderadoServlet extends HttpServlet {

private static final long serialVersionUID = 1L;
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

case "buscar":
    String dni = request.getParameter("dni");

    Apoderado ap = dao.buscarPorDni(dni);

    if (ap == null) {
        request.setAttribute("error", "❌ Apoderado no encontrado");
    } else {
        request.setAttribute("apoderado", ap);
    }

    request.setAttribute("lista", dao.listarApoderado());
    request.getRequestDispatcher("WEB-INF/MantenimientoApoderado/mantenimientoApoderado.jsp")
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
	//validar dni duplicado
	if (dao.buscarPorDni(a.getDni()) != null) {
	
	    request.setAttribute("error", "⚠️ El DNI ya existe");
	    request.setAttribute("lista", dao.listarApoderado());
	
	    request.getRequestDispatcher("WEB-INF/MantenimientoApoderado/mantenimientoApoderado.jsp")
	           .forward(request, response);
	    return;
	}
	
	boolean ok = dao.guardar(a);
	
	if (ok) {
	    request.setAttribute("mensaje", "Apoderado registrado correctamente");
	} else {
	    request.setAttribute("error", "Error al registrar apoderado");
	}
	
	request.setAttribute("lista", dao.listarApoderado());
	
	request.getRequestDispatcher("WEB-INF/MantenimientoApoderado/mantenimientoApoderado.jsp")
	           .forward(request, response);
	
	} else if (accion.equals("actualizar")) {
	
	int id = Integer.parseInt(request.getParameter("id"));
	a.setIdApoderado(id);
	
	boolean ok = dao.actualizarApoderado(a);
	
	if (ok) {
	    request.setAttribute("mensaje", "Datos actualizados correctamente");
	} else {
	    request.setAttribute("error", "Error al actualizar los datos");
	}
	
	request.setAttribute("lista", dao.listarApoderado());
	
	request.getRequestDispatcher("WEB-INF/MantenimientoApoderado/mantenimientoApoderado.jsp")
	           .forward(request, response);
}}}