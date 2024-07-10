package br.org.amigosdoautista.cadastroautista.service.formulary;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import br.org.amigosdoautista.cadastroautista.model.dto.formulary.ActiveFormResponse;
import br.org.amigosdoautista.cadastroautista.model.dto.formulary.ActiveVersionResponse;
import br.org.amigosdoautista.cadastroautista.model.dto.formulary.FormRequest;
import br.org.amigosdoautista.cadastroautista.model.dto.formulary.QuestionDTO;
import br.org.amigosdoautista.cadastroautista.model.dto.formulary.QuestionItemDTO;
import br.org.amigosdoautista.cadastroautista.model.dto.formulary.ShortFormResponse;
import br.org.amigosdoautista.cadastroautista.model.dto.formulary.TopicDTO;
import br.org.amigosdoautista.cadastroautista.model.repository.formulary.FormRepository;
import br.org.amigosdoautista.cadastroautista.model.schemas.formulary.FormSchema;
import br.org.amigosdoautista.cadastroautista.model.schemas.formulary.QuestionSchema;
import br.org.amigosdoautista.cadastroautista.model.schemas.formulary.QuestionItemSchema;
import br.org.amigosdoautista.cadastroautista.model.schemas.formulary.TopicSchema;
import br.org.amigosdoautista.cadastroautista.model.schemas.formulary.VersionSchema;
import br.org.amigosdoautista.cadastroautista.web.error.BadRequestException;
import br.org.amigosdoautista.cadastroautista.web.error.DataNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@Validated
@RequiredArgsConstructor
public class FormService {

    private final FormRepository formRepository;
    private final VersionService versionService;
    private final TopicService topicService;
    private final QuestionService questionService;
    private final QuestionItemService questionItemService;

    @Transactional
    public ShortFormResponse createNew(@Valid FormRequest formToSave) {
        ModelMapper mapper = new ModelMapper();
        FormSchema form = mapper.map(formToSave, FormSchema.class);

        if (form == null) {
            throw new BadRequestException("Não foi possível salvar o formulário.");
        }

        FormSchema savedForm = formRepository.save(form);
        VersionSchema version = versionService.createNewVersion(savedForm);

        saveTopics(formToSave, version);

        return mapper.map(savedForm, ShortFormResponse.class);
    }

    public List<ShortFormResponse> getForms() {
        ModelMapper mapper = new ModelMapper();

        List<FormSchema> formSchemas = formRepository.findAll();
        List<ShortFormResponse> shortForms = new ArrayList<>();

        formSchemas.forEach(formSchema -> shortForms.add(mapper.map(formSchema, ShortFormResponse.class)));

        return shortForms;
    }

    public ActiveFormResponse getActiveFormWithQuestions(@NonNull Integer idForm) {
        FormSchema form = formRepository.findById(idForm)
                .orElseThrow(() -> new DataNotFoundException(idForm));

        ModelMapper mapper = new ModelMapper();
        ActiveFormResponse activeForm = mapper.map(form, ActiveFormResponse.class);

        // Buscando a versão ativa
        VersionSchema version = versionService.getActiveVersion(form);
        ActiveVersionResponse activeVersion = mapper.map(version, ActiveVersionResponse.class);

        activeForm.setActiveVersion(activeVersion);

        // Listando os tópicos da versão
        topicService.listTopicsByVersion(version)
                .forEach(topic -> {
                    TopicDTO topicDTO = mapper.map(topic, TopicDTO.class);
                    activeVersion.getTopics().add(topicDTO);

                    // Listando as questões
                    questionService.listByTopic(topic)
                            .forEach(question -> {
                                QuestionDTO questionDTO = mapper.map(question, QuestionDTO.class);
                                topicDTO.getQuestions().add(questionDTO);

                                // Listando as questão itens
                                questionItemService.listByQuestion(question)
                                        .forEach(questionItem -> {
                                            QuestionItemDTO questionItemDTO = mapper.map(questionItem,
                                                    QuestionItemDTO.class);
                                            questionDTO.getQuestionItems().add(questionItemDTO);
                                        });
                            });
                });

        return activeForm;
    }

    @Transactional
    public ShortFormResponse updateForm(@NonNull Integer idForm, @Valid FormRequest formRequest) {
        ModelMapper mapper = new ModelMapper();

        FormSchema formToUpdate = formRepository.findById(idForm).orElseThrow(() -> new DataNotFoundException(idForm));
        VersionSchema version = versionService.createNewVersion(formToUpdate);

        formToUpdate.setName(formRequest.getName());
        saveTopics(formRequest, version);

        return mapper.map(formToUpdate, ShortFormResponse.class);
    }

    public ShortFormResponse cancelForm(@NonNull Integer idForm) {
        ModelMapper mapper = new ModelMapper();
        FormSchema formSchema = formRepository.findById(idForm).orElseThrow(() -> new DataNotFoundException(idForm));
        formSchema.setCanceled(true);

        return mapper.map(formRepository.save(formSchema), ShortFormResponse.class);
    }

    private void saveTopics(@Valid FormRequest formRequest, VersionSchema version) {
        ModelMapper mapper = new ModelMapper();
        formRequest.getTopics().forEach(topicDto -> {
            TopicSchema topic = mapper.map(topicDto, TopicSchema.class);
            topic.setVersion(version);

            TopicSchema savedTopic = topicService.createNewTopic(topic);

            topicDto.getQuestions().forEach(questionDto -> {
                QuestionSchema question = mapper.map(questionDto, QuestionSchema.class);
                question.setTopic(savedTopic);

                QuestionSchema savedQuestion = questionService.createNewQuestion(question);

                questionDto.getQuestionItems().forEach(itemDto -> {
                    QuestionItemSchema item = mapper.map(itemDto, QuestionItemSchema.class);
                    item.setQuestion(savedQuestion);
                    questionItemService.createNewQuestionItem(item);
                });
            });
        });
    }

}
