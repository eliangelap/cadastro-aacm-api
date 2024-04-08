package br.org.amigosdoautista.cadastroautista.model.repository.formulary;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.org.amigosdoautista.cadastroautista.model.schemas.formulary.FormSchema;
import br.org.amigosdoautista.cadastroautista.model.schemas.formulary.VersionSchema;
import br.org.amigosdoautista.cadastroautista.model.schemas.formulary.id.VersionID;

import java.util.List;
import java.util.Optional;

@Repository
public interface VersionRepository extends JpaRepository<VersionSchema, VersionID> {

    VersionSchema findByCanceled(Boolean canceled);

    List<VersionSchema> findByForm(FormSchema form, Sort sort);

    @Query("select v from VersionSchema v where v.form.id = ?1 and v.canceled = false")
    Optional<VersionSchema> findActiveByForm(Integer formId);

}
