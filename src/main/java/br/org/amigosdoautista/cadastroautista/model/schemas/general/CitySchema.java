package br.org.amigosdoautista.cadastroautista.model.schemas.general;

import java.io.Serializable;

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
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "ger_municipio")
public class CitySchema implements Serializable {

    private static final String GER_MUNICIPIO_SEQ = "ger_municipio_seq";

    @Id
    @Column(name = "id_municipio")
    @SequenceGenerator(name = GER_MUNICIPIO_SEQ, allocationSize = 1, initialValue = 2)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = GER_MUNICIPIO_SEQ)
    private Integer id;

    @Column(name = "nm_municipio", length = 100, nullable = false)
    @Size(max = 100, message = "Nome do município deve ter até 100 caracteres")
    @NotBlank(message = "Nome do município é obrigatório")
    private String name;

    @Column(name = "nr_ibge", nullable = false, unique = true)
    @NotNull(message = "Código do IBGE é obrigatório")
    private Integer ibgeNumber;

    @ManyToOne(optional = false)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_estado_municipio"), name = "id_estado", referencedColumnName = "id_estado")
    @NotNull(message = "UF é obrigatório")
    private StateSchema state;
}
