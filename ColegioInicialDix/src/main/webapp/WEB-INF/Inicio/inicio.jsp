<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="app.modelos.Usuario" %>

<%
    Usuario u = (Usuario) session.getAttribute("usuario");
    if(u == null){
        response.sendRedirect("login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>Inicio</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">

    <style>
        body {
            margin: 0;
            font-family: Arial;
            display: flex;
            background: #0b0f0c;
            color: white;
        }

        .sidebar {
            width: 220px;
            height: 100vh;
            background: #111;
            padding: 20px;
        }

        .sidebar h2 {
            color: #00ff88;
            margin-bottom: 20px;
        }

        .sidebar a {
            display: block;
            color: #ccc;
            padding: 10px;
            text-decoration: none;
            border-radius: 5px;
            margin-bottom: 5px;
        }

        .sidebar a:hover {
            background: #00ff88;
            color: black;
        }

        .main {
            flex: 1;
            padding: 30px;
        }

        .perfil {
            background: rgba(0,0,0,0.8);
            padding: 20px;
            border-radius: 10px;
            max-width: 500px;
        }
    </style>
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
    <a href="#">Actividad</a>

    <hr>

    <a href="Logout" style="color:red;">Cerrar sesión</a>
</div>

<div class="main">

    <h2>👋 Bienvenido <%= u.getNombre() %></h2>
    <p>Rol: <%= u.getRol() %></p>

    <div class="perfil mt-4">

        <h4>Datos del Usuario</h4>
        <hr>

        <p><b>ID:</b> <%= u.getId() %></p>
        <p><b>Código:</b> <%= u.getCodigo() %></p>
        <p><b>Correo:</b> <%= u.getUsuario() %></p>
        <p><b>Rol:</b> <%= u.getRol() %></p>

    </div>
    <div class="mt-4">
    <%
        String rol = u.getRol();

        if("Admin".equals(rol)){
    %>
        <div class="alert alert-success">Panel ADMIN - acceso total</div>
    <%
        } else if("Encargado".equals(rol)){
    %>
        <div class="alert alert-warning">Panel ENCARGADO - gestión de matrículas</div>
    <%
        } else {
    %>
        <div class="alert alert-info">Panel ASISTENTE - soporte</div>
    <%
        }
    %>
    </div>

</div>
<footer class="mt-auto">
    Dix Academy Inicial / Todos los derechos reservados © 2026
</footer>

</body>
</html>