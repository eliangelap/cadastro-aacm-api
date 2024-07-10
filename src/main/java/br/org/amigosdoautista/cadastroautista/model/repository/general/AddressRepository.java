package br.org.amigosdoautista.cadastroautista.model.repository.general;

import org.springframework.data.jpa.repository.JpaRepository;

import br.org.amigosdoautista.cadastroautista.model.schemas.general.AddressSchema;

public interface AddressRepository extends JpaRepository<AddressSchema, Integer> {

}
