package br.org.amigosdoautista.cadastroautista.model.schemas.formulary.id;

import br.org.amigosdoautista.cadastroautista.model.schemas.formulary.QuestionSchema;
import lombok.Data;

@Data
public class QuestionItemID {

    private QuestionSchema question;
    private Integer idQuestionItem;

}
