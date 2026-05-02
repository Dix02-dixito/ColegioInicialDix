package app.data.interfaces;

import java.util.List;
import app.modelos.Matricula;

public interface IMatricula {

    public List<Matricula> listarMatriculas();

    public Matricula buscarMatriculaActivaPorDni(String dni);

    public List<String[]> listarApoderadosPorEstudiante(int idEstudiante);

    public List<String[]> listarNiveles();

    public boolean editarMatricula(Matricula m);

    public boolean cambiarEstado(int idMatricula, String estado);
}