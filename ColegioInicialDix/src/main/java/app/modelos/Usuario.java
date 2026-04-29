package app.modelos;

public class Usuario {

    private int id;
    private String codigo;
    private String nombre;
    private String usuario; // será el código
    private String contrasena;
    private String rol;
    private String correo;

    public Usuario() {}

    public Usuario(int id, String codigo, String nombre, String usuario, String contrasena, String rol) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.rol = rol;
    }

    public int getId() { 
    	return id; 
    	}
    public void setId(int id) { 
    	this.id = id; 
    	}

    public String getCodigo() { 
    	return codigo; 
    	}
    public void setCodigo(String codigo) { 
    	this.codigo = codigo;
    	}

    public String getNombre() { 
    	return nombre; 
    	}
    public void setNombre(String nombre) { 
    	this.nombre = nombre;
    	}

    public String getUsuario() { 
    	return usuario; 
    	}
    public void setUsuario(String usuario) { 
    	this.usuario = usuario;
    	}

    public String getContrasena() { 
    	return contrasena;
    	}
    public void setContrasena(String contrasena) { 
    	this.contrasena = contrasena;
    	}

    public String getRol() { 
    	return rol; 
    	}
    public void setRol(String rol) { 
    	this.rol = rol;
    	}
    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}