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

<link rel="stylesheet"
href="${pageContext.request.contextPath}/Contenido/estilos/editarmatricula.css">

<!-- ALERTAS PRO -->
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>

<style>
.apo-box { margin-top:20px; }
.apo-tabla { width:100%; border-collapse:separate; border-spacing:0 8px; }
.apo-tabla th, .apo-tabla td { padding:10px; }

/* BOTON ELIMINAR BONITO */
.btn-eliminar-icon {
    background:#e74c3c;
    border:none;
    color:white;
    padding:6px 10px;
    border-radius:8px;
    cursor:pointer;
    transition:0.3s;
}

.btn-eliminar-icon:hover {
    background:#c0392b;
    transform:scale(1.1);
}

/* INPUT ERROR */
.input-error {
    border:2px solid #e74c3c;
}

.alerta {
    background:#e74c3c;
    color:white;
    padding:10px;
    border-radius:8px;
    margin-bottom:10px;
}
</style>

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

    <% if(error != null){ %>
        <div class="alerta"><%= error %></div>
    <% } %>

    <!-- 🔍 BUSCAR -->
    <div class="box">
        <form action="MantenimientoApoderado" method="get">
            <input type="hidden" name="accion" value="buscar">

            <div class="row">
                <div class="col">
                    <label>Buscar DNI</label>
                    <input id="buscarDni" type="text" name="dni" maxlength="9" required>
                </div>

                <div class="col">
                    <label>&nbsp;</label>
                    <button class="btn">Buscar</button>
                </div>
            </div>
        </form>
    </div>

    <!-- FORMULARIO -->
    <div class="box">

        <h3><%= editando ? "Ver Apoderado" : "Nuevo Apoderado" %></h3>

        <form action="${pageContext.request.contextPath}/MantenimientoApoderado" method="post">

            <input type="hidden" name="accion" value="<%= editando ? "actualizar" : "guardar" %>">

            <% if (editando) { %>
                <input type="hidden" name="id" value="<%= a.getIdApoderado() %>">
            <% } %>

            <div class="row">

                <div class="col">
                    <label>DNI</label>
                    <input id="dni" name="dni"
                        value="<%= editando ? a.getDni() : "" %>"
                        maxlength="9"
                        <%= editando ? "readonly" : "" %> required>
                </div>

                <div class="col">
                    <label>Teléfono</label>
                    <input id="telefono" name="telefono"
                        value="<%= editando ? a.getTelefono() : "" %>"
                        <%= editando ? "readonly" : "" %>>
                </div>

            </div>

            <div class="row">

                <div class="col">
                    <label>Nombres</label>
                    <input id="nombres" name="nombres"
                        value="<%= editando ? a.getNombres() : "" %>"
                        <%= editando ? "readonly" : "" %> required>
                </div>

                <div class="col">
                    <label>Apellidos</label>
                    <input id="apellidos" name="apellidos"
                        value="<%= editando ? a.getApellidos() : "" %>"
                        <%= editando ? "readonly" : "" %> required>
                </div>

            </div>

            <div class="row">

                <div class="col">
                    <label>Correo</label>
                    <input type="email" name="correo"
                        value="<%= editando ? a.getCorreo() : "" %>"
                        <%= editando ? "readonly" : "" %>>
                </div>

                <div class="col">
                    <label>Sexo</label>
                    <select name="sexo" <%= editando ? "disabled" : "" %>>
                        <option value="M" <%= editando && "M".equals(a.getSexo()) ? "selected" : "" %>>Masculino</option>
                        <option value="F" <%= editando && "F".equals(a.getSexo()) ? "selected" : "" %>>Femenino</option>
                    </select>
                </div>

            </div>

            <label>Dirección</label>
            <input type="text" name="direccion"
                value="<%= editando ? a.getDireccion() : "" %>"
                <%= editando ? "readonly" : "" %>>

            <div class="guardar">

                <button type="submit" class="btn">
                    <%= editando ? "Actualizar" : "Guardar" %>
                </button>

                <% if(editando){ %>
                    <button type="button" class="btn" onclick="habilitarEdicion()">
                        Editar
                    </button>
                <% } %>

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
                <th>Correo</th>
                <th></th>
            </tr>

            <% if (lista != null) {
                for (Apoderado ap : lista) { %>

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

            <% } } %>

        </table>

    </div>

</div>

<script>

/* 🔓 EDITAR */
function habilitarEdicion() {
    document.querySelectorAll("input, select").forEach(e => {
        e.removeAttribute("readonly");
        e.removeAttribute("disabled");
    });
}

/* 🗑 ELIMINAR */
function eliminar(id){
    Swal.fire({
        title:'¿Eliminar?',
        text:'Esta acción no se puede deshacer',
        icon:'warning',
        showCancelButton:true,
        confirmButtonColor:'#3498db',
        cancelButtonColor:'#e74c3c',
        confirmButtonText:'Eliminar'
    }).then((r)=>{
        if(r.isConfirmed){
            window.location='MantenimientoApoderado?accion=eliminar&id='+id;
        }
    });
}

/* ⚡ VALIDACIONES EN VIVO */

document.getElementById("dni").addEventListener("input", e=>{
    e.target.value = e.target.value.replace(/[^0-9]/g,'');
});

document.getElementById("buscarDni").addEventListener("input", e=>{
    e.target.value = e.target.value.replace(/[^0-9]/g,'');
});

document.getElementById("telefono").addEventListener("input", e=>{
    e.target.value = e.target.value.replace(/[^0-9]/g,'');
});

document.getElementById("nombres").addEventListener("input", e=>{
    e.target.value = e.target.value.replace(/[^a-zA-Záéíóúñ ]/g,'');
});

document.getElementById("apellidos").addEventListener("input", e=>{
    e.target.value = e.target.value.replace(/[^a-zA-Záéíóúñ ]/g,'');
});

/* 🎉 ALERTA SERVIDOR */
<% if(mensaje != null){ %>
Swal.fire({
    icon:'success',
    title:'<%= mensaje %>',
    confirmButtonColor:'#3498db'
});
<% } %>

</script>

</body>
</html>