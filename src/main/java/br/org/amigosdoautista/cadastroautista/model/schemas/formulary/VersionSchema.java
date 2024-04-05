package br.org.amigosdoautista.cadastroautista.model.schemas.formulary;

import java.time.LocalDateTime;

import br.org.amigosdoautista.cadastroautista.model.schemas.formulary.id.VersionID;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "cad_versao")
@IdClass(VersionID.class)
public class VersionSchema {

    @Id
    @ManyToOne(optional = false)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_versao_formulario"), name = "id_formulario", referencedColumnName = "id_formulario")
    private FormSchema form;

    @Id
    @Column(name = "id_versao")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer idVersion;

    @Column(name = "in_cancelado", nullable = false)
    private Boolean canceled = Boolean.FALSE;

    @Column(name = "dt_criacao", nullable = false)
    private LocalDateTime creationDateTime = LocalDateTime.now();

}
