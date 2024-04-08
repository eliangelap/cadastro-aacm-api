package br.org.amigosdoautista.cadastroautista.model.schemas.formulary;

import br.org.amigosdoautista.cadastroautista.model.schemas.formulary.id.TopicID;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
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
@IdClass(TopicID.class)
public class TopicSchema {

    @Id
    @ManyToOne(optional = false)
    @JoinColumns(value = {
            @JoinColumn(name = "id_formulario", referencedColumnName = "id_formulario"),
            @JoinColumn(name = "id_versao", referencedColumnName = "id_versao")
    }, foreignKey = @ForeignKey(name = "fk_topico_versao"))
    private VersionSchema version;

    @Id
    @Column(name = "id_topico")
    @SequenceGenerator(name = "topico_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer idTopic;

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
