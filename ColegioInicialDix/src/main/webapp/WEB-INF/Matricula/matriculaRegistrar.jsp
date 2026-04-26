<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
Boolean ok = (Boolean) request.getAttribute("ok");
String mensaje = (String) request.getAttribute("mensaje");
Object idG = request.getAttribute("idG");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registrar Matrícula</title>

<link rel="stylesheet"
href="${pageContext.request.contextPath}/Contenido/estilos/editarmatricula.css">

</head>

<body>

<!-- SIDEBAR -->
<div class="sidebar">
    <h2>Dix Academy</h2>
    <a href="#">Inicio</a>
    <a href="${pageContext.request.contextPath}/MatriculaRegistrar">Generar Matrícula</a>
    <a href="${pageContext.request.contextPath}/MatriculaEditarServlet">Editar Matrícula</a>
    <a href="#">Renovar Matrícula</a>
    <a href="#">Cancelar Matrícula</a>
    <a href="${pageContext.request.contextPath}/MantenimientoApoderado">Apoderados</a>
    <a href="#">Estudiantes</a>
    <a href="#">Actividad</a>
</div>

<!-- MAIN -->
<div class="main">

    <div class="top">
        <h2>Generar Matrícula</h2>
    </div>

    <!-- FORMULARIO -->
    <form action="${pageContext.request.contextPath}/MatriculaRegistrar" method="post">

        <div class="box">

            <h3>Datos del Estudiante</h3>

            <div class="row">

                <div class="col">
                    <label>DNI Estudiante</label>
                    <input type="text" name="txtDniEstudiante" required pattern="[0-9]{8}">
                </div>

                <div class="col">
                    <label>DNI Apoderado</label>
                    <input type="text" name="txtDniApoderado" required pattern="[0-9]{8}">
                </div>

            </div>

        </div>

        <!-- DATOS MATRÍCULA -->
        <div class="box">

            <h3>Datos de Matrícula</h3>

            <div class="row">

                <div class="col">
                    <label>Nivel</label>
                    <select name="txtNivel">
                        <option value="1">Inicial 1</option>
                        <option value="2">Inicial 2</option>
                        <option value="3">Inicial 3</option>
                    </select>
                </div>

                <div class="col">
                    <label>Año</label>
                    <input type="number" name="txtAnio" value="2026">
                </div>

            </div>

            <div class="row">

                <div class="col">
                    <label>Relación</label>
                    <input type="text" name="txtRelacion" placeholder="Ej: Padre, Madre, Tío">
                </div>

            </div>

            <label>Observación</label>
            <textarea name="txtObservacion"></textarea>

        </div>

        <div class="guardar">
            <button type="submit" class="btn">Registrar Matrícula</button>
        </div>

    </form>

    <!-- MENSAJE -->
    <% if (mensaje != null) { %>
        <div class="box">
            <%= mensaje %>
            <% if (ok != null && ok && idG != null) { %>
                <br>ID generado: <%= idG %>
            <% } %>
        </div>
    <% } %>

</div>

</body>
</html>