package br.org.amigosdoautista.cadastroautista.model.schemas.formulary;

import br.org.amigosdoautista.cadastroautista.model.schemas.formulary.id.QuestionItemID;
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
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "cad_itemquestao")
@IdClass(QuestionItemID.class)
public class QuestionItemSchema {

    @Id
    @ManyToOne(optional = false)
    @JoinColumns(value = {
            @JoinColumn(name = "id_formulario", referencedColumnName = "id_formulario"),
            @JoinColumn(name = "id_versao", referencedColumnName = "id_versao"),
            @JoinColumn(name = "id_topico", referencedColumnName = "id_topico"),
            @JoinColumn(name = "id_questao", referencedColumnName = "id_questao")
    }, foreignKey = @ForeignKey(name = "fk_item_questao"))
    private QuestionSchema question;

    @Id
    @Column(name = "id_item")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer idQuestionItem;

    @Size(max = 500, message = "O tamanho máximo permitido para a descrição do item é 500 caracteres.")
    @NotBlank(message = "É obrigatório informar uma descrição")
    @Column(name = "ds_item", length = 500, nullable = false)
    private String description;

    @Column(name = "in_cancelado", nullable = false)
    private Boolean canceled = Boolean.FALSE;

}
