package br.org.amigosdoautista.cadastroautista.model.repository.formulary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.org.amigosdoautista.cadastroautista.model.schemas.formulary.AnswerSchema;
import br.org.amigosdoautista.cadastroautista.model.schemas.formulary.id.AnswerID;

@Repository
public interface AnswerRepository extends JpaRepository<AnswerSchema, AnswerID> {

}
