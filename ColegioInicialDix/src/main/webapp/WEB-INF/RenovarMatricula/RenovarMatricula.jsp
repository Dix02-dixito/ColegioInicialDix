<%@ page language="java"
contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<%@ page import="app.modelos.Matricula"%>

<%
Matricula matricula = (Matricula) request.getAttribute("matricula");
String mensaje = (String) request.getAttribute("mensaje");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Renovar Matricula</title>

<link rel="stylesheet"
href="${pageContext.request.contextPath}/Contenido/estilos/renovarMatricula.css">

</head>
<body>

<div class="sidebar">

    <h2>Dix Academy</h2>

    <a href="${pageContext.request.contextPath}/InicioServlet">Inicio</a>

    <a href="${pageContext.request.contextPath}/MatriculaServlet">
        Generar Matrícula
    </a>

    <a href="${pageContext.request.contextPath}/MatriculaEditarServlet">
        Editar Matrícula
    </a>

    <a href="${pageContext.request.contextPath}/MatriculaRenovarServlet">
        Renovar Matrícula
    </a>

    <a href="${pageContext.request.contextPath}/MatriculaEliminarServlet">
        Cancelar Matrícula
    </a>

    <a href="#">Apoderados</a>
    <a href="#">Estudiantes</a>
    <a href="#">Actividad</a>

</div>
<div class="main">

    <div class="top">
        <h2>Renovar Matricula</h2>
    </div>

    <!-- buscar -->
    <div class="box">

        <h3>Buscar Matricula Inactiva</h3>

        <form action="${pageContext.request.contextPath}/MatriculaRenovarServlet"
              method="post">

            <input type="hidden" name="accion" value="buscarMatricula">

            <div class="row">

                <div class="col">
                    <label>DNI</label>
                    <input type="text"
                           name="dni"
                           placeholder="Ingrese DNI del estudiante"
                           maxlength="8"
                           pattern="[0-9]{8}"
                           required
                           oninput="this.value=this.value.replace(/[^0-9]/g,'')">
                </div>

                <div class="col">
                    <label>&nbsp;</label>
                    <button class="btn" type="submit">Buscar</button>
                </div>

            </div>

        </form>

    </div>


<% if(matricula != null){ %>

    <!-- estudiante -->
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


    <!-- Reactivar -->
    <form action="${pageContext.request.contextPath}/MatriculaRenovarServlet"
          method="post">

        <input type="hidden" name="accion" value="reactivarMatricula">

        <input type="hidden" name="dni" value="<%=matricula.getDni()%>">

        <input type="hidden"
               name="idMatricula"
               value="<%=matricula.getIdMatricula()%>">


        <div class="box">

            <h3>Acción</h3>

            <div class="row">

                <div class="col">

                    <label>Estado</label>

                    <select name="estado" required>
                        <option value="">Seleccione</option>
                        <option value="Activo">Activo</option>
                    </select>

                </div>

            </div>

            <label>Motivo de la Reactivacion de la matricula</label>
            <textarea name="observacion"></textarea>

        </div>

        <div class="guardar">

            <button type="submit"
                    class="btn"
                    onclick="return confirm('¿Seguro de reactivar la matricula?')">
                Guardar Cambios
            </button>

        </div>

    </form>

<% } %>


<% if(mensaje!=null){ %>
<div class="box"><%=mensaje%></div>
<% } %>

</div>

</body>
</html>