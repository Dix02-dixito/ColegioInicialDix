package app.modelos;

import java.sql.Date;

public class Matricula {

private int idMatricula;
private int idEstudiante;
private int idApoderado;
private int idNivel;
private int anio;

private String estado;
private String observacion;

private Date fecha;



private String dni;
private String nombresEstudiante;
private String apellidosEstudiante;

private String nombreApoderado;
private String apellidoApoderado;
private String telefonoApoderado;
private String direccionApoderado;
private String correoApoderado;
private String relacion;

private String nombreNivel;


public Matricula(){

idMatricula=0;
idEstudiante=0;
idApoderado=0;
idNivel=0;
anio=0;

estado="";
observacion="";
fecha=null;

dni="";
nombresEstudiante="";
apellidosEstudiante="";
nombreApoderado="";
relacion="";
nombreNivel="";
}




public int getIdMatricula() {
return idMatricula;
}

public void setIdMatricula(int idMatricula) {
this.idMatricula = idMatricula;
}

public int getIdEstudiante() {
return idEstudiante;
}

public void setIdEstudiante(int idEstudiante) {
this.idEstudiante = idEstudiante;
}

public int getIdApoderado() {
return idApoderado;
}

public void setIdApoderado(int idApoderado) {
this.idApoderado = idApoderado;
}

public int getIdNivel() {
return idNivel;
}

public void setIdNivel(int idNivel) {
this.idNivel = idNivel;
}

public int getAnio() {
return anio;
}

public void setAnio(int anio) {
this.anio = anio;
}

public String getEstado() {
return estado;
}

public void setEstado(String estado) {
this.estado = estado;
}

public String getObservacion() {
return observacion;
}

public void setObservacion(String observacion) {
this.observacion = observacion;
}

public Date getFecha() {
return fecha;
}

public void setFecha(Date fecha) {
this.fecha = fecha;
}


/* nuevos */

public String getDni() {
return dni;
}

public void setDni(String dni) {
this.dni = dni;
}

public String getNombresEstudiante() {
return nombresEstudiante;
}

public void setNombresEstudiante(String nombresEstudiante) {
this.nombresEstudiante = nombresEstudiante;
}

public String getApellidosEstudiante() {
return apellidosEstudiante;
}

public void setApellidosEstudiante(String apellidosEstudiante) {
this.apellidosEstudiante = apellidosEstudiante;
}

public String getNombreApoderado() {
return nombreApoderado;
}

public void setNombreApoderado(String nombreApoderado) {
this.nombreApoderado = nombreApoderado;
}

public String getRelacion() {
return relacion;
}

public void setRelacion(String relacion) {
this.relacion = relacion;
}

public String getNombreNivel() {
return nombreNivel;
}

public void setNombreNivel(String nombreNivel) {
this.nombreNivel = nombreNivel;
}

public String getApellidoApoderado() {
    return apellidoApoderado;
}

public void setApellidoApoderado(String apellidoApoderado) {
    this.apellidoApoderado = apellidoApoderado;
}


public String getTelefonoApoderado() {
    return telefonoApoderado;
}

public void setTelefonoApoderado(String telefonoApoderado) {
    this.telefonoApoderado = telefonoApoderado;
}


public String getDireccionApoderado() {
    return direccionApoderado;
}

public void setDireccionApoderado(String direccionApoderado) {
    this.direccionApoderado = direccionApoderado;
}


public String getCorreoApoderado() {
    return correoApoderado;
}

public void setCorreoApoderado(String correoApoderado) {
    this.correoApoderado = correoApoderado;
}

}