package app.data.interfaces;

import app.modelos.Usuario;

public interface IUsuario {

    Usuario validar(String usuario, String contrasena);

}