package br.org.amigosdoautista.cadastroautista.service.security;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import br.org.amigosdoautista.cadastroautista.model.dto.security.NewUserDTO;
import br.org.amigosdoautista.cadastroautista.model.dto.security.UserDTO;
import br.org.amigosdoautista.cadastroautista.model.repository.security.UserRepository;
import br.org.amigosdoautista.cadastroautista.model.schemas.security.UserSchema;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserDetailsService userDetailsService() {
        return (String username) -> userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário ou senha incorretos"));
    }

    public UserDTO createNewUser(NewUserDTO newUser) {
        ModelMapper mapper = new ModelMapper();
        UserSchema userSchema = mapper.map(newUser, UserSchema.class);

        ObjectMapper m = new ObjectMapper();
        m.registerModule(new JavaTimeModule());
        try {
            System.out.println(m.writeValueAsString(userSchema));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return newUser;
    }

}