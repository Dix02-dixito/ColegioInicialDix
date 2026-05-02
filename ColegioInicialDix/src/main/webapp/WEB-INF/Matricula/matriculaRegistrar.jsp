<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
Boolean ok = (Boolean) request.getAttribute("ok");
String mensaje = (String) request.getAttribute("mensaje");
Object idG = request.getAttribute("idG");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registrar Matrícula</title>

<link rel="stylesheet"
href="${pageContext.request.contextPath}/Contenido/estilos/apoderado.css">

<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>

</head>

<body>

<div class="sidebar">
    <h2>Dix Academy</h2>
    <a href="${pageContext.request.contextPath}/Inicio">INICIO</a>
    <a href="${pageContext.request.contextPath}/MatriculaRegistrar">GENERAR MATRICULA</a>
    <a href="${pageContext.request.contextPath}/Mantenimiento/Matricula">MATRICULA</a>
    <a href="${pageContext.request.contextPath}/MantenimientoApoderado">APODERADO</a>
    <a href="${pageContext.request.contextPath}/Mantenimiento/Estudiante">ESTUDIANTE</a>
    <a href="${pageContext.request.contextPath}/actividad">ACTIVIDAD</a>
</div>

<div class="main">

    <div class="top">
        <h2>Generar Matrícula</h2>
    </div>

    <form onsubmit="return validarRegistro(event)" method="post" >

<!-- 🔵 ESTUDIANTE -->
<div class="box">

    <h3>Datos del Estudiante</h3>

    <div class="row">

        <div class="col">
            <label>DNI</label>
            <input type="text" id="dniEstudiante"
                   name="txtDniEstudiante"
                   maxlength="8"
                   required>
        </div>

        <div class="col">
            <label>&nbsp;</label>
            <button type="button" class="btn" onclick="buscar()">Buscar</button>
        </div>

    </div>

    <label>Nombres</label>
    <input id="nomEst" readonly>

    <label>Apellidos</label>
    <input id="apeEst" readonly>
    <label>Fecha de Nacimiento</label>
	<input id="fechaEst" readonly>
	<label>Sexo</label>
   <input id="sexoEst" readonly>

</div>

<!-- 🔵 APODERADO -->
     <div class="box">

         <h3>Datos del Apoderado</h3>

         <label>Nombre</label>
         <input id="nomApo" readonly>

         <label>DNI</label>
         <input id="dniApo" name="txtDniApoderado" readonly>

         <label>Relación</label>
         <input id="relacion" name="txtRelacion" readonly>
         <label>Teléfono</label>
<input id="telApo" readonly>		
<label>Correo</label>
<input id="correoApo" readonly>	
<label>Dirección</label>
<input id="dirApo" readonly>

     </div>

     <!-- 🔵 MATRÍCULA -->
<div class="box">

    <h3>Datos de Matrícula</h3>

    <div class="row">

        <div class="col">
            <label>Nivel</label>
            <select name="txtNivel">
                <option value="1">Inicial 1</option>
                <option value="2">Inicial 2</option>
                <option value="3">Inicial 3</option>
            </select>
        </div>

        <div class="col">
            <label>Año</label>
            <input type="number" name="txtAnio"
                   value="<%= java.time.Year.now().getValue() %>"
                   readonly>
        </div>

    </div>

    <label>Observación</label>
    <textarea name="txtObservacion"></textarea>

</div>
<div class="guardar">
	            <button type="submit" class="btn">Registrar Matrícula</button>
	            
	        </div>


<div id="panelFirma" style="display:none; margin-top:20px;" class="box">

    <h3>Firma del Administrador</h3>


</div>

    </form>
    <!-- 📄 SUBIR PDF FIRMADO -->
<div class="box">

    <h3>Firma Administrador (PDF)</h3>

    <form id="formPDF" enctype="multipart/form-data">

        <label>Subir documento firmado</label>
        <input type="file" id="pdfFirmado" accept="application/pdf" required>

        <div class="guardar">
            <button type="button" class="btn" onclick="validarPDF()">
                Subir PDF
            </button>
        </div>
        
        

    </form>
    
    

</div>


</div>
	

<script>

let estudianteCargado = false;

/* SOLO NUMEROS */
document.getElementById("dniEstudiante").addEventListener("input", e=>{
    e.target.value = e.target.value.replace(/[^0-9]/g,'');
});

