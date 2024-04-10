package br.org.amigosdoautista.cadastroautista.service.general;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import br.org.amigosdoautista.cadastroautista.model.dao.general.CityResponse;
import br.org.amigosdoautista.cadastroautista.model.repository.general.CityRepository;
import br.org.amigosdoautista.cadastroautista.model.schemas.general.CitySchema;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CityService {

    private final CityRepository cityRepository;

    public CityResponse createNewCity(CityResponse cityResponse) {
        ModelMapper mapper = new ModelMapper();
        CitySchema citySchema = mapper.map(cityResponse, CitySchema.class);

        cityRepository.save(citySchema);

        return mapper.map(citySchema, CityResponse.class);
    }

}
