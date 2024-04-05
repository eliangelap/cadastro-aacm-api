package br.org.amigosdoautista.cadastroautista.service.formulary;

import java.util.List;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import br.org.amigosdoautista.cadastroautista.model.repository.formulary.QuestionRepository;
import br.org.amigosdoautista.cadastroautista.model.schemas.formulary.QuestionSchema;
import br.org.amigosdoautista.cadastroautista.model.schemas.formulary.TopicSchema;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@Validated
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;

    public QuestionSchema createNewQuestion(@NonNull @Valid QuestionSchema question) {
        return questionRepository.save(question);
    }

    public QuestionSchema createNewQuestion(@Valid QuestionSchema question, @Valid TopicSchema topic) {
        question.setTopic(topic);
        return questionRepository.save(question);
    }

    public List<QuestionSchema> listByTopic(TopicSchema topic) {
        return questionRepository.findByTopic(topic);
    }

}
