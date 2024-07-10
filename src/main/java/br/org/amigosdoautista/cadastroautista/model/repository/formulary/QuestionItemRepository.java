package br.org.amigosdoautista.cadastroautista.model.repository.formulary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.org.amigosdoautista.cadastroautista.model.schemas.formulary.QuestionSchema;
import br.org.amigosdoautista.cadastroautista.model.schemas.formulary.QuestionItemSchema;
import java.util.List;

@Repository
public interface QuestionItemRepository extends JpaRepository<QuestionItemSchema, Integer> {

    List<QuestionItemSchema> findByQuestion(QuestionSchema question);

}
