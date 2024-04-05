package br.org.amigosdoautista.cadastroautista.model.schemas.person;

import java.io.Serializable;
import java.time.LocalDate;

import org.hibernate.validator.constraints.br.CPF;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "pes_pessoafisica")
public class IndividualEntitySchema implements Serializable {

    @Id
    @ManyToOne(optional = false)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_pessoa_pessoafisica"), name = "id_pessoa", referencedColumnName = "id_pessoa")
    private PersonSchema person;

    @NotEmpty(message = "É obrigatório informar um nome")
    @Size(max = 100, message = "Nome Pessoa pode ter até 100 caracteres")
    @Column(name = "nm_pessoa", nullable = false, length = 100)
    private String name;

    @NotEmpty(message = "É obrigatório informar um CPF")
    @CPF(message = "Forneça um CPF válido")
    @Size(max = 11, min = 11, message = "CPF deve ter 11 dígitos")
    @Column(name = "nr_cpf", nullable = false, length = 11)
    private String cpf;

    @NotEmpty(message = "É obrigatório informar um RG")
    @Size(max = 13, min = 7, message = "RG deve ter de 7 até 13 dígitos")
    @Column(name = "nr_rg", nullable = false, length = 13)
    private String rg;

    @NotEmpty(message = "É obrigatório informar a data de nascimento")
    @Past(message = "Foneça uma data de nascimento válida")
    @Column(name = "dt_nascimento", nullable = false)
    private LocalDate birthDate;

    @NotEmpty(message = "É obrigatório informar o sexo")
    @Column(name = "tp_sexo", nullable = false)
    @Enumerated(EnumType.STRING)
    private SexType sex;

    @PastOrPresent(message = "Forneça uma data de óbito válida")
    @Column(name = "dt_obito", nullable = true)
    private LocalDate deathDate;

    @OneToOne(optional = true)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_pessoafisica_mae"), name = "id_mae", referencedColumnName = "id_pessoa")
    private IndividualEntitySchema motherPerson;

    @OneToOne(optional = true)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_pessoafisica_pai"), name = "id_pai", referencedColumnName = "id_pessoa")
    private IndividualEntitySchema fatherPerson;

}
