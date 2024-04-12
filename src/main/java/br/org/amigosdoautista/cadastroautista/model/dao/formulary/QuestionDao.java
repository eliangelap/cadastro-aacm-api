package br.org.amigosdoautista.cadastroautista.model.dao.formulary;

import java.util.ArrayList;
import java.util.List;

import br.org.amigosdoautista.cadastroautista.model.types.QuestionType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionDao {

    @NotEmpty(message = "É obrigatório informar uma descrição para a questão")
    @Size(max = 250, message = "Questão deve possuir até 250 caracteres")
    private String description;

    @Size(max = 500, message = "Descrição Adicional deve possuir até 500 caracteres")
    private String additionalDescription;

    @NotNull(message = "Tipo de questão é obrigatório")
    private QuestionType questionType;

    @NotNull(message = "É obrigatório informar a numeração da ordem")
    private Integer sequence;

    @NotNull(message = "É obrigatório informar se a questão é obrigatória")
    private Boolean mandatory;

    @Builder.Default
    private List<QuestionItemDao> questionItems = new ArrayList<>();

}
