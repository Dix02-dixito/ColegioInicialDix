package app.data.interfaces;

import app.modelos.Estudiante;
import java.util.List;

public interface IEstudiante {

    Estudiante buscarPorDni(String dni);

    boolean registrarEstudiante(Estudiante e);

    boolean editarEstudiante(Estudiante e);

    boolean eliminarEstudiante(int idEstudiante);

    List<Estudiante> listarEstudiantes();

    boolean existeDni(String dni);

    boolean tieneMatricula(int idEstudiante);
    
    boolean insertarRelacion(int idEstudiante, int idApoderado, String relacion);
}