/* FORMATEAR */
function formatearFecha(fecha){
    if(!fecha) return "";

    let f = new Date(fecha);

    let dia = String(f.getDate()).padStart(2, '0');
    let mes = String(f.getMonth() + 1).padStart(2, '0');
    let anio = f.getFullYear();

    return `${dia}/${mes}/${anio}`;
}

function mostrarSexo(sexo){
    return sexo === "M" ? "Masculino" : sexo === "F" ? "Femenino" : "";
}

/* BUSCAR */
function buscar() {

    let dni = document.getElementById("dniEstudiante").value.trim();

    if(dni.length !== 8){
        Swal.fire({
            icon:'warning',
            title:'DNI inválido',
            text:'Debe tener 8 dígitos'
        });
        return;
    }

    fetch("<%=request.getContextPath()%>/BuscarEstudianteServlet?dni=" + dni)
    .then(res => res.json())
    .then(data => {

        console.log("DATA:", data);

        if(!data || !data.dni){
            estudianteCargado = false;

            Swal.fire({
                icon:'error',
                title:'No encontrado',
                text:'El estudiante no existe'
            });
            return;
        }

        estudianteCargado = true;

        // ✅ ALERTA CORRECTA
        Swal.fire({
            icon: 'success',
            title: 'Estudiante encontrado',
            text: data.nombres + " " + data.apellidos,
            timer: 1500,
            showConfirmButton: false
        });

        // ESTUDIANTE
        document.getElementById("nomEst").value = data.nombres;
        document.getElementById("apeEst").value = data.apellidos;
        document.getElementById("fechaEst").value = data.fecha;
        document.getElementById("sexoEst").value = mostrarSexo(data.sexo);
        

        // APODERADO
        if(!data.dniApoderado){
            Swal.fire({
                icon:'warning',
                title:'Sin apoderado',
                text:'El estudiante no tiene apoderado registrado'
            });
            return;
        }

        document.getElementById("nomApo").value = data.nombreApoderado;
        document.getElementById("dniApo").value = data.dniApoderado;
        document.getElementById("relacion").value = data.relacion;
        document.getElementById("telApo").value = data.telefono;
        document.getElementById("correoApo").value = data.correo;
        document.getElementById("dirApo").value = data.direccion;

    })
    .catch(err => {
        console.error("ERROR:", err);
        Swal.fire({
            icon:'error',
            title:'Error',
            text:'No se pudo conectar con el servidor'
        });
    });
}

/* VALIDAR ANTES DE REGISTRAR */
function generarMatricula(){

    let firma = document.getElementById("firmaBase64").value;

    if(!firma){
        Swal.fire({
            icon:'warning',
            title:'Firma requerida',
            text:'Debe firmar antes de registrar'
        });
        return;
    }

    document.querySelector("form").submit();
}

/* FIRMA */


/* ALERTA FINAL JSP */
<% if (ok != null && ok) { %>

Swal.fire({
    icon: 'success',
    title: '🎉 Matrícula registrada',
    html: `
        <b>ID generado:</b> <%= idG %><br>
        Registro guardado correctamente
    `,
    confirmButtonColor: '#2ecc71'
});

<% } else if (mensaje != null) { %>

Swal.fire({
    icon: 'error',
    title: 'Oops...',
    text: '<%= mensaje %>',
    confirmButtonColor: '#e74c3c'
});

<% } %>

function validarPDF(){

    let file = document.getElementById("pdfFirmado").files[0];

    if(!file){
        Swal.fire({
            icon:'warning',
            title:'Archivo requerido',
            text:'Debe seleccionar un PDF firmado'
        });
        return;
    }

    if(file.type !== "application/pdf"){
        Swal.fire({
            icon:'error',
            title:'Archivo inválido',
            text:'Solo se permite formato PDF'
        });
        return;
    }

    if(file.size > 5 * 1024 * 1024){
        Swal.fire({
            icon:'warning',
            title:'Archivo muy pesado',
            text:'Máximo 5MB permitido'
        });
        return;
    }

    Swal.fire({
        icon:'success',
        title:'PDF cargado correctamente',
        text:'Documento listo ✔',
        confirmButtonColor:'#2ecc71'
    });

}
</script>
</body>
</html>