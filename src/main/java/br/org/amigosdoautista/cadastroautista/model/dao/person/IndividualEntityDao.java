package br.org.amigosdoautista.cadastroautista.model.dao.person;

import java.time.LocalDate;

import br.org.amigosdoautista.cadastroautista.model.dao.general.AddressDao;
import br.org.amigosdoautista.cadastroautista.model.types.SexType;
import lombok.Data;

@Data
public class IndividualEntityDao {

    private Integer id;
    private String name;
    private String cpf;
    private String rg;
    private AddressDao address;
    private LocalDate birthDate;
    private SexType sex;
    private LocalDate deathDate;
    private String occupation;

}
