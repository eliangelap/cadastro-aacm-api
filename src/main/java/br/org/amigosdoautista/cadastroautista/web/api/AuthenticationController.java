package br.org.amigosdoautista.cadastroautista.web.api;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.amigosdoautista.cadastroautista.model.dto.security.JwtAuthenticationResponse;
import br.org.amigosdoautista.cadastroautista.model.dto.security.SigninRequest;
import br.org.amigosdoautista.cadastroautista.service.security.AuthenticationService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/authentication")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public JwtAuthenticationResponse authenticate(@RequestBody(required = true) SigninRequest signinRequest) {
        return authenticationService.signin(signinRequest);
    }

}
