package br.org.amigosdoautista.cadastroautista.model.schemas.person;

import br.org.amigosdoautista.cadastroautista.model.types.PhoneType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "pes_telefone")
public class PhoneSchema {

    private static final String PES_TELEFONE_SEQ = "pes_telefone_seq";

    @Id
    @Column(name = "id_telefone")
    @SequenceGenerator(name = PES_TELEFONE_SEQ, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = PES_TELEFONE_SEQ)
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_pessoa_telefone"), name = "id_pessoa", referencedColumnName = "id_pessoa")
    private PersonSchema person;

    @NotNull(message = "É obrigatório informar o DDD")
    @Column(name = "nr_ddd", nullable = false)
    private Integer areaCode;

    @NotNull(message = "É obrigatório informar o Telefone")
    @Column(name = "nr_telefone", nullable = false)
    private Integer phoneNumber;

    @NotNull(message = "É obrigatório informar o Tipo de Telefone")
    @Column(name = "tp_telefone", nullable = false)
    @Enumerated(EnumType.STRING)
    private PhoneType phoneType;

}
