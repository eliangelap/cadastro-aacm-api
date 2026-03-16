package br.org.amigosdoautista.cadastroautista.model.dto.security;

import java.time.LocalDate;

import br.org.amigosdoautista.cadastroautista.model.dto.general.AddressDTO;
import br.org.amigosdoautista.cadastroautista.model.types.SexType;
import lombok.Data;

@Data
public class UserDTO {

    private Integer id;
    private String individualEntityName;
    private String individualEntityCpf;
    private String individualEntityRg;
    private String email;
    private LocalDate birthDate;
    private SexType sex;
    private String occupation;
    private AddressDTO address;

}
