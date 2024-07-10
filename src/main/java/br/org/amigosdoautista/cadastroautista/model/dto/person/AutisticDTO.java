package br.org.amigosdoautista.cadastroautista.model.dto.person;

import java.time.LocalDate;

import br.org.amigosdoautista.cadastroautista.model.dto.general.AddressDTO;
import br.org.amigosdoautista.cadastroautista.model.types.SexType;
import lombok.Data;

@Data
public class AutisticDTO {

    private Integer id;
    private String name;
    private String cpf;
    private String email;
    private String rg;
    private AddressDTO address;
    private LocalDate birthDate;
    private SexType sex;
    private LocalDate deathDate;
    private String occupation;
    private IndividualEntityDTO motherPerson;
    private IndividualEntityDTO fatherPerson;
    private IndividualEntityDTO guardianPerson;

}
