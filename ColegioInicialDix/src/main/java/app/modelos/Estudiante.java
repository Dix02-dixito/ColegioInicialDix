package app.modelos;

import java.sql.Date;

public class Estudiante {
	
	private int idEstudiante;
	private String dni;
	private String nombres;
	private String apellidos;
	private Date fechaNacimiento;
	private String sexo;
	
	private String nombreApoderado;
	private String relacion;
	private String apellidoPaterno;
	private String apellidoMaterno;
	
	public Estudiante() {
		idEstudiante = 0;
		dni = "";
		nombres = "";
		apellidos = "";
		fechaNacimiento = null;
		sexo = "";
	}

	public String getApellidoPaterno() {
		return apellidoPaterno;
	}

	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	public String getApellidoMaterno() {
		return apellidoMaterno;
	}

	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	public int getIdEstudiante() {
		return idEstudiante;
	}

	public void setIdEstudiante(int idEstudiante) {
		this.idEstudiante = idEstudiante;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
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
	
	
}
