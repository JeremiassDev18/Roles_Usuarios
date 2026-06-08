package RolesUsuarios.Exception;

public class RolNotFoundException extends RuntimeException{
    public RolNotFoundException(String nombre){
        super("Rol del nombre no encontrado: " + nombre);
    }
}
