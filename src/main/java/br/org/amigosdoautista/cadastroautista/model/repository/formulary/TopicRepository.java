package br.org.amigosdoautista.cadastroautista.model.repository.formulary;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.org.amigosdoautista.cadastroautista.model.schemas.formulary.TopicSchema;
import br.org.amigosdoautista.cadastroautista.model.schemas.formulary.VersionSchema;
import br.org.amigosdoautista.cadastroautista.model.schemas.formulary.id.TopicID;

public interface TopicRepository extends JpaRepository<TopicSchema, TopicID> {

    List<TopicSchema> findByVersion(VersionSchema version);

}
