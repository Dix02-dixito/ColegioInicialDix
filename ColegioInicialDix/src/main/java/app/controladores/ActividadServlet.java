package app.controladores;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import app.data.ActividadDAO;
import app.modelos.Actividad;

@WebServlet("/actividad")
public class ActividadServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ActividadDAO dao = new ActividadDAO();

        List<Actividad> lista = dao.listar();

        //seguridad por si DAO devuelve null
        if (lista == null) {
            lista = new java.util.ArrayList<>();
        }

        //limpio para JSP
        request.setAttribute("listaActividad", lista);

        request.setAttribute("totalActividades", lista.size());

        request.getRequestDispatcher("/WEB-INF/ActividadSistema/actividad.jsp").forward(request, response);
    }
}