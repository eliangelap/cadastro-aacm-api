package br.org.amigosdoautista.cadastroautista.model.repository.person;

import org.springframework.data.jpa.repository.JpaRepository;

import br.org.amigosdoautista.cadastroautista.model.schemas.person.PersonSchema;

public interface PersonRepository extends JpaRepository<PersonSchema, Integer> {

}
