package br.org.amigosdoautista.cadastroautista.model.dto.security;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class NewUserDTO extends UserDTO {

    private String password;

}
