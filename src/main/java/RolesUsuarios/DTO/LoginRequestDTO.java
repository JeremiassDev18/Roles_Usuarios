package RolesUsuarios.DTO;

import lombok.Data;

@Data
public class LoginRequestDTO {
    private String cedula;
    private String password;
}