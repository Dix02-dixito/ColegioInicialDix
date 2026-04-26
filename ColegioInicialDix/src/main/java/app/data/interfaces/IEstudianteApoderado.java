package app.data.interfaces;

import java.util.List;

import app.modelos.EstudianteApoderado;

public interface IEstudianteApoderado {
	
	public boolean insertarRelacion(EstudianteApoderado ea);
	
	public boolean existeRelacion(int idEstudiante, int idApoderado);
	
	public List<EstudianteApoderado> listarRelacion();
	
	public boolean eliminarRelacion(int id);
	
	public List<EstudianteApoderado> listarPorEstudiante(int idEstudiante);
	
	

}
