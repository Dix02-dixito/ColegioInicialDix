package app.data.interfaces;

import app.modelos.Estudiante;
import java.util.List;

public interface IEstudiante {

    // registrar
    public boolean guardar(Estudiante e);

    // por id
    public Estudiante buscarPorId(int id);

    // por dni
    public Estudiante buscarPorDni(String dni);

    public List<Estudiante> listar(); }