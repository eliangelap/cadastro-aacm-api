package br.org.amigosdoautista.cadastroautista.model.schemas.formulary.id;

import br.org.amigosdoautista.cadastroautista.model.schemas.formulary.VersionSchema;
import lombok.Data;

@Data
public class TopicID {

    private VersionSchema version;
    private Integer idTopic;

}
