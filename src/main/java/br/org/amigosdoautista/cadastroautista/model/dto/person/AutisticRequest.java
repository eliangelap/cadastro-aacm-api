package br.org.amigosdoautista.cadastroautista.model.dto.person;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class AutisticRequest extends IndividualEntityRequest {

    private Integer id;
    private IndividualEntityRequest guardianPerson;

}
