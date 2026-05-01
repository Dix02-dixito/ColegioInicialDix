package app.controladores;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import app.data.ActividadDAO;
import app.modelos.Usuario;

@WebServlet("/Logout")
public class LogoutServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession sesion = request.getSession(false);

        if (sesion != null) {

            Usuario u = (Usuario) sesion.getAttribute("usuario");

            if (u != null) {
                ActividadDAO dao = new ActividadDAO();

                dao.registrar(
                    u.getId(),
                    "LOGOUT",
                    "Cerró sesión"
                );
            }

            sesion.invalidate();
        }

        response.sendRedirect(request.getContextPath() + "/login");
    }
}