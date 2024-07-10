package br.org.amigosdoautista.cadastroautista.model.schemas.formulary;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
@Entity
@Table(name = "cad_topico")
public class TopicSchema {

    private static final String CAD_TOPICO_SEQ = "cad_topico_seq";

    @Id
    @Column(name = "id_topico")
    @SequenceGenerator(name = CAD_TOPICO_SEQ, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = CAD_TOPICO_SEQ)
    private Integer idTopic;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_versao", referencedColumnName = "id_versao", foreignKey = @ForeignKey(name = "fk_topico_versao"))
    private VersionSchema version;

    @NotBlank(message = "É obrigatório informar um título")
    @Column(name = "nm_topico", nullable = false, length = 250)
    private String name;

    @NotNull(message = "É obrigatório informar uma sequência numérica")
    @Positive(message = "É obrigatório informar um número maior que zero para a sequência")
    @Column(name = "nr_ordem", nullable = false)
    private Integer sequence;

    @Column(name = "in_cancelado", nullable = false)
    private Boolean canceled = Boolean.FALSE;

}
