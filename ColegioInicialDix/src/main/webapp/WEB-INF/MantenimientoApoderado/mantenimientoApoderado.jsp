<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*, app.modelos.Apoderado" %>

<%
    List<Apoderado> lista = (List<Apoderado>) request.getAttribute("lista");
    Apoderado a = (Apoderado) request.getAttribute("apoderado");
    boolean editando = (a != null);
    String error = (String) request.getAttribute("error");
    String mensaje = (String) request.getAttribute("mensaje");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mantenimiento Apoderado</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/Contenido/estilos/apoderado.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/Contenido/estilos/inicio.css">
<link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;600;700&family=Outfit:wght@400;600&display=swap" rel="stylesheet">

<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css">

</head>

<body>

<!-- ================= SIDEBAR ================= -->
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
<!-- ================= MAIN ================= -->
<div class="main">

    <!-- HEADER -->
    <div class="top">
        <h2>Mantenimiento de Apoderado</h2>
    </div>

    <!-- ALERTA -->
    <% if(error != null){ %>
        <div class="alerta"><%= error %></div>
    <% } %>

    <!-- ================= BUSCAR ================= -->
    <div class="box">

        <h3>Buscar Apoderado</h3>

        <form action="${pageContext.request.contextPath}/Mantenimiento/Apoderado" method="get">

            <input type="hidden" name="accion" value="buscar">

            <div class="row">

                <div class="col">
                    <label>DNI</label>
                    <input type="text" name="dni" maxlength="9" required>
                </div>

                <div class="col">
                    <label>&nbsp;</label>
                    <button class="btn" type="submit">Buscar</button>
                </div>

            </div>

        </form>
    </div>

    <!-- ================= FORM ================= -->
    <div class="box">

        <h3>
            <%= editando ? "Ver Apoderado" : "Nuevo Apoderado" %>
        </h3>

        <form action="${pageContext.request.contextPath}/Mantenimiento/Apoderado" method="post">

            <input type="hidden" name="accion"
                   value="<%= editando ? "actualizar" : "guardar" %>">

            <% if (editando) { %>
                <input type="hidden" name="id"
                       value="<%= a.getIdApoderado() %>">
            <% } %>

            <!-- FILA 1 -->
            <div class="row">

                <div class="col">
                    <label>DNI</label>
                    <input name="dni"
                           value="<%= editando ? a.getDni() : "" %>"
                           maxlength="9"
                           <%= editando ? "readonly" : "" %>
                           required>
                </div>

                <div class="col">
                    <label>Teléfono</label>
                    <input name="telefono"
                           value="<%= editando ? a.getTelefono() : "" %>"
                           <%= editando ? "readonly" : "" %>>
                </div>

            </div>

            <!-- FILA 2 -->
            <div class="row">

                <div class="col">
                    <label>Nombres</label>
                    <input name="nombres"
                           value="<%= editando ? a.getNombres() : "" %>"
                           <%= editando ? "readonly" : "" %>
                           required>
                </div>

                <div class="col">
                    <label>Apellidos</label>
                    <input name="apellidos"
                           value="<%= editando ? a.getApellidos() : "" %>"
                           <%= editando ? "readonly" : "" %>
                           required>
                </div>

            </div>

            <!-- FILA 3 -->
            <div class="row">

                <div class="col">
                    <label>Correo</label>
                    <input type="email"
                           name="correo"
                           value="<%= editando ? a.getCorreo() : "" %>"
                           <%= editando ? "readonly" : "" %>>
                </div>

                <div class="col">
                    <label>Sexo</label>
                    <select name="sexo" <%= editando ? "disabled" : "" %>>

                        <option value="M"
                            <%= editando && "M".equals(a.getSexo()) ? "selected" : "" %>>
                            Masculino
                        </option>

                        <option value="F"
                            <%= editando && "F".equals(a.getSexo()) ? "selected" : "" %>>
                            Femenino
                        </option>

                    </select>
                </div>

            </div>

            <!-- DIRECCIÓN -->
            <label>Dirección</label>
            <input name="direccion"
                   value="<%= editando ? a.getDireccion() : "" %>"
                   <%= editando ? "readonly" : "" %>>

            <!-- BOTONES -->
            <div class="guardar">

                <button type="submit" class="btn">
                    <%= editando ? "Actualizar" : "Guardar" %>
                </button>

                <% if(editando){ %>
                    <button type="button" class="btn"
                            onclick="habilitarEdicion()">
                        Editar
                    </button>
                <% } %>

            </div>

        </form>
    </div>

    <!-- ================= LISTA ================= -->
    <div class="apo-box">

        <h3>Lista de Apoderados</h3>

        <table class="apo-tabla">

            <tr>
                <th>ID</th>
                <th>DNI</th>
                <th>Nombre</th>
                <th>Teléfono</th>
                <th>Correo</th>
                <th>Acción</th>
            </tr>

            <% if(lista != null){
                for(Apoderado ap : lista){ %>

            <tr>
                <td><%= ap.getIdApoderado() %></td>
                <td><%= ap.getDni() %></td>
                <td><%= ap.getNombres() %> <%= ap.getApellidos() %></td>
                <td><%= ap.getTelefono() %></td>
                <td><%= ap.getCorreo() %></td>

                <td>
                    <button class="btn-eliminar-icon"
                            onclick="eliminar(<%= ap.getIdApoderado() %>)">
                        🗑
                    </button>
                </td>
            </tr>

            <% }} %>

        </table>

    </div>

</div>
<script>

function eliminar(id){
    Swal.fire({
        title:'¿Eliminar?',
        icon:'warning',
        showCancelButton:true,
        confirmButtonText:'Sí'
    }).then((r)=>{
        if(r.isConfirmed){
            window.location='${pageContext.request.contextPath}/Mantenimiento/Apoderado?accion=eliminar&id='+id;
        }
    });
}

function habilitarEdicion(){
    document.querySelectorAll("input, select").forEach(e=>{
        e.removeAttribute("readonly");
        e.removeAttribute("disabled");
    });
}

</script>

</body>
</html>