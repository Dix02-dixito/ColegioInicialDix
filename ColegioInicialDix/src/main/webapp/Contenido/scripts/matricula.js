function seleccionarFila(id,nivel,apoderado,estado,obs){

    document.getElementById("idMatricula").value = id;
    document.getElementById("idNivel").value = nivel;
    document.getElementById("idApoderado").value = apoderado;
    document.getElementById("estado").value = estado;
    document.getElementById("observacion").value = obs;
}

function confirmar(accion){

    if(accion === "cancelar"){
        return confirm("Seguro deseas cancelar la matricula?");
    }

    if(accion === "inactivar"){
        return confirm("Seguro deseas inactivar la matricula?");
    }

    return true;
}

/* auto desaparecer alerta */
window.onload = function(){

    let alerta = document.querySelector(".alert");

    if(alerta){
        setTimeout(()=>{
            alerta.style.opacity = "0";
            alerta.style.transition = "0.5s";
        }, 2500);
    }

}