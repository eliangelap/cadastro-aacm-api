package br.org.amigosdoautista.cadastroautista.model.schemas.person;

import java.io.Serializable;

import org.hibernate.validator.constraints.br.CNPJ;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@Entity
@Table(name = "pes_pessoajuridica")
public class LegalEntitySchema implements Serializable {

    @Id
    @Column(name = "id_pessoajuridica")
    @SequenceGenerator(name = "pessoajuridica_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer idLegalEntity;

    @OneToOne(optional = false)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_pessoafisica_pessoa"), name = "id_pessoa", referencedColumnName = "id_pessoa")
    private PersonSchema person;

    @NotEmpty(message = "É obrigatório informar um CNPJ")
    @CNPJ(message = "Forneça um CNPJ válido")
    @Column(name = "nr_cnpj", nullable = false, length = 14)
    private String cnpj;

}
