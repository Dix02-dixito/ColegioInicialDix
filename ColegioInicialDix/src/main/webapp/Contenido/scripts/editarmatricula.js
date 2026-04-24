function abrirModal(){

document.getElementById(
"modalApoderado"
).style.display="block";

}


function cerrarModal(){

document.getElementById(
"modalApoderado"
).style.display="none";

}


window.onclick=function(e){

var modal=
document.getElementById(
"modalApoderado"
);

if(e.target==modal){
modal.style.display="none";
}

}