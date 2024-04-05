package br.org.amigosdoautista.cadastroautista.model.schemas.person;

import br.org.amigosdoautista.cadastroautista.model.schemas.person.id.AutisticID;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
// @IdClass(AutisticID.class)
@Table(name = "pes_autista")
public class AutisticSchema {

    @Id
    @ManyToOne(optional = false)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_pessoafisica_autista"), name = "id_pessoa", referencedColumnName = "id_pessoa")
    private IndividualEntitySchema person;

    @OneToOne(optional = true)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_pessoafisica_responsavel"), name = "id_responsavel", referencedColumnName = "id_pessoa")
    private IndividualEntitySchema guardianPerson;

}
