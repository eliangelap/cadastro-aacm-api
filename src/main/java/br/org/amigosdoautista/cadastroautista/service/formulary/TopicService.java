package br.org.amigosdoautista.cadastroautista.service.formulary;

import java.util.List;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import br.org.amigosdoautista.cadastroautista.model.repository.formulary.TopicRepository;
import br.org.amigosdoautista.cadastroautista.model.schemas.formulary.TopicSchema;
import br.org.amigosdoautista.cadastroautista.model.schemas.formulary.VersionSchema;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@Validated
@RequiredArgsConstructor
public class TopicService {

    private final TopicRepository topicRepository;

    public TopicSchema createNewTopic(@NonNull @Valid TopicSchema topic) {
        return topicRepository.save(topic);
    }

    public TopicSchema createNewTopic(@Valid TopicSchema topic, @Valid VersionSchema version) {
        topic.setVersion(version);
        return topicRepository.save(topic);
    }

    public List<TopicSchema> listTopicsByVersion(VersionSchema version) {
        return topicRepository.findByVersion(version);
    }

}
