package br.org.amigosdoautista.cadastroautista.model.repository.person;

import org.springframework.data.jpa.repository.JpaRepository;

import br.org.amigosdoautista.cadastroautista.model.schemas.person.AutisticSchema;
import br.org.amigosdoautista.cadastroautista.model.schemas.person.IndividualEntitySchema;
import br.org.amigosdoautista.cadastroautista.model.schemas.person.id.AutisticID;

public interface AutisticRepository extends JpaRepository<AutisticSchema, IndividualEntitySchema> {

}
