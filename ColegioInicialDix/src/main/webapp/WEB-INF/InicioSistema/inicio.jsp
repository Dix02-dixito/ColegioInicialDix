<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="app.modelos.Usuario" %>

<%
    Usuario u = (Usuario) session.getAttribute("usuario");
    if(u == null){
        response.sendRedirect("login.jsp");
        return;
    }

    //
    int totalEstudiantes = 5;
    int totalMatriculas = 5;
    int activas = 3;
%>

<!DOCTYPE html>
<html>
<head>
    <title>Inicio</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;600;700&family=Outfit:wght@400;600&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css">
    <link rel="stylesheet" href="Contenido/estilos/inicio.css">
</head>

<body>


    <!-- SIDEBAR -->
<div class="sidebar">

    <h2>Dix Academy</h2>
    <a href="${pageContext.request.contextPath}/Inicio">
    <i class="bi bi-house-door-fill"></i> INICIO
</a>

<a href="${pageContext.request.contextPath}/Registrar/Matricula">
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

    <h2>👋 Bienvenido <%= u.getNombre() %></h2>
    <p class="rol">Rol: <%= u.getRol() %></p>

    <!-- KPIs -->
    <div class="kpi-grid">

        <div class="kpi">
            <h3><%= totalEstudiantes %></h3>
            <p>👨‍🎓 Estudiantes</p>
        </div>

        <div class="kpi">
            <h3><%= totalMatriculas %></h3>
            <p>📄 Matrículas</p>
        </div>

        <div class="kpi">
            <h3><%= activas %></h3>
            <p>✅ Activas</p>
        </div>

    </div>

    <!-- PERFIL -->
    <div class="perfil mt-4">
        <h4>👤 Datos del Usuario</h4>
        <hr>

        <div class="perfil-grid">
            <p><b>ID:</b> <%= u.getId() %></p>
            <p><b>Código:</b> <%= u.getCodigo() %></p>
            <p><b>Correo:</b> <%= u.getCorreo() %></p>
            <p><b>Rol:</b> <%= u.getRol() %></p>
        </div>
    </div>

    <!-- PANEL POR ROL -->
    <div class="mt-4">
    <%
        String rol = u.getRol();

        if("Admin".equals(rol)){
    %>
        <div class="alert-custom success">Panel ADMIN - acceso total</div>
    <%
        } else if("Encargado".equals(rol)){
    %>
        <div class="alert-custom warning">Panel ENCARGADO - gestión de matrículas</div>
    <%
        } else {
    %>
        <div class="alert-custom info">Panel ASISTENTE - soporte</div>
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
