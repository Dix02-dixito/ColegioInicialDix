<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="app.modelos.Estudiante"%>
<%@ page import="app.modelos.Usuario"%>

<%
Usuario u = (Usuario) session.getAttribute("usuario");
if(u == null){
    response.sendRedirect("login.jsp");
    return;
}

Estudiante est = (Estudiante) request.getAttribute("estudiante");
List<Estudiante> lista = (List<Estudiante>) request.getAttribute("lista");
String mensaje = (String) request.getAttribute("mensaje");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mantenimiento Estudiantes</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="${pageContext.request.contextPath}/Contenido/estilos/inicio.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/Contenido/estilos/estudiante.css">

</head>

<body>

<!-- SIDEBAR -->
<div class="sidebar">
    <h2>Dix Academy</h2>

    <a href="${pageContext.request.contextPath}/Inicio">INICIO</a>
    <a href="#">MATRICULA</a>
    <a href="${pageContext.request.contextPath}/Mantenimiento/Apoderado">APODERADO</a>
    <a href="${pageContext.request.contextPath}/Mantenimiento/Estudiante">ESTUDIANTE</a>
    <a href="${pageContext.request.contextPath}/actividad">ACTIVIDAD</a>

    <hr>
    <a href="${pageContext.request.contextPath}/Logout" style="color:red;">
    Cerrar sesión
</a>
</div>

<!-- MAIN -->
<div class="main">

<h2>👨‍🎓 Mantenimiento de Estudiantes</h2>
<p>Usuario: <b><%= u.getNombre() %></b> | Rol: <%= u.getRol() %></p>

<form action="${pageContext.request.contextPath}/Mantenimiento/Estudiante" method="post">

<% if(est!=null){ %>
<input type="hidden" name="idEstudiante" value="<%=est.getIdEstudiante()%>">
<% } %>

<!-- ================= DATOS DEL ESTUDIANTE ================= -->
<div class="box">

<h3>Datos del Estudiante</h3>

<div class="row">

<div class="col">
<label>DNI</label>
<div class="input-group">
<input name="dni" maxlength="8"
oninput="soloNumeros(this)"
value="<%=est!=null?est.getDni():""%>">

<button class="btn" name="accion" value="buscar">Buscar</button>
</div>
</div>

<div class="col">
<label>Sexo</label>
<select name="sexo" class="editable">
<option value="M" <%=est!=null && "M".equals(est.getSexo())?"selected":""%>>M</option>
<option value="F" <%=est!=null && "F".equals(est.getSexo())?"selected":""%>>F</option>
</select>
</div>

</div>

<div class="row">

<div class="col">
<label>Nombres</label>
<input name="nombres" class="editable"
value="<%=est!=null?est.getNombres():""%>" readonly>
</div>

<div class="col">
<label>Apellidos</label>
<input name="apellidos" class="editable"
value="<%=est!=null?est.getApellidos():""%>" readonly>
</div>

</div>

<div class="row">
<div class="col">
<label>Fecha</label>
<input type="date" name="fecha" class="editable"
value="<%= est!=null && est.getFechaNacimiento()!=null ? est.getFechaNacimiento().toString() : "" %>" readonly>
</div>
</div>

</div>

<!-- ================= APODERADO ACTUAL ================= -->
<div class="box">

<h3>Apoderado del Estudiante</h3>

<div class="row">

<div class="col">
<label>Nombre</label>
<input value="<%=est!=null?est.getNombreApoderado():""%>" readonly>
</div>

<div class="col">
<label>Relación</label>
<input value="<%=est!=null?est.getRelacion():""%>" readonly>
</div>

</div>

</div>

<!-- ================= ASIGNAR APODERADO ================= -->
<div class="box">

<h3>Asignar Apoderado</h3>

<div class="row">

<div class="col">
<label>DNI Apoderado</label>
<input type="text" name="dniApoderado" maxlength="8"
oninput="soloNumeros(this)">
</div>

<div class="col">
<label>Relación</label>
<select name="relacion">
<option value="">Seleccione</option>
<option>Padre</option>
<option>Madre</option>
<option>Tutor</option>
<option>Abuelo</option>
</select>
</div>

</div>

</div>

<!-- ================= ACCIONES ================= -->
<div class="box actions">

<button type="button" class="btn-success" onclick="nuevo()">Nuevo</button>

<button name="accion" value="registrar" class="btn">
Registrar
</button>

<button type="button" class="btn" onclick="habilitarCampos()">
Editar
</button>

<button name="accion" value="editar" class="btn">
Actualizar
</button>

<button type="button" class="btn-warning" onclick="limpiar()">
Limpiar
</button>

<button name="accion" value="eliminar"
class="btn-danger"
onclick="return confirmarEliminar()">
Eliminar
</button>

</div>

</form>

<!-- ================= LISTA ================= -->
<div class="box">

<h3>Lista de Estudiantes</h3>

<table>

<tr>
<th>DNI</th>
<th>Nombres</th>
<th>Acción</th>
</tr>

<% if(lista!=null){
for(Estudiante e: lista){ %>

<tr>

<td><%=e.getDni()%></td>
<td><%=e.getNombres()%></td>

<td>
<form method="post" action="${pageContext.request.contextPath}/Mantenimiento/Estudiante">
<input type="hidden" name="accion" value="buscar">
<input type="hidden" name="dni" value="<%=e.getDni()%>">
<button class="btn">Seleccionar</button>
</form>
</td>

</tr>

<% }} %>

</table>

</div>

<% if(mensaje!=null){ %>
<div class="mensaje"><%=mensaje%></div>
<% } %>

</div>

<footer>
    Dix Academy Inicial © 2026
</footer>

<script src="${pageContext.request.contextPath}/Contenido/scripts/estudiante.js"></script>

</body>
</html>