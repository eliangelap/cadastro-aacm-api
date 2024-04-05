package br.org.amigosdoautista.cadastroautista.model.dto.security;

import java.time.LocalDateTime;

import br.org.amigosdoautista.cadastroautista.model.schemas.person.IndividualEntitySchema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    private Integer id;
    private IndividualEntitySchema individualEntity;
    private String email;

    @Builder.Default
    private Boolean canceled = Boolean.FALSE;

    @Builder.Default
    private LocalDateTime creationDateTime = LocalDateTime.now();
}
