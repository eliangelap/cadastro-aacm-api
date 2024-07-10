package br.org.amigosdoautista.cadastroautista.model.schemas.formulary;

import java.time.LocalDateTime;

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
import lombok.Data;

@Data
@Entity
@Table(name = "cad_versao")
public class VersionSchema {

    private static final String CAD_VERSAO_SEQ = "cad_versao_seq";

    @Id
    @Column(name = "id_versao")
    @SequenceGenerator(name = CAD_VERSAO_SEQ, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = CAD_VERSAO_SEQ)
    private Integer idVersion;

    @ManyToOne(optional = false)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_versao_formulario"), name = "id_formulario", referencedColumnName = "id_formulario")
    private FormSchema form;

    @Column(name = "in_cancelado", nullable = false)
    private Boolean canceled = Boolean.FALSE;

    @Column(name = "dt_criacao", nullable = false)
    private LocalDateTime creationDateTime = LocalDateTime.now();

}
