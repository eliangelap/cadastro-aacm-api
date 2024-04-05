package br.org.amigosdoautista.cadastroautista.model.schemas.formulary.id;

import br.org.amigosdoautista.cadastroautista.model.schemas.formulary.QuestionSchema;
import lombok.Data;

@Data
public class AttachmentID {

    private QuestionSchema question;
    private Integer idAttachment;

}
