<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*, app.modelos.Apoderado" %>

<%
    List<Apoderado> lista = (List<Apoderado>) request.getAttribute("lista");
    Apoderado a = (Apoderado) request.getAttribute("apoderado");
    boolean editando = (a != null);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mantenimiento Apoderado</title>

<link rel="stylesheet"
href="${pageContext.request.contextPath}/Contenido/estilos/editarmatricula.css">

</head>

<body>


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


<div class="main">

    <div class="top">
        <h2>Mantenimiento de Apoderado</h2>
    </div>

   
    <div class="box">

        <h3><%= editando ? "Editar Apoderado" : "Nuevo Apoderado" %></h3>

        <form action="${pageContext.request.contextPath}/MantenimientoApoderado" method="post">

            <input type="hidden" name="accion" value="<%= editando ? "actualizar" : "guardar" %>">

            <% if (editando) { %>
                <input type="hidden" name="id" value="<%= a.getIdApoderado() %>">
            <% } %>

            <div class="row">

                <div class="col">
                    <label>DNI</label>
                    <input type="text" name="dni"
                           value="<%= editando ? a.getDni() : "" %>" required>
                </div>

                <div class="col">
                    <label>Teléfono</label>
                    <input type="text" name="telefono"
                           value="<%= editando ? a.getTelefono() : "" %>">
                </div>

            </div>

            <div class="row">

                <div class="col">
                    <label>Nombres</label>
                    <input type="text" name="nombres"
                           value="<%= editando ? a.getNombres() : "" %>" required>
                </div>

                <div class="col">
                    <label>Apellidos</label>
                    <input type="text" name="apellidos"
                           value="<%= editando ? a.getApellidos() : "" %>" required>
                </div>

            </div>

            <div class="row">

                <div class="col">
                    <label>Correo</label>
                    <input type="email" name="correo"
                           value="<%= editando ? a.getCorreo() : "" %>">
                </div>

                <div class="col">
                    <label>Sexo</label>
                    <select name="sexo">
                        <option value="M" <%= editando && "M".equals(a.getSexo()) ? "selected" : "" %>>Masculino</option>
                        <option value="F" <%= editando && "F".equals(a.getSexo()) ? "selected" : "" %>>Femenino</option>
                    </select>
                </div>

            </div>

            <label>Dirección</label>
            <input type="text" name="direccion"
                   value="<%= editando ? a.getDireccion() : "" %>">

            <div class="guardar">
                <button type="submit" class="btn">
                    <%= editando ? "Actualizar" : "Guardar" %>
                </button>
            </div>

        </form>

    </div> 

    <!-- LISTA -->
<div class="apo-box">

    <h3>Lista de Apoderados</h3>

    <table class="apo-tabla">

        <tr>
            <th>ID</th>
            <th>DNI</th>
            <th>Nombre</th>
            <th>Teléfono</th>
            <th>Acciones</th>
        </tr>

        <% if (lista != null) {
            for (Apoderado ap : lista) { %>

        <tr>
            <td><%= ap.getIdApoderado() %></td>
            <td><%= ap.getDni() %></td>
            <td><%= ap.getNombres() %> <%= ap.getApellidos() %></td>
            <td><%= ap.getTelefono() %></td>

            <td class="apo-acciones">
                <a class="apo-btn-editar"
                   href="MantenimientoApoderado?accion=editar&id=<%= ap.getIdApoderado() %>">
                    Editar
                </a>

                <a class="apo-btn-eliminar"
                   href="MantenimientoApoderado?accion=eliminar&id=<%= ap.getIdApoderado() %>"
                   onclick="return confirm('¿Eliminar?')">
                    Eliminar
                </a>
            </td>
        </tr>

        <% } } %>

    </table>

</div> </div>



</body>
</html>