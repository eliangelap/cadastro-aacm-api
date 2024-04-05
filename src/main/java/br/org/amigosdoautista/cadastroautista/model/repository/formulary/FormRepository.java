package br.org.amigosdoautista.cadastroautista.model.repository.formulary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import br.org.amigosdoautista.cadastroautista.model.schemas.formulary.FormSchema;

import java.util.Optional;

public interface FormRepository extends JpaRepository<FormSchema, Integer> {

    FormSchema findByName(String name);

    @NonNull
    Optional<FormSchema> findById(@NonNull Integer id);

}
