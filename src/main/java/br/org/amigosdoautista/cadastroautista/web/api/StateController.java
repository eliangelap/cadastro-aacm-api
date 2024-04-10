package br.org.amigosdoautista.cadastroautista.web.api;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.amigosdoautista.cadastroautista.model.dao.general.IbgeCity;
import br.org.amigosdoautista.cadastroautista.model.dao.general.StateResponse;
import br.org.amigosdoautista.cadastroautista.service.general.StateService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/state")
@RequiredArgsConstructor
public class StateController {

    private final StateService stateService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<StateResponse> list() {
        return stateService.listAll();
    }

    @GetMapping(value = "/cities/{uf}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<IbgeCity> listCitiesByUf(@NonNull @PathVariable(name = "uf", required = false) String uf) {
        return stateService.listAllCitiesFromState(uf);
    }

}
