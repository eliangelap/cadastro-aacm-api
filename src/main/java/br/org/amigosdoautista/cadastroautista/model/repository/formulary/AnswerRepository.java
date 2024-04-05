package br.org.amigosdoautista.cadastroautista.model.repository.formulary;

import org.springframework.data.jpa.repository.JpaRepository;

import br.org.amigosdoautista.cadastroautista.model.schemas.formulary.AnswerSchema;
import br.org.amigosdoautista.cadastroautista.model.schemas.formulary.id.AnswerID;

public interface AnswerRepository extends JpaRepository<AnswerSchema, AnswerID> {

}
