package RolesUsuarios.DTO;

import lombok.Data;

@Data
public class UsuarioResponseDTO {

    private Long id;
    private String nombre;
    private String cedula;
    private Integer edad;
    private String rol;

}
