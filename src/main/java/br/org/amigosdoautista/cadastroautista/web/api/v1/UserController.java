package br.org.amigosdoautista.cadastroautista.web.api.v1;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.amigosdoautista.cadastroautista.model.dto.security.NewUserDTO;
import br.org.amigosdoautista.cadastroautista.model.dto.security.UserDTO;
import br.org.amigosdoautista.cadastroautista.service.security.UserService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/user")
public class UserController {

    private final UserService userService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDTO createNewUser(@RequestBody(required = true) NewUserDTO newUser) {
        return userService.createNewUser(newUser);
    }

}
