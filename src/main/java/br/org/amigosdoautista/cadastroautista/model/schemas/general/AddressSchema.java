package br.org.amigosdoautista.cadastroautista.model.schemas.general;

import java.io.Serializable;

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
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "ger_endereco")
public class AddressSchema implements Serializable {

    @Id
    @SequenceGenerator(name = "endereco_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_endereco")
    private Integer id;

    @Column(name = "tp_endereco", nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private AddressType type;

    @Size(max = 100, message = "O tamanho máximo permitido para o endereço é 100 caracteres.")
    @NotBlank(message = "É obrigatório informar o endereço")
    @Column(name = "ds_logradouro", nullable = false, length = 100)
    private String streetAddress;

    @Size(max = 100, message = "O tamanho máximo permitido para o complemento é 100 caracteres.")
    @Column(name = "ds_complemento", nullable = true, length = 100)
    private String streetAddressLine2;

    @Size(max = 40, message = "O tamanho máximo permitido para o bairro é 40 caracteres.")
    @NotBlank(message = "É obrigatório informar o bairro")
    @Column(name = "ds_bairro", nullable = false, length = 40)
    private String neighbourhood;

    @Size(max = 8, min = 8, message = "CEP deve ter 8 dígitos.")
    @NotBlank(message = "É obrigatório informar o CEP")
    @Column(name = "nr_cep", nullable = false, length = 8)
    private String postalCode;

    @NotNull(message = "É obrigatório informar o município")
    @ManyToOne(optional = false, targetEntity = CitySchema.class)
    @JoinColumn(name = "id_municipio", referencedColumnName = "id_municipio", foreignKey = @ForeignKey(name = "fk_municipio_endereco"))
    private CitySchema city;

}
