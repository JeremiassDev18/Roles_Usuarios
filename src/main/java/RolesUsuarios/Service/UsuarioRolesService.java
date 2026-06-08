package RolesUsuarios.Service;


import RolesUsuarios.DTO.UsuarioRequestDTO;
import RolesUsuarios.DTO.UsuarioResponseDTO;
import RolesUsuarios.Entity.Rol;
import RolesUsuarios.Entity.Usuario;
import RolesUsuarios.Exception.RolNotFoundException;
import RolesUsuarios.Exception.UsuarioNotFoundException;
import RolesUsuarios.Repository.RolRepository;
import RolesUsuarios.Repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@RequiredArgsConstructor
@Service
public class UsuarioRolesService {
    private final UsuarioRepository usu;
    private final RolRepository roli;
    private final PasswordEncoder passwordEncoder; // ← inyectas esto

    public List<UsuarioResponseDTO> listar(){
        return usu.findAll()
                .stream()
                .map(this::convertirDTO)
                .toList();
                }

    public UsuarioResponseDTO obtenerPorId(Long id) {

        Usuario usuario = usu.findById(id)
                .orElseThrow(() -> new UsuarioNotFoundException(id));

        UsuarioResponseDTO dto = convertirDTO(usuario);
     
        return dto;
    }

    public UsuarioResponseDTO convertirDTO(Usuario usuario){
        UsuarioResponseDTO dto = new UsuarioResponseDTO();
        dto.setId(usuario.getId());
        dto.setNombre(usuario.getNombre());
        dto.setCedula(usuario.getCedula());
        dto.setEdad(usuario.getEdad());
        dto.setRol(usuario.getRol().getNombre());

        return dto;
    }
    public UsuarioResponseDTO CrearUsuario(UsuarioRequestDTO urd){
        Rol rol = roli.findByNombre(urd.getRol())
                .orElseThrow(() -> new RolNotFoundException(urd.getRol()));

        Usuario usua = new Usuario();
        usua.setNombre(urd.getNombre());
        usua.setEdad(urd.getEdad());
        usua.setCedula(urd.getCedula());
        usua.setPassword(passwordEncoder.encode(urd.getPassword()));
        usua.setRol(rol);

        Usuario guardado =  usu.save(usua);

        UsuarioResponseDTO urdto = new UsuarioResponseDTO();
        urdto.setNombre(guardado.getNombre());
        urdto.setId(guardado.getId());
        urdto.setEdad(guardado.getEdad());
        urdto.setRol(guardado.getRol().getNombre());
        urdto.setCedula(guardado.getCedula());

        return urdto;

    }

    public void eliminar(Long id){
        if(!usu.existsById(id)){
            throw new UsuarioNotFoundException(id);
        }
        usu.deleteById(id);
    }


    public UsuarioResponseDTO actualizar(Long id, UsuarioRequestDTO request) {

        Usuario usuario = usu.findById(id)
                .orElseThrow(() -> new UsuarioNotFoundException(id));

        Rol rol = roli.findByNombre(request.getRol())
                .orElseThrow(() -> new RolNotFoundException(request.getRol()));

        usuario.setNombre(request.getNombre());
        usuario.setCedula(request.getCedula());
        usuario.setEdad(request.getEdad());
        usuario.setPassword(passwordEncoder.encode(request.getPassword()));
        usuario.setRol(rol);

        Usuario actualizado = usu.save(usuario);

        return convertirDTO(actualizado);
    }


}



 