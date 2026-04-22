<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Editar Matrícula</title>

<style>
    body {
        font-family: Arial;
        background: #f4f6f9;
        margin: 0;
        padding: 0;
    }

    .header {
        background: #007bff;
        color: white;
        padding: 15px;
        font-size: 20px;
        font-weight: bold;
    }

    .container {
        width: 90%;
        margin: auto;
        padding: 20px;
    }

    .box {
        background: white;
        padding: 15px;
        margin-top: 15px;
        border-radius: 8px;
        box-shadow: 0 0 8px rgba(0,0,0,0.1);
    }

    .row {
        display: flex;
        gap: 10px;
        flex-wrap: wrap;
    }

    .col {
        flex: 1;
        min-width: 200px;
    }

    label {
        font-weight: bold;
        display: block;
        margin-top: 8px;
    }

    input, select {
        width: 100%;
        padding: 8px;
        margin-top: 5px;
    }

    button {
        padding: 10px;
        border: none;
        cursor: pointer;
        border-radius: 5px;
    }

    .btn-primary {
        background: #007bff;
        color: white;
    }

    .btn-warning {
        background: #ffc107;
    }

    .btn-success {
        background: #28a745;
        color: white;
        width: 100%;
        margin-top: 15px;
    }

    .section-title {
        font-weight: bold;
        margin-bottom: 10px;
        color: #333;
    }

</style>

</head>

<body>

<!-- 🔵 HEADER -->
<div class="header">
    Editar Matrícula
</div>

<div class="container">

    <!-- 🔍 FILTRO -->
    <div class="box">
        <div class="section-title">Buscar Matrícula</div>

        <div class="row">
            <div class="col">
                <label>Año Académico</label>
                <select>
                    <option>2024</option>
                    <option>2025</option>
                    <option>2026</option>
                </select>
            </div>

            <div class="col">
                <label>Buscar por DNI o Código</label>
                <input type="text" placeholder="Ingrese DNI o código">
            </div>

            <div class="col">
                <label>&nbsp;</label>
                <button class="btn-primary" style="width:100%;">Buscar</button>
            </div>
        </div>
    </div>

    <!-- 👨‍🎓 ESTUDIANTE -->
    <div class="box">
        <div class="section-title">Datos del Estudiante</div>

        <div class="row">
            <div class="col">
                <label>Nombres</label>
                <input type="text" disabled>
            </div>

            <div class="col">
                <label>Apellidos</label>
                <input type="text" disabled>
            </div>

            <div class="col">
                <label>DNI</label>
                <input type="text" disabled>
            </div>

            <div class="col">
                <label>Fecha Nacimiento</label>
                <input type="date" disabled>
            </div>
        </div>
    </div>

    <!-- 👨‍👩‍👦 APODERADO -->
    <div class="box">
        <div class="section-title">Datos del Apoderado</div>

        <div class="row">
            <div class="col">
                <label>Nombres</label>
                <input type="text" disabled>
            </div>

            <div class="col">
                <label>Apellidos</label>
                <input type="text" disabled>
            </div>

            <div class="col">
                <label>DNI</label>
                <input type="text" disabled>
            </div>

            <div class="col">
                <label>Relación</label>
                <input type="text" disabled>
            </div>

            <div class="col">
                <label>&nbsp;</label>
                <button class="btn-warning" style="width:100%;">Cambiar Apoderado</button>
            </div>
        </div>
    </div>

    <!-- 📚 MATRÍCULA -->
    <div class="box">
        <div class="section-title">Datos de la Matrícula</div>

        <div class="row">

            <div class="col">
                <label>Estado</label>
                <select name="estado">
                    <option value="ACTIVO">ACTIVO</option>
                    <option value="INACTIVO">INACTIVO</option>
                    <option value="CANCELADO">CANCELADO</option>
                </select>
            </div>

            <div class="col">
                <label>Nivel</label>
                <select name="idNivel">
                    <option value="1">Nivel 1</option>
                    <option value="2">Nivel 2</option>
                    <option value="3">Nivel 3</option>
                </select>
            </div>

        </div>

        <label>Observación</label>
        <input type="text" name="observacion" placeholder="Observaciones sobre la matrícula">
    </div>

    <!-- 💾 BOTÓN GUARDAR -->
    <form action="${pageContext.request.contextPath}/MatriculaEditarServlet" method="post">

        <!-- estos hidden luego los llenas con datos reales -->
        <input type="hidden" name="idMatricula" value="">
        <input type="hidden" name="idApoderado" value="">

        <button type="submit" class="btn-success">
            Guardar Cambios
        </button>

    </form>

    <!-- 📩 MENSAJE -->
    <%
        String mensaje = (String) request.getAttribute("mensaje");
        if (mensaje != null) {
    %>
        <div class="box" style="margin-top:15px;">
            <%= mensaje %>
        </div>
    <%
        }
    %>

</div>

</body>
</html>