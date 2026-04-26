package app.data.interfaces;

import java.util.List;
import java.sql.ResultSet;
import app.modelos.Apoderado;
import app.modelos.Matricula;

public interface IMatricula {

    Matricula buscarPorDniOCodigo(String dni, Integer id);

    boolean editarMatricula(Matricula matricula);

    boolean renovarMatricula(int idMatricula);

    boolean cancelarMatricula(int idMatricula);

    boolean cambiarApoderado(int idMatricula, int idEstudiante, int idNuevoApoderado);

    Apoderado buscarApoderadoPorDni(String dni, int idEstudiante);

    boolean existeRelacion(int idEstudiante, int idApoderado);

    List<Matricula> listarRelacionApoderadoEstudiante(int idEstudiante);

    List<String> listarNiveles();

    Matricula mapearMatricula(ResultSet rs) throws Exception;
    
    public boolean existeMatriculaActiva(int idEstudiante);
    
    public boolean generar(Matricula m);
}