package br.org.amigosdoautista.cadastroautista.model.schemas.person;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "pes_autista")
public class AutisticSchema {

    private static final String PES_AUTISTA_SEQ = "pes_autista_seq";

    @Id
    @Column(name = "id_autista")
    @SequenceGenerator(name = PES_AUTISTA_SEQ, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = PES_AUTISTA_SEQ)
    private Integer idAutistic;

    @OneToOne(optional = false)
    @JoinColumn(name = "id_pessoafisica", referencedColumnName = "id_pessoafisica", foreignKey = @ForeignKey(name = "fk_pessoafisica_autista"))
    private IndividualEntitySchema individualEntity;

    @OneToOne(optional = true)
    @JoinColumn(name = "id_responsavel", referencedColumnName = "id_pessoafisica", foreignKey = @ForeignKey(name = "fk_pessoafisica_responsavel"))
    private IndividualEntitySchema guardianPerson;

}
