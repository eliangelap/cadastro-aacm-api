package br.org.amigosdoautista.cadastroautista.model.schemas.formulary.id;

import br.org.amigosdoautista.cadastroautista.model.schemas.formulary.QuestionSchema;
import br.org.amigosdoautista.cadastroautista.model.schemas.person.AutisticSchema;
import lombok.Data;

@Data
public class AnswerID {

    private QuestionSchema question;
    private Integer idAnswer;
    private AutisticSchema autistic;

}
