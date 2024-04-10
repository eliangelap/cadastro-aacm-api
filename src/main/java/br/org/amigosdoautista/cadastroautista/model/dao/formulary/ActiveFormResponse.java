package br.org.amigosdoautista.cadastroautista.model.dao.formulary;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActiveFormResponse {

    private Integer id;
    private String name;
    private Boolean canceled;
    private ActiveVersionResponse activeVersion;

}
