package RolesUsuarios.Service;

import RolesUsuarios.DTO.LoginRequestDTO;
import RolesUsuarios.DTO.LoginResponseDTO;
import RolesUsuarios.Entity.Usuario;
import RolesUsuarios.Repository.UsuarioRepository;
import RolesUsuarios.Security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public LoginResponseDTO login(LoginRequestDTO request) {
        Usuario usuario = usuarioRepository.findByCedula(request.getCedula())
                .orElseThrow(() -> new RuntimeException("Credenciales inválidas"));

        if (!passwordEncoder.matches(request.getPassword(), usuario.getPassword())) {
            throw new RuntimeException("Credenciales inválidas");
        }

        String token = jwtUtil.generarToken(usuario.getCedula(), usuario.getRol().getNombre());

        return new LoginResponseDTO(token, usuario.getCedula(), usuario.getRol().getNombre());
    }
}