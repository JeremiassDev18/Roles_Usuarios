package RolesUsuarios.Entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "usuarios")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String cedula;
    private String password;
    private Integer edad;

    @ManyToOne
    @JoinColumn(name = "rol_id")
    private Rol rol;

}
