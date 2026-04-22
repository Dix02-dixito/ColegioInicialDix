package app.data.interfaces;

import java.util.List;

import java.sql.ResultSet;

import app.modelos.Apoderado;
import app.modelos.Matricula;

public interface IMatricula {
	
	Matricula buscarPorDniOCodigo(String dni, Integer id);
	
	boolean editarMatricula (Matricula matricula);
	
	boolean renovarMatricula(int idMatricula);
    
	boolean cancelarMatricula(int idMatricula);

	Apoderado buscarApoderadoPorDni(String dni);
	
	List<Matricula> listarRelacionApoderadoEstudiante(int idEstudiante);
    
	List<String> listarNiveles();
	
	Matricula mapearMatricula(ResultSet rs) throws Exception;
	
    
    
    
	
	

}
