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
	    
	    public Matricula () {
	    	
	    	idMatricula = 0;
	    	idEstudiante = 0;
	    	idApoderado = 0;
	    	idNivel = 0;
	    	anio = 0;
	    	estado = "";
	    	observacion = "";
	    	fecha = null;
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

	    
}
