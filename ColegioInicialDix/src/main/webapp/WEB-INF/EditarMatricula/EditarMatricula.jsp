<%@ page language="java"
contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<%@ page import="app.modelos.Matricula"%>
<%@ page import="app.modelos.Apoderado"%>

<%
Matricula matricula = (Matricula) request.getAttribute("matricula");
Apoderado apo = (Apoderado) request.getAttribute("apoderadoBuscado");
String mensaje = (String) request.getAttribute("mensaje");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Editar Matrícula</title>

<link rel="stylesheet"
href="${pageContext.request.contextPath}/Contenido/estilos/editarmatricula.css">

</head>
<body>

<div class="sidebar">
    <h2>Dix Academy</h2>
    <a href="#">Inicio</a>
    <a href="#">Generar Matrícula</a>
    <a href="#">Editar Matrícula</a>
    <a href="#">Renovar Matrícula</a>
    <a href="#">Cancelar Matrícula</a>
    <a href="#">Apoderados</a>
    <a href="#">Estudiantes</a>
    <a href="#">Actividad</a>
</div>

<div class="main">

    <div class="top">
        <h2>Editar Matrícula</h2>
    </div>

    <!-- buscar matricula -->
    <div class="box">

        <h3>Buscar Matrícula</h3>

        <form action="${pageContext.request.contextPath}/MatriculaEditarServlet"
              method="post">

            <input type="hidden" name="accion" value="buscarMatricula">

            <div class="row">

                <div class="col">
                    <label>DNI</label>
                    <input type="text" name="dni" placeholder="Ingrese DNI">
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

        <button type="button"
                class="btn2"
                onclick="abrirModal()">
            Cambiar Apoderado
        </button>

    </div>


    <!-- modal -->
    <div id="modalApoderado" class="modal">

    <div class="modal-contenido">

        <span class="cerrar" onclick="cerrarModal()">×</span>

        <h3>Buscar Apoderado</h3>

        <!-- formulario -->
        <form action="${pageContext.request.contextPath}/MatriculaEditarServlet"
              method="post">

            <input type="hidden" name="accion" value="buscarApoderado">

            <input type="hidden" name="idMatricula"
                   value="<%=matricula.getIdMatricula()%>">

            <input type="hidden" name="idEstudiante"
                   value="<%=matricula.getIdEstudiante()%>">

            <input type="hidden" name="dniAlumno"
                   value="<%=matricula.getDni()%>">

            <label>DNI Apoderado</label>

            <input type="text"
                   name="dniApoderado"
                   placeholder="Ingrese DNI">

            <button type="submit" class="btn">
                Buscar
            </button>

        </form>


        <% if(apo != null){ %>

        <div class="apoderado-info">

            <h4>Resultado</h4>

            <p><b>Nombre:</b> <%=apo.getNombres()%> <%=apo.getApellidos()%></p>
            <p><b>DNI:</b> <%=apo.getDni()%></p>
            <p><b>Teléfono:</b> <%=apo.getTelefono()%></p>
            <p><b>Dirección:</b> <%=apo.getDireccion()%></p>
            <p><b>Correo:</b> <%=apo.getCorreo()%></p>
            <p><b>Relación:</b> <%=apo.getRelacion()%></p>

        </div>

        <!-- vincular -->
        <form action="${pageContext.request.contextPath}/MatriculaEditarServlet"
              method="post">

            <input type="hidden" name="accion" value="cambiarApoderado">

            <input type="hidden" name="idMatricula"
                   value="<%=matricula.getIdMatricula()%>">

            <input type="hidden" name="idEstudiante"
                   value="<%=matricula.getIdEstudiante()%>">

            <input type="hidden" name="dniAlumno"
                   value="<%=matricula.getDni()%>">

            <input type="hidden" name="idNuevoApoderado"
                   value="<%=apo.getIdApoderado()%>">

            <button type="submit" class="btn2">
                Vincular Apoderado
            </button>

        </form>

        <% } %>

    </div>
</div>


    <!-- editar -->
    <form action="${pageContext.request.contextPath}/MatriculaEditarServlet"
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

                <div class="col">

                    <label>Nivel</label>

                    <select name="idNivel">
                        <option value="1" <%=matricula.getIdNivel()==1?"selected":""%>>Inicial 1</option>
                        <option value="2" <%=matricula.getIdNivel()==2?"selected":""%>>Inicial 2</option>
                        <option value="3" <%=matricula.getIdNivel()==3?"selected":""%>>Inicial 3</option>
                    </select>

                </div>

            </div>

            <label>Observación</label>
            <textarea name="observacion"><%=matricula.getObservacion()==null?"":matricula.getObservacion()%></textarea>

        </div>

        <div class="guardar">
            <button type="submit" class="btn">Guardar Cambios</button>
        </div>

    </form>

<% } %>


<% if(mensaje!=null){ %>
<div class="box"><%=mensaje%></div>
<% } %>

</div>

<script src="${pageContext.request.contextPath}/Contenido/scripts/editarmatricula.js"></script>

<% if(apo!=null){ %>
<script>
window.onload = function(){
    abrirModal();
}
</script>
<% } %>

</body>
</html>