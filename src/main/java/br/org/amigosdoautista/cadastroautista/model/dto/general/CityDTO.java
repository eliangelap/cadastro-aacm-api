package br.org.amigosdoautista.cadastroautista.model.dto.general;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CityDTO {

    private Integer id;

    @NotEmpty(message = "Nome da cidade é obrigatório")
    @Size(max = 100, message = "Nome da cidade deve possuir até 100 caracteres")
    private String name;

    @NotNull(message = "É obrigatório informar o número do IBGE.")
    private Integer ibgeNumber;

    @NotNull(message = "É obrigatório informar um estado.")
    private StateResponse state;
}
