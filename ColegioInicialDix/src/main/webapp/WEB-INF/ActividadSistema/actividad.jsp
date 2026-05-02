<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*" %>
<%@ page import="app.modelos.Usuario" %>
<%@ page import="app.modelos.Actividad" %>

<%
    Usuario u = (Usuario) session.getAttribute("usuario");
    if (u == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    List<Actividad> lista = (List<Actividad>) request.getAttribute("listaActividad");
    if (lista == null) lista = new ArrayList<>();

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
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;600;700&family=Outfit:wght@400;600&display=swap" rel="stylesheet">
    
    <link rel="stylesheet" href="Contenido/estilos/inicio.css">
     <link rel="stylesheet" href="Contenido/estilos/actividad.css">

    <!-- ICONOS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css">
</head>

<body>

<!-- SIDEBAR -->
<div class="sidebar">

    <h2>Dix Academy</h2>
    <a href="${pageContext.request.contextPath}/Inicio">
    <i class="bi bi-house-door-fill"></i> INICIO
</a>

<a href="${pageContext.request.contextPath}//Registrar/Matricula">
    <i class="bi bi-journal-text"></i> REGISTRAR MATRÍCULA
</a>

<a href="${pageContext.request.contextPath}/Mantenimiento/Apoderado">
    <i class="bi bi-people-fill"></i> MANTENIMIENTO APODERADO
</a>

<a href="${pageContext.request.contextPath}/Mantenimiento/Estudiante">
    <i class="bi bi-person-badge-fill"></i> MANTENIMIENTO ESTUDIANTE
</a>
<a href="${pageContext.request.contextPath}/Mantenimiento/Matricula">
    <i class="bi bi-journal-text"></i> MANTENIMIENTO MATRÍCULA
</a>
<a href="${pageContext.request.contextPath}/actividad">
    <i class="bi bi-activity"></i> ACTIVIDAD GENERAL
</a>

<a href="${pageContext.request.contextPath}/Logout" style="color:#f87171;">
    <i class="bi bi-box-arrow-right"></i> CERRAR SESIÓN
</a>
    
</div>


<!-- MAIN -->
<div class="main">

    <h2 class="titulo">Panel de Actividad</h2>
    <p class="rol">Bienvenido <b><%= u.getNombre() %></b></p>
    <!-- KPI -->
    <div class="kpi-grid">

        <div class="kpi">
            <h3><%= totalEstudiantes %></h3>
            <p>Estudiantes</p>
        </div>

        <div class="kpi">
            <h3><%= matriculasActivas %></h3>
            <p>Activas</p>
        </div>

        <div class="kpi">
            <h3><%= matriculasInactivas %></h3>
            <p>Inactivas</p>
        </div>

        <div class="kpi">
            <h3><%= matriculasCanceladas %></h3>
            <p>Canceladas</p>
        </div>

        <div class="kpi total">
   	 <h3><%= totalActividades %></h3>
    	<p>Total Actividades</p>
	</div>
        
    </div>

    <!-- ACTIVIDAD (TARJETAS PRO) -->
<div class="tabla-container">

    <h4>Actividad reciente</h4>

    <div class="tabla-actividad">

    <%
        for (Actividad act : lista) {
    %>

        <div class="fila">

            <!-- USUARIO -->
            <div class="col usuario">
                <i class="bi bi-person-circle"></i>
                <span><%= act.getUsuario() %></span>
            </div>

            <!-- ACCION -->
            <div class="col accion-box">
                <div class="accion">
                    <i class="bi bi-lightning-charge"></i>
                    <span><%= act.getAccion() %></span>
                </div>

                <div class="detalle">
                    <%= act.getDetalle() %>
                </div>
            </div>

            <!-- FECHA -->
            <div class="col fecha">
                <span><%= act.getFecha() %></span>
                <small><%= act.getHora() %></small>
            </div>

        </div>

    <%
        }
    %>

    </div>

</div>
    

<footer>
    Dix Academy Inicial © 2026
</footer>

</body>
</html>

