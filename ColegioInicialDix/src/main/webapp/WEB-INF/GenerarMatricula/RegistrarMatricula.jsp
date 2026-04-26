<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Registrar Matrícula</title>
    <style>
        body {
            font-family: Arial;
            background-color: #f4f4f4;
        }
        .container {
            width: 400px;
            margin: 50px auto;
            background: white;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0px 0px 10px gray;
        }
        h2 {
            text-align: center;
        }
        input, textarea {
            width: 100%;
            padding: 8px;
            margin: 8px 0;
        }
        button {
            width: 100%;
            padding: 10px;
            background-color: #2ecc71;
            color: white;
            border: none;
            cursor: pointer;
        }
        .mensaje {
            margin-top: 10px;
            padding: 10px;
            text-align: center;
        }
        .ok {
            background-color: #2ecc71;
            color: white;
        }
        .error {
            background-color: #e74c3c;
            color: white;
        }
    </style>
</head>
<body>

<div class="container">
    <h2>Registrar Matrícula</h2>

    <form action="MatriculaRegistrar" method="post">
        
        <label>DNI Estudiante:</label>
        <input type="text" name="txtDniEstudiante" pattern="[0-9]{8}" 
         type="text" name="txtDniEstudiante"
               value="<%= request.getAttribute("dniEstudiante") != null ? request.getAttribute("dniEstudiante") : "" %>"
               required>
        

        <label>DNI Apoderado:</label>
        <input type="text" name="txtDniApoderado"
               value="<%= request.getAttribute("dniApoderado") != null ? request.getAttribute("dniApoderado") : "" %>"
               required>

        <label>Observación:</label>
        <textarea name="txtObservacion"></textarea>
        
        <label>Relación:</label>
		<select name="txtRelacion" onchange="mostrarOtro(this.value)">
		    <option value="">Seleccione</option>
		    <option value="Padre">Padre</option>
		    <option value="Madre">Madre</option>
		    <option value="Otro">Otro</option>
		</select>
		
		<input type="text" name="txtRelacionOtro" id="otroRelacion" 
		       placeholder="Especifique relación" style="display:none;">	

        <select name="txtEstado">
		    <option value="ACTIVO">ACTIVO</option>
		    <option value="INACTIVO">INACTIVO</option>
		</select>
        <button type="submit">Registrar</button>
    </form>

    <% 
        Boolean ok = (Boolean) request.getAttribute("ok");
        String mensaje = (String) request.getAttribute("mensaje");
        Object idG = request.getAttribute("idG");

        if (mensaje != null) {
    %>
        <div class="mensaje <%= (ok != null && ok) ? "ok" : "error" %>">
            <%= mensaje %>
            <% if (ok != null && ok && idG != null) { %>
                <br>ID Generado: <%= idG %>
            <% } %>
        </div>
    <% } %>

</div>

</body>
</html>