package br.org.amigosdoautista.cadastroautista.model.dto.general;

import lombok.Data;

@Data
public class StateResponse {

    private Integer id;
    private String name;
    private Integer ibgeNumber;
    private String uf;

}
