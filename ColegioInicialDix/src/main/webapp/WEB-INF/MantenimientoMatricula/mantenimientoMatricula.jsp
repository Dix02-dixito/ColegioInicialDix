<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="app.modelos.Matricula"%>
<%@ page import="app.modelos.Estudiante"%>

<%
Estudiante est = (Estudiante) request.getAttribute("estudiante");
Matricula mat = (Matricula) request.getAttribute("matricula");
List<String[]> apoderados = (List<String[]>) request.getAttribute("apoderados");
List<Matricula> lista = (List<Matricula>) request.getAttribute("lista");
List<String[]> niveles = (List<String[]>) request.getAttribute("niveles");
String mensaje = (String) request.getAttribute("mensaje");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mantenimiento Matrícula</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/Contenido/estilos/inicio.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/Contenido/estilos/matricula.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css">
<link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;600;700&family=Outfit:wght@400;600&display=swap" rel="stylesheet">


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

<h2 class="title">Mantenimiento Matrícula</h2>

<!-- ALERTA -->
<% if(mensaje!=null){ %>
<div class="alert"><%=mensaje%></div>
<% } %>

<!-- BUSCAR -->
<div class="card hover">

<form method="post">

<input name="dni" placeholder="Ingrese DNI"
value="<%=est!=null?est.getDni():""%>">

<button name="accion" value="buscar" class="btn">
Buscar
</button>

</form>

</div>

<!-- INFO ESTUDIANTE -->
<div class="card soft">

<b>Estudiante:</b>
<%= est!=null ? est.getNombres()+" "+est.getApellidos() : "-" %>

</div>

<!-- INFO MATRICULA -->
<div class="card soft">

<p><b>Apoderado:</b> <%= mat!=null ? mat.getNombreApoderado() : "-" %></p>
<p><b>Relación:</b> <%= mat!=null ? mat.getRelacion() : "-" %></p>
<p><b>Nivel:</b> <%= mat!=null ? mat.getNombreNivel() : "-" %></p>

</div>

<!-- FORM -->
<div class="card">

<form method="post">

<input type="hidden" name="idMatricula"
value="<%=mat!=null?mat.getIdMatricula():""%>">

<label>Apoderado</label>
<select name="idApoderado">

<option value="">Seleccione</option>

<%
if(apoderados!=null){
for(String[] a:apoderados){
String sel = (mat!=null && String.valueOf(mat.getIdApoderado()).equals(a[0]))?"selected":"";
%>
<option value="<%=a[0]%>" <%=sel%>><%=a[1]%> - <%=a[2]%></option>
<% }} %>

</select>

<label>Nivel</label>
<select name="idNivel">

<option value="">Seleccione</option>

<%
if(niveles!=null){
for(String[] n:niveles){
String sel = (mat!=null && String.valueOf(mat.getIdNivel()).equals(n[0]))?"selected":"";
%>
<option value="<%=n[0]%>" <%=sel%>><%=n[1]%></option>
<% }} %>

</select>

<label>Observación</label>
<input name="observacion"
value="<%=mat!=null?mat.getObservacion():""%>">

<label>Estado</label>
<select name="estado">

<option <%=mat!=null && "ACTIVO".equals(mat.getEstado())?"selected":""%>>ACTIVO</option>
<option <%=mat!=null && "INACTIVO".equals(mat.getEstado())?"selected":""%>>INACTIVO</option>

</select>

<button name="accion" value="editar" class="btn">
Actualizar
</button>

</form>

</div>

<!-- TABLA -->
<div class="card table-card">

<table>

<tr>
<th>ID</th>
<th>Estudiante</th>
<th>Apoderado</th>
<th>Relación</th>
<th>Nivel</th>
<th>Estado</th>
</tr>

<%
if(lista!=null){
for(Matricula m:lista){
%>

<tr onclick="seleccionarFila(
'<%=m.getIdMatricula()%>',
'<%=m.getIdNivel()%>',
'<%=m.getIdApoderado()%>',
'<%=m.getEstado()%>',
'<%=m.getObservacion()%>')">

<td><%=m.getIdMatricula()%></td>
<td><%=m.getNombresEstudiante()%></td>
<td><%=m.getNombreApoderado()%></td>
<td><%=m.getRelacion()%></td>
<td><%=m.getNombreNivel()%></td>
<td><%=m.getEstado()%></td>

</tr>

<% }} %>

</table>

</div>

</div>

</body>
</html>