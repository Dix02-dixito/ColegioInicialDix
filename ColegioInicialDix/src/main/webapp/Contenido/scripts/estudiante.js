function habilitarCampos(){
    document.querySelectorAll(".editable").forEach(e=>{
        e.removeAttribute("readonly");
    });
}

function bloquearCampos(){
    document.querySelectorAll(".editable").forEach(e=>{
        e.setAttribute("readonly", true);
    });
}

function nuevo(){
    document.querySelectorAll(".editable").forEach(e=>{
        e.removeAttribute("readonly");
        e.value = "";
    });
}

function limpiar(){
    document.getElementById("form").reset();
}

function confirmarEliminar(){
    return confirm("¿Seguro que deseas eliminar este estudiante?");
}

function soloNumeros(input){
    input.value = input.value.replace(/[^0-9]/g,'');
}