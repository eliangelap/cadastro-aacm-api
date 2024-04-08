package br.org.amigosdoautista.cadastroautista.model.repository.person;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.org.amigosdoautista.cadastroautista.model.schemas.person.AutisticSchema;

@Repository
public interface AutisticRepository extends JpaRepository<AutisticSchema, Integer> {

}
