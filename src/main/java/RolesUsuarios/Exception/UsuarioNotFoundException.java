package RolesUsuarios.Exception;

public class UsuarioNotFoundException extends RuntimeException{
    public UsuarioNotFoundException(Long id){
        super("Usuario con id no encontrado" + id);
    }
}
