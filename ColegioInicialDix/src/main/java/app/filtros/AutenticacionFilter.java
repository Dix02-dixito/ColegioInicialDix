package app.filtros;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;

@WebFilter("/*")
public class AutenticacionFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String ruta = req.getRequestURI().substring(req.getContextPath().length());

        HttpSession sesion = req.getSession(false);
        boolean logueado = (sesion != null && sesion.getAttribute("usuario") != null);

        // rutas públicas
        boolean publico =
                ruta.equals("") ||
                ruta.equals("/Login") ||
                ruta.startsWith("/Contenido") ||
                ruta.startsWith("/Multimedia");

        if (!logueado && !publico) {
            res.sendRedirect(req.getContextPath() + "/Login");
            return;
        }

        chain.doFilter(request, response);
    }
}