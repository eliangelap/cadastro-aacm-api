package br.org.amigosdoautista.cadastroautista.service.general;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.org.amigosdoautista.cadastroautista.model.dao.general.StateResponse;
import br.org.amigosdoautista.cadastroautista.model.repository.general.StateRepository;
import br.org.amigosdoautista.cadastroautista.model.schemas.general.StateSchema;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StateService {

    private final StateRepository stateRepository;

    public List<StateResponse> listAll() {
        ModelMapper mapper = new ModelMapper();

        List<StateSchema> stateSchemas = stateRepository.findAll(Sort.by("name").ascending());
        List<StateResponse> stateResponses = new ArrayList<>();

        stateSchemas.forEach(schema -> stateResponses.add(mapper.map(schema, StateResponse.class)));

        return stateResponses;
    }

}
