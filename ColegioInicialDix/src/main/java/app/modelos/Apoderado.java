package app.modelos;

public class Apoderado {
	
	 private int idApoderado;
	    private String dni;
	    private String nombres;
	    private String apellidos;
	    private String direccion;
	    private String correo;
	    private String telefono;
	    private String sexo;
	    
	    
	    public Apoderado() {
			idApoderado = 0;
			dni = "";
			nombres = "";
			apellidos = "";
			direccion = "";
			correo = "";
			telefono = "";
			sexo = "";
		}

		public int getIdApoderado() {
			return idApoderado;
		}

		public void setIdApoderado(int idApoderado) {
			this.idApoderado = idApoderado;
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

		public String getDireccion() {
			return direccion;
		}

		public void setDireccion(String direccion) {
			this.direccion = direccion;
		}

		public String getCorreo() {
			return correo;
		}

		public void setCorreo(String correo) {
			this.correo = correo;
		}

		public String getTelefono() {
			return telefono;
		}

		public void setTelefono(String telefono) {
			this.telefono = telefono;
		}

		public String getSexo() {
			return sexo;
		}

		public void setSexo(String sexo) {
			this.sexo = sexo;
		}

		
	    
}
