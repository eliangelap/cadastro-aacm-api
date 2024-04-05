package br.org.amigosdoautista.cadastroautista.model.schemas.formulary.id;

import br.org.amigosdoautista.cadastroautista.model.schemas.formulary.TopicSchema;
import lombok.Data;

@Data
public class QuestionID {

    private TopicSchema topic;
    private Integer idQuestion;

}
