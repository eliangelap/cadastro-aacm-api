package br.org.amigosdoautista.cadastroautista.model.schemas.formulary.id;

import br.org.amigosdoautista.cadastroautista.model.schemas.formulary.FormSchema;
import lombok.Data;

@Data
public class VersionID {

    private FormSchema form;
    private Integer idVersion;

}
