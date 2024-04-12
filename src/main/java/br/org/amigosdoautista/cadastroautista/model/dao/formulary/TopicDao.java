package br.org.amigosdoautista.cadastroautista.model.dao.formulary;

import java.util.ArrayList;
import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TopicDao {

    @NotEmpty(message = "É obrigatório informar um título")
    private String name;

    @NotNull(message = "É obrigatório informar uma sequência numérica")
    @Positive(message = "É obrigatório informar um número maior que zero para a sequência")
    private Integer sequence;

    @NotEmpty(message = "É obrigatório informar pelo menos uma questão")
    @Builder.Default
    private List<QuestionDao> questions = new ArrayList<>();

}
