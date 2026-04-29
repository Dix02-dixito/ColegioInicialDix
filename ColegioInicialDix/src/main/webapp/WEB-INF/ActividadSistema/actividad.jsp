<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="app.modelos.Usuario" %>
<%@ page import="app.modelos.Actividad" %>

<%
    Usuario u = (Usuario) session.getAttribute("usuario");
    if (u == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    //LISTA QUE VIENE DEL SERVLET
    List<Actividad> lista = (List<Actividad>) request.getAttribute("listaActividad");

    if (lista == null) {
        lista = new ArrayList<>();
    }

    // 🔥 seguro contra null del atributo
    Integer totalAct = (Integer) request.getAttribute("totalActividades");
    int totalActividades = (totalAct != null) ? totalAct : 0;

    int totalEstudiantes = 5;
    int matriculasActivas = 3;
    int matriculasInactivas = 1;
    int matriculasCanceladas = 1;
%>

<!DOCTYPE html>
<html>
<head>
    <title>Actividades</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="Contenido/estilos/actividad.css">
</head>

<body>

<div class="sidebar">
    <h2>Dix Academy</h2>
    <a href="inicio.jsp">Inicio</a>
    <a href="${pageContext.request.contextPath}/MatriculaRegistrar">Generar Matrícula</a>
    <a href="${pageContext.request.contextPath}/MatriculaEditarServlet">Editar Matrícula</a>
    <a href="#">Renovar Matrícula</a>
    <a href="#">Cancelar Matrícula</a>
    <a href="${pageContext.request.contextPath}/MantenimientoApoderado">Apoderados</a>
    <a href="#">Estudiantes</a>
    <a href="${pageContext.request.contextPath}/actividad">Actividad</a>

    <hr>
    <a href="Logout" style="color:red;">Cerrar sesión</a>
</div>

<div class="main">

    <h2>📊 Panel General</h2>
    <p>Bienvenido <b><%= u.getNombre() %></b></p>

    <!-- KPI CARDS -->
    <div class="kpi-grid">

        <div class="kpi">
            <h3><%= totalEstudiantes %></h3>
            <p>👨‍🎓 Estudiantes</p>
        </div>

        <div class="kpi">
            <h3><%= matriculasActivas %></h3>
            <p>✅ Matrículas Activas</p>
        </div>

        <div class="kpi">
            <h3><%= matriculasInactivas %></h3>
            <p>⏸️ Inactivas</p>
        </div>

        <div class="kpi">
            <h3><%= matriculasCanceladas %></h3>
            <p>❌ Canceladas</p>
        </div>

        <div class="kpi">
            <h3><%= totalActividades %></h3>
            <p>📌 Actividades</p>
        </div>

    </div>

    <!-- ACTIVIDAD -->
    <div class="tabla-container mt-4">

        <h4>📋 Actividad reciente</h4>

        <table class="tabla-actividad">
            <tbody>

            <%
                if (!lista.isEmpty()) {
                    for (Actividad act : lista) {
            %>

                <tr>
                    <td class="usuario"><%= act.getUsuario() %></td>

                    <td>
                        <span class="accion"><%= act.getAccion() %></span>
                        <br>
                        <span class="detalle"><%= act.getDetalle() %></span>
                    </td>

                    <td class="fecha">
                        <%= act.getFecha() %><br>
                        <small><%= act.getHora() %></small>
                    </td>
                </tr>

            <%
                    }
                }
            %>

            </tbody>
        </table>

    </div>

</div>

<footer>
    Dix Academy Inicial © 2026
</footer>

</body>
</html>
