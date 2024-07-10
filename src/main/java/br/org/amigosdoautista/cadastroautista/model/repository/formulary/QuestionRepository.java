package br.org.amigosdoautista.cadastroautista.model.repository.formulary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.org.amigosdoautista.cadastroautista.model.schemas.formulary.QuestionSchema;
import br.org.amigosdoautista.cadastroautista.model.schemas.formulary.TopicSchema;
import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<QuestionSchema, Integer> {

    List<QuestionSchema> findByTopic(TopicSchema topic);

}
