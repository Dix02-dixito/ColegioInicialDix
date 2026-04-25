<%@ page language="java"
contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<%@ page import="app.modelos.Matricula"%>
<%@ page import="app.modelos.Apoderado"%>

<%
Matricula matricula = (Matricula) request.getAttribute("matricula");
String mensaje = (String) request.getAttribute("mensaje");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Eliminar Matricula</title>

<link rel="stylesheet"
href="${pageContext.request.contextPath}/Contenido/estilos/eliminarMatricula.css">

</head>
<body>

<div class="sidebar">
    <h2>Dix Academy</h2>
    <a href="#">Inicio</a>
    <a href="#">Generar Matricula</a>
    <a href="#">Editar Matrícula</a>
    <a href="#">Renovar Matrícula</a>
    <a href="#">Cancelar Matrícula</a>
    <a href="#">Apoderados</a>
    <a href="#">Estudiantes</a>
    <a href="#">Actividad</a>
</div>

<div class="main">

    <div class="top">
        <h2>Eliminar Matricula</h2>
    </div>

    <!-- buscar matricula -->
    <div class="box">

        <h3>Buscar Matrícula</h3>

        <form action="${pageContext.request.contextPath}/MatriculaEliminarServlet"
              method="post">

            <input type="hidden" name="accion" value="buscarMatricula">

            <div class="row">

                <div class="col">
                    <label>DNI</label>
                    <input type="text" name="dni" placeholder="Ingrese DNI del estudiante">
                </div>

                <div class="col">
                    <label>&nbsp;</label>
                    <button class="btn" type="submit">Buscar</button>
                </div>

            </div>

        </form>

    </div>


<% if(matricula != null){ %>

    <!-- datos estudiante -->
    <div class="box">

        <h3>Datos del Estudiante</h3>

        <div class="row">

            <div class="col">
                <label>Nombres</label>
                <input value="<%=matricula.getNombresEstudiante()%>" disabled>
            </div>

            <div class="col">
                <label>Apellidos</label>
                <input value="<%=matricula.getApellidosEstudiante()%>" disabled>
            </div>

        </div>

        <div class="row">

            <div class="col">
                <label>DNI</label>
                <input value="<%=matricula.getDni()%>" disabled>
            </div>

            <div class="col">
                <label>Año</label>
                <input value="<%=matricula.getAnio()%>" disabled>
            </div>

        </div>

    </div>


    <!-- apoderado -->
    <div class="box">

        <h3>Datos del Apoderado</h3>

        <div class="row">

            <div class="col">
                <label>Nombre</label>
                <input value="<%=matricula.getNombreApoderado()%>" disabled>
            </div>

            <div class="col">
                <label>Relación</label>
                <input value="<%=matricula.getRelacion()%>" disabled>
            </div>

        </div>

    </div>


    <!-- editar -->
    <form action="${pageContext.request.contextPath}/MatriculaEliminarServlet"
          method="post">

        <input type="hidden" name="accion" value="editar">

        <input type="hidden" name="dni" value="<%=matricula.getDni()%>">

        <input type="hidden"
               name="idMatricula"
               value="<%=matricula.getIdMatricula()%>">

        <input type="hidden"
               name="idApoderado"
               value="<%=matricula.getIdApoderado()%>">


        <div class="box">

            <h3>Datos de Matrícula</h3>

            <div class="row">

                <div class="col">

                    <label>Estado</label>

                    <select name="estado">
                        <option value="ACTIVO" <%=matricula.getEstado().equals("ACTIVO")?"selected":""%>>ACTIVO</option>
                        <option value="INACTIVO" <%=matricula.getEstado().equals("INACTIVO")?"selected":""%>>INACTIVO</option>
                        <option value="CANCELADO" <%=matricula.getEstado().equals("CANCELADO")?"selected":""%>>CANCELADO</option>
                    </select>

                </div>

            </div>

            <label>Observación</label>
            <textarea name="observacion"><%=matricula.getObservacion()==null?"":matricula.getObservacion()%></textarea>

        </div>

        <div class="guardar">
            <button type="submit" class="btn">Eliminar Matricula</button>
        </div>

    </form>

<% } %>


<% if(mensaje!=null){ %>
<div class="box"><%=mensaje%></div>
<% } %>

</div>

</body>
</html>