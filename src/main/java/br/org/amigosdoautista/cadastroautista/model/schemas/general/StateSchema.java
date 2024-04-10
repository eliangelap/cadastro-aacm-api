package br.org.amigosdoautista.cadastroautista.model.schemas.general;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "ger_estado")
public class StateSchema implements Serializable {

    @Id
    @Column(name = "id_estado")
    @SequenceGenerator(name = "estado_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "nm_estado", length = 50, nullable = false)
    @Size(max = 50, message = "Nome do estado deve ter até 50 caracteres")
    @NotBlank(message = "Nome do estado é obrigatório")
    private String name;

    @Column(name = "nr_ibge", nullable = false, unique = true)
    @NotNull(message = "Código do IBGE é obrigatório")
    private Integer ibgeNumber;

    @Column(name = "cd_uf", length = 2, nullable = false, unique = true)
    @NotBlank(message = "UF é obrigatório")
    private String uf;

}
