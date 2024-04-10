package br.org.amigosdoautista.cadastroautista.model.dao.general;

import lombok.Data;

@Data
public class CityResponse {

    private Integer id;
    private String name;
    private Integer ibgeNumber;
    private StateResponse state;
}
