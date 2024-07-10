package br.org.amigosdoautista.cadastroautista.model.dto.general;

import br.org.amigosdoautista.cadastroautista.model.types.AddressType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AddressDTO {

    private Integer id;

    @NotNull(message = "É obrigatório informar o tipo de endereço.")
    private AddressType type;

    @NotEmpty(message = "É obrigatório informar o endereço.")
    @Size(max = 100, message = "Endereço deve ter até 100 caracteres")
    private String streetAddress;

    @Size(max = 100, message = "Complemento deve ter até 100 caracteres")
    private String streetAddressLine2;

    @NotEmpty(message = "É obrigatório informar o bairro.")
    @Size(max = 40, message = "Bairro deve ter até 40 caracteres")
    private String neighbourhood;

    @NotEmpty(message = "É obrigatório informar o CEP.")
    @Size(max = 8, min = 8, message = "CEP deve ter 8 dígitos")
    private String postalCode;

    @NotNull(message = "É obrigatório informar a cidade.")
    private CityDTO city;
}
