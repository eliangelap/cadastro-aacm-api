package br.org.amigosdoautista.cadastroautista.model.dao.formulary;

import java.util.ArrayList;
import java.util.List;

import br.org.amigosdoautista.cadastroautista.model.schemas.formulary.QuestionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionDao {

    private String description;
    private String additionalDescription;
    private QuestionType questionType;
    private Integer sequence;
    private Boolean mandatory;

    @Builder.Default
    private List<QuestionItemDao> questionItems = new ArrayList<>();

}
