package app.data.interfaces;

import app.modelos.Apoderado;
import java.util.List;

public interface IApoderado {

    // registrar
    public boolean guardar(Apoderado a);

    // por ID
    public Apoderado buscarPorId(int id);

    // por dni
    public Apoderado buscarPorDni(String dni);
    
    public List<Apoderado> listarApoderado();
    
    public boolean actualizarApoderado(Apoderado a);
    
    public boolean eliminarApoderado(int id);
    
    
}
