package RolesUsuarios.Controller;


import RolesUsuarios.DTO.UsuarioRequestDTO;
import RolesUsuarios.DTO.UsuarioResponseDTO;
import RolesUsuarios.Entity.Usuario;
import RolesUsuarios.Service.UsuarioRolesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/usuarios")
public class UsuarioController {
    private final UsuarioRolesService service;

    @GetMapping
    public List<UsuarioResponseDTO> listar() {
        return service.listar();
    }

    // GET /usuarios/1
    @GetMapping("/{id}")
    public UsuarioResponseDTO buscarPorId(@PathVariable Long id) {
        return service.obtenerPorId(id);
    }

    // POST /usuarios
    @PostMapping
    public UsuarioResponseDTO crear(@RequestBody UsuarioRequestDTO dto) {
        return service.CrearUsuario(dto);
    }

    // DELETE /usuarios/1
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }

    @PutMapping("/{id}")
    public UsuarioResponseDTO actualizar(@PathVariable Long id, @RequestBody UsuarioRequestDTO dto){
        return service.actualizar(id, dto);
    }
}
