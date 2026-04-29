package app.controladores;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import app.data.UsuarioDAO;
import app.modelos.Usuario;

@WebServlet("/Login")
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String usuario = request.getParameter("usuario");
        String contrasena = request.getParameter("contrasena");

        Usuario u = usuarioDAO.validar(usuario, contrasena);

        if (u != null) {

            HttpSession session = request.getSession();

            // guardas el objeto completo 
            session.setAttribute("usuario", u);

            // SIEMPRE UN SOLO INICIO
            response.sendRedirect(request.getContextPath() + "/Inicio");

        } else {

            request.setAttribute("error", "Usuario o contraseña incorrectos");
            request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        }
    }
}