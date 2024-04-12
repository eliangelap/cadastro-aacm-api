package br.org.amigosdoautista.cadastroautista.service.general;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import br.org.amigosdoautista.cadastroautista.model.dao.general.CityDao;
import br.org.amigosdoautista.cadastroautista.model.dao.general.StateResponse;
import br.org.amigosdoautista.cadastroautista.model.repository.general.CityRepository;
import br.org.amigosdoautista.cadastroautista.model.repository.general.StateRepository;
import br.org.amigosdoautista.cadastroautista.model.schemas.general.CitySchema;
import br.org.amigosdoautista.cadastroautista.web.error.DataNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CityService {

    private final CityRepository cityRepository;
    private final StateRepository stateRepository;

    public CityDao createNewCity(@Valid CityDao cityToSave) {
        ModelMapper mapper = new ModelMapper();
        CitySchema citySchema = mapper.map(cityToSave, CitySchema.class);

        CitySchema saved = cityRepository.save(citySchema);
        Integer stateId = saved.getState().getId();

        StateResponse stateResponse = mapper.map(
                stateRepository.findById(stateId).orElseThrow(() -> new DataNotFoundException(stateId)),
                StateResponse.class);
        CityDao cityResponse = mapper.map(saved, CityDao.class);
        cityResponse.setState(stateResponse);
        return cityResponse;
    }

    public CityDao getCityById(Integer id) {
        CitySchema citySchema = cityRepository.findById(id).orElseThrow(() -> new DataNotFoundException(id));

        ModelMapper mapper = new ModelMapper();
        return mapper.map(citySchema, CityDao.class);
    }

}
