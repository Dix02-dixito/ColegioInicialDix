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
<title>Mantenimiento Matricula</title>

<!-- CSS -->
<link rel="stylesheet"
href="${pageContext.request.contextPath}/Contenido/estilos/matricula.css">

<!-- JS -->
<script src="${pageContext.request.contextPath}/Contenido/scripts/matricula.js"></script>

</head>

<body>

<div class="container">

<h2>Mantenimiento Matricula</h2>

<!-- ALERTA -->
<% if(mensaje!=null){ %>
<div class="alert"><%=mensaje%></div>
<% } %>

<!-- BUSCAR -->
<div class="card">
<form method="post">

<input name="dni" placeholder="Ingrese DNI"
value="<%=est!=null?est.getDni():""%>">

<button name="accion" value="buscar" class="btn">
Buscar
</button>

</form>
</div>

<!-- ESTUDIANTE -->
<div class="card">
<b>Estudiante:</b><br>
<%= est!=null ? est.getNombres()+" "+est.getApellidos() : "-" %>
</div>

<!-- DATOS ACTUALES -->
<div class="card">

<b>Apoderado actual:</b>
<%= mat!=null ? mat.getNombreApoderado() : "-" %><br>

<b>Relacion:</b>
<%= mat!=null ? mat.getRelacion() : "-" %><br>

<b>Nivel:</b>
<%= mat!=null ? mat.getNombreNivel() : "-" %>

</div>

<!-- FORM EDITAR -->
<div class="card">

<form method="post">

<input type="hidden" id="idMatricula" name="idMatricula"
value="<%=mat!=null?mat.getIdMatricula():""%>">

<!-- APODERADO -->
<label>Apoderado</label>
<select name="idApoderado" id="idApoderado">

<option value="">Seleccione</option>

<%
if(apoderados!=null){
for(String[] a:apoderados){

String selected = "";
if(mat!=null && String.valueOf(mat.getIdApoderado()).equals(a[0])){
    selected = "selected";
}
%>

<option value="<%=a[0]%>" <%=selected%>>
<%=a[1]%> - <%=a[2]%>
</option>

<%
}}
%>

</select>

<!-- NIVEL -->
<label>Nivel</label>
<select name="idNivel" id="idNivel">

<option value="">Seleccione</option>

<%
if(niveles!=null){
for(String[] n:niveles){

String selected = "";
if(mat!=null && String.valueOf(mat.getIdNivel()).equals(n[0])){
    selected = "selected";
}
%>

<option value="<%=n[0]%>" <%=selected%>>
<%=n[1]%>
</option>

<%
}}
%>

</select>

<!-- OBS -->
<label>Observacion</label>
<input name="observacion" id="observacion"
value="<%=mat!=null?mat.getObservacion():""%>">

<!-- ESTADO -->
<label>Estado</label>
<select name="estado" id="estado">

<option <%=mat!=null && "ACTIVO".equals(mat.getEstado())?"selected":""%>>
ACTIVO
</option>

<option <%=mat!=null && "INACTIVO".equals(mat.getEstado())?"selected":""%>>
INACTIVO
</option>


</select>

<!-- BOTONES -->
<button name="accion" value="editar" class="btn">
Actualizar
</button>

<button name="accion" value="inactivar"
onclick="return confirmar('inactivar')"
class="btn-warning">
Inactivar
</button>

<button name="accion" value="cancelar"
onclick="return confirmar('cancelar')"
class="btn-danger">
Cancelar
</button>

</form>

</div>

<!-- TABLA -->
<div class="card">

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
'<%=m.getObservacion()%>'
)">

<td><%=m.getIdMatricula()%></td>
<td><%=m.getNombresEstudiante()%> <%=m.getApellidosEstudiante()%></td>
<td><%=m.getNombreApoderado()%></td>
<td><%=m.getRelacion()%></td>
<td><%=m.getNombreNivel()%></td>
<td><%=m.getEstado()%></td>

</tr>

<%
}}
%>

</table>

</div>

</div>

</body>
</html>