package br.org.amigosdoautista.cadastroautista.model.dto.formulary;

import java.util.ArrayList;
import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FormRequest {

    @Size(max = 100, message = "O tamanho máximo permitido para o nome do formulário é 100 caracteres.")
    @NotEmpty(message = "É obrigatório informar um nome")
    private String name;

    @Builder.Default
    @NotEmpty(message = "É obrigatório informar uma lista de tópicos")
    private List<TopicDTO> topics = new ArrayList<>();

}
