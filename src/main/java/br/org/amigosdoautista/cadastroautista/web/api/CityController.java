package br.org.amigosdoautista.cadastroautista.web.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.amigosdoautista.cadastroautista.model.dao.general.CityResponse;
import br.org.amigosdoautista.cadastroautista.service.general.CityService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/city")
@RequiredArgsConstructor
public class CityController {

    private final CityService cityService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public CityResponse postMethodName(@RequestBody CityResponse cityResponse) {
        return cityService.createNewCity(cityResponse);
    }

}
