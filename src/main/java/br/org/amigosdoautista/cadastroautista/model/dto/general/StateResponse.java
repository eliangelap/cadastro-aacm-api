package br.org.amigosdoautista.cadastroautista.model.dto.general;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class StateResponse {

    @NotEmpty(message = "É obrigatório informar o ID do estado.")
    private Integer id;

    private String name;
    private Integer ibgeNumber;
    private String uf;

}
