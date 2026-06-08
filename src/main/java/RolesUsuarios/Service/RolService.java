package RolesUsuarios.Service;

import RolesUsuarios.Entity.Rol;
import RolesUsuarios.Exception.RolNotFoundException;
import RolesUsuarios.Repository.RolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RolService {

    private final RolRepository rolRepository;

    public List<Rol> listar() {
        return rolRepository.findAll();
    }

    public Rol buscarPorId(Long id) {
        return rolRepository.findById(id)
                .orElseThrow(() -> new RolNotFoundException("id: " + id));
    }

    public Rol crear(Rol rol) {
        return rolRepository.save(rol);
    }

    public void eliminar(Long id) {
        if (!rolRepository.existsById(id)) {
            throw new RolNotFoundException("id: " + id);
        }
        rolRepository.deleteById(id);
    }
}