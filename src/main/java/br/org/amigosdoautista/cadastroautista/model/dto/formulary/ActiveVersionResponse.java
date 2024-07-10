package br.org.amigosdoautista.cadastroautista.model.dto.formulary;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActiveVersionResponse {

    private Integer idVersion;
    private Boolean canceled;
    private LocalDateTime creationDateTime;

    @Builder.Default
    private List<TopicDTO> topics = new ArrayList<>();

}
