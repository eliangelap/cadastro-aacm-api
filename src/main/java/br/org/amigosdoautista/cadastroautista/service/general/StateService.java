package br.org.amigosdoautista.cadastroautista.service.general;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.org.amigosdoautista.cadastroautista.model.dao.general.IbgeCity;
import br.org.amigosdoautista.cadastroautista.model.dao.general.StateResponse;
import br.org.amigosdoautista.cadastroautista.model.repository.general.StateRepository;
import br.org.amigosdoautista.cadastroautista.model.schemas.general.StateSchema;
import br.org.amigosdoautista.cadastroautista.web.error.NotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StateService {

    private final StateRepository stateRepository;

    @Value("${api.ibge.states}")
    private String ibgeStatesApiUrl;

    public List<StateResponse> listAll() {
        ModelMapper mapper = new ModelMapper();

        List<StateSchema> stateSchemas = stateRepository.findAll(Sort.by("name").ascending());
        List<StateResponse> stateResponses = new ArrayList<>();

        stateSchemas.forEach(schema -> stateResponses.add(mapper.map(schema, StateResponse.class)));

        return stateResponses;
    }

    public List<IbgeCity> listAllCitiesFromState(String uf) {
        StateSchema stateSchema = stateRepository.findByUf(uf).orElseThrow(NotFoundException::new);

        String url = ibgeStatesApiUrl.replace("{UF}", String.valueOf(stateSchema.getIbgeNumber()));

        RestTemplate restTemplate = new RestTemplate();
        return List.of(restTemplate.getForObject(url, IbgeCity[].class));
    }

}
