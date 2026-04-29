package app.modelos;

public class Actividad {
    private String usuario;
    private String accion;
    private String detalle;
    private String fecha;
    private String hora;

    public String getUsuario() {
    	return usuario; 
    	}
    public void setUsuario(String usuario) { 
    	this.usuario = usuario; 
    	}

    public String getAccion() {
    	return accion; 
    	}
    public void setAccion(String accion) { 
    	this.accion = accion;
    	}

    public String getDetalle() { 
    	return detalle;
    	}
    public void setDetalle(String detalle) {
    	this.detalle = detalle;
    	}

    public String getFecha() { 
    	return fecha;
    	}
    public void setFecha(String fecha) {
    	this.fecha = fecha; 
    	}

    public String getHora() {
    	return hora; 
    }
    public void setHora(String hora) {  
    	this.hora = hora; 
    	}
}