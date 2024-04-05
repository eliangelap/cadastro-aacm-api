package br.org.amigosdoautista.cadastroautista.model.dto.person;

import java.time.LocalDate;

import br.org.amigosdoautista.cadastroautista.model.schemas.person.SexType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IndividualEntityRequest {

    private Integer id;
    private String name;
    private String cpf;
    private String rg;
    private LocalDate birthDate;
    private SexType sex;
    private LocalDate deathDate;
    private IndividualEntityRequest motherPerson;
    private IndividualEntityRequest fatherPerson;

}
