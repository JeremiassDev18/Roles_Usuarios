package RolesUsuarios.Exception;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ErrorResponse {
    private int status;
    private String mensaje;
    private LocalDateTime timeError;

    public ErrorResponse(int status, String mensaje){
        this.status = status;
        this.mensaje = mensaje;
        this.timeError = LocalDateTime.now();
    }

}
