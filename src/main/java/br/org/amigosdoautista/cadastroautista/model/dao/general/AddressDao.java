package br.org.amigosdoautista.cadastroautista.model.dao.general;

import br.org.amigosdoautista.cadastroautista.model.types.AddressType;
import lombok.Data;

@Data
public class AddressDao {

    private Integer id;
    private AddressType type;
    private String streetAddress;
    private String streetAddressLine2;
    private String neighbourhood;
    private String postalCode;
    private CityResponse city;
}
