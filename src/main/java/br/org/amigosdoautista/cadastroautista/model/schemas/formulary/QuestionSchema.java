package br.org.amigosdoautista.cadastroautista.model.schemas.formulary;

import br.org.amigosdoautista.cadastroautista.model.schemas.formulary.id.QuestionID;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "cad_questao")
@IdClass(QuestionID.class)
public class QuestionSchema {

    @Id
    @ManyToOne(optional = false)
    @JoinColumns(value = {
            @JoinColumn(name = "id_formulario", referencedColumnName = "id_formulario"),
            @JoinColumn(name = "id_versao", referencedColumnName = "id_versao"),
            @JoinColumn(name = "id_topico", referencedColumnName = "id_topico")
    }, foreignKey = @ForeignKey(name = "fk_questao_topico"))
    private TopicSchema topic;

    @Id
    @Column(name = "id_questao")
    @SequenceGenerator(name = "questao_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer idQuestion;

    @Column(name = "tp_questao", nullable = false)
    @NotNull(message = "É obrigatório selecionar o tipo de questão")
    @Enumerated(EnumType.STRING)
    private QuestionType questionType;

    @Size(max = 250, message = "O tamanho máximo permitido para a descrição da questão é 250 caracteres.")
    @NotBlank(message = "É obrigatório informar uma descrição")
    @Column(name = "ds_questao", nullable = false, length = 250)
    private String description;

    @Size(max = 500, message = "O tamanho máximo permitido para o a descrição adicional é 500 caracteres.")
    @Column(name = "ds_adicional", nullable = true, length = 500)
    private String additionalDescription;

    @NotNull(message = "É obrigatório informar uma sequência")
    @Positive(message = "Sequência deve ser maior que zero")
    @Column(name = "nr_ordem", nullable = false)
    private Integer sequence;

    @Column(name = "in_obrigatorio", nullable = false)
    private Boolean mandatory = Boolean.FALSE;

    @Column(name = "in_cancelado", nullable = false)
    private Boolean canceled = Boolean.FALSE;

}
