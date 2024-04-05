package br.org.amigosdoautista.cadastroautista.model.schemas.person;

import org.hibernate.validator.constraints.br.CNPJ;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@Entity
@Table(name = "pes_pessoajuridica")
public class LegalEntitySchema {

    @Id
    @ManyToOne(optional = false)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_pessoafisica_pessoa"), name = "id_pessoa", referencedColumnName = "id_pessoa")
    private PersonSchema person;

    @NotEmpty(message = "É obrigatório informar um CNPJ")
    @CNPJ(message = "Forneça um CNPJ válido")
    @Column(name = "nr_cnpj", nullable = false, length = 14)
    private String cnpj;

}
