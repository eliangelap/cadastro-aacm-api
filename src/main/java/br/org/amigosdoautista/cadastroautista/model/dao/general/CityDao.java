package br.org.amigosdoautista.cadastroautista.model.dao.general;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CityDao {

    private Integer id;

    @NotEmpty(message = "Nome da cidade é obrigatório")
    private String name;

    @NotNull(message = "É obrigatório informar o número do IBGE.")
    private Integer ibgeNumber;

    @NotNull(message = "É obrigatório informar um estado.")
    private StateResponse state;
}
