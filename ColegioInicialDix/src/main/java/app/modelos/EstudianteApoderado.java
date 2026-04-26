package app.modelos;


public class EstudianteApoderado {

    private int id;
    private int idEstudiante;
    private int idApoderado;
    private String relacion;

    
    public EstudianteApoderado() {
        id = 0;
        idEstudiante = 0;
        idApoderado = 0;
        relacion = "";
    }

   
    public EstudianteApoderado(int id, int idEstudiante, int idApoderado, String relacion) {
        this.id = id;
        this.idEstudiante = idEstudiante;
        this.idApoderado = idApoderado;
        this.relacion = relacion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getRelacion() {
        return relacion;
    }

    public void setRelacion(String relacion) {
        this.relacion = relacion;
    }
}