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
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "cad_anexo")
public class AttachmentSchema {

    private static final String CAD_ANEXO_SEQ = "cad_anexo_seq";

    @Id
    @Column(name = "id_anexo")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = CAD_ANEXO_SEQ)
    @SequenceGenerator(name = CAD_ANEXO_SEQ, allocationSize = 1)
    private Integer idAttachment;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_questao", referencedColumnName = "id_questao", foreignKey = @ForeignKey(name = "fk_questao_anexo"))
    private QuestionSchema question;

    @Column(name = "in_cancelado", nullable = false)
    private Boolean canceled = Boolean.FALSE;

    @NotNull(message = "Anexo é obrigatório")
    @Column(name = "im_attachment", nullable = false)
    private byte[] content;

    @NotNull(message = "É obrigatório informar o nome do arquivo")
    @Column(name = "nm_arquivo", nullable = false, length = 100)
    private String name;

    @NotNull(message = "É obrigatório informar a extensão do arquivo")
    @Column(name = "ds_extensao", nullable = false, length = 10)
    private String extension;

    @Column(name = "ds_arquivo", nullable = true, length = 150)
    private String description;

}
