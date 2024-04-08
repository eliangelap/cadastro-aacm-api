package br.org.amigosdoautista.cadastroautista.model.repository.person;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.org.amigosdoautista.cadastroautista.model.schemas.person.IndividualEntitySchema;

@Repository
public interface IndividualEntityRepository extends JpaRepository<IndividualEntitySchema, Integer> {

}
