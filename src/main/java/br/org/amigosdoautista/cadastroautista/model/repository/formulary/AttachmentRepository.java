package br.org.amigosdoautista.cadastroautista.model.repository.formulary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.org.amigosdoautista.cadastroautista.model.schemas.formulary.AttachmentSchema;
import br.org.amigosdoautista.cadastroautista.model.schemas.formulary.id.AttachmentID;

@Repository
public interface AttachmentRepository extends JpaRepository<AttachmentSchema, AttachmentID> {

}
