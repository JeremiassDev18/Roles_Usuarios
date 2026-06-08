package RolesUsuarios.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
public class UsuarioRequestDTO {
    private String nombre;
    private String cedula;
    private String password;
    private Integer edad;
    private String rol;
}
