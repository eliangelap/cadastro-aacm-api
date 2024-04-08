package br.org.amigosdoautista.cadastroautista.model.schemas.formulary;

import java.time.LocalDateTime;

import br.org.amigosdoautista.cadastroautista.model.schemas.formulary.id.AnswerID;
import br.org.amigosdoautista.cadastroautista.model.schemas.person.AutisticSchema;
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
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "cad_resposta")
@IdClass(AnswerID.class)
public class AnswerSchema {

    @Id
    @ManyToOne(optional = false)
    @JoinColumns(value = {
            @JoinColumn(name = "id_formulario", referencedColumnName = "id_formulario"),
            @JoinColumn(name = "id_versao", referencedColumnName = "id_versao"),
            @JoinColumn(name = "id_topico", referencedColumnName = "id_topico"),
            @JoinColumn(name = "id_questao", referencedColumnName = "id_questao")
    }, foreignKey = @ForeignKey(name = "fk_questao_resposta"))
    private QuestionSchema question;

    @Id
    @Column(name = "nr_seqresposta")
    @SequenceGenerator(name = "resposta_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer idAnswer;

    @ManyToOne(optional = true, targetEntity = QuestionItemSchema.class)
    @JoinColumns(value = {
            @JoinColumn(name = "id_formulario", referencedColumnName = "id_formulario"),
            @JoinColumn(name = "id_versao", referencedColumnName = "id_versao"),
            @JoinColumn(name = "id_topico", referencedColumnName = "id_topico"),
            @JoinColumn(name = "id_questao", referencedColumnName = "id_questao"),
            @JoinColumn(name = "id_item", referencedColumnName = "id_item", nullable = true)
    }, foreignKey = @ForeignKey(name = "fk_item_resposta"))
    private QuestionItemSchema questionItem;

    @ManyToOne(optional = false, targetEntity = AutisticSchema.class)
    @JoinColumn(name = "id_autista", referencedColumnName = "id_autista", foreignKey = @ForeignKey(name = "fk_autista_resposta"))
    private AutisticSchema autistic;

    @Size(max = 500, message = "O tamanho máximo permitido para a resposta é 500 caracteres.")
    @Column(name = "ds_resposta", length = 500, nullable = true)
    private String answerDescription;

    @Column(name = "dt_resposta", nullable = false)
    private LocalDateTime answerDateTime = LocalDateTime.now();

}
