package br.org.amigosdoautista.cadastroautista.model.repository.general;

import org.springframework.data.jpa.repository.JpaRepository;

import br.org.amigosdoautista.cadastroautista.model.schemas.general.StateSchema;
import java.util.Optional;

public interface StateRepository extends JpaRepository<StateSchema, Integer> {

    Optional<StateSchema> findByUf(String uf);

}
