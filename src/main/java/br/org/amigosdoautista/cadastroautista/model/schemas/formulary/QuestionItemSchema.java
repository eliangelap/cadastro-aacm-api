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
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "cad_itemquestao")
public class QuestionItemSchema {

    private static final String CAD_ITEMQUESTAO_SEQ = "cad_itemquestao_seq";

    @Id
    @Column(name = "id_item")
    @SequenceGenerator(name = CAD_ITEMQUESTAO_SEQ, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = CAD_ITEMQUESTAO_SEQ)
    private Integer idQuestionItem;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_questao", referencedColumnName = "id_questao", foreignKey = @ForeignKey(name = "fk_item_questao"))
    private QuestionSchema question;

    @Size(max = 500, message = "O tamanho máximo permitido para a descrição do item é 500 caracteres.")
    @NotBlank(message = "É obrigatório informar uma descrição")
    @Column(name = "ds_item", length = 500, nullable = false)
    private String description;

    @Column(name = "in_cancelado", nullable = false)
    private Boolean canceled = Boolean.FALSE;

}
