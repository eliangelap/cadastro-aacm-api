package br.org.amigosdoautista.cadastroautista.service.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import br.org.amigosdoautista.cadastroautista.model.dto.security.JwtAuthenticationResponse;
import br.org.amigosdoautista.cadastroautista.model.dto.security.SigninRequest;
import br.org.amigosdoautista.cadastroautista.model.repository.security.UserRepository;
import br.org.amigosdoautista.cadastroautista.model.schemas.security.UserSchema;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public JwtAuthenticationResponse signin(SigninRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        UserSchema user = userRepository.findByEmail(request.getUsername())
                .orElseThrow(() -> new BadCredentialsException("Usuário ou senha inválidos."));

        return jwtService.generateToken(user);
    }

}
