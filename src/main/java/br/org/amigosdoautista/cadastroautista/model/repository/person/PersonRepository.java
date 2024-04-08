package br.org.amigosdoautista.cadastroautista.model.repository.person;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.org.amigosdoautista.cadastroautista.model.schemas.person.PersonSchema;

@Repository
public interface PersonRepository extends JpaRepository<PersonSchema, Integer> {

}
