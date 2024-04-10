package br.org.amigosdoautista.cadastroautista.model.dao.person;

import lombok.Data;

@Data
public class AutisticDao {

    private Integer id;
    private IndividualEntityDao motherPerson;
    private IndividualEntityDao fatherPerson;
    private IndividualEntityDao guardianPerson;

}
