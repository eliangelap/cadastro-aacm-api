package br.org.amigosdoautista.cadastroautista.service.formulary;

import java.util.List;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import br.org.amigosdoautista.cadastroautista.model.repository.formulary.QuestionItemRepository;
import br.org.amigosdoautista.cadastroautista.model.schemas.formulary.QuestionSchema;
import br.org.amigosdoautista.cadastroautista.model.schemas.formulary.QuestionItemSchema;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@Validated
@RequiredArgsConstructor
public class QuestionItemService {

    private final QuestionItemRepository questionItemRepository;

    public QuestionItemSchema createNewQuestionItem(@NonNull @Valid QuestionItemSchema questionItem) {
        return questionItemRepository.save(questionItem);
    }

    public QuestionItemSchema createNewQuestionItem(@Valid QuestionItemSchema questionItem,
            @Valid QuestionSchema question) {
        questionItem.setQuestion(question);
        return questionItemRepository.save(questionItem);
    }

    public List<QuestionItemSchema> listByQuestion(QuestionSchema question) {
        return questionItemRepository.findByQuestion(question);
    }

}
