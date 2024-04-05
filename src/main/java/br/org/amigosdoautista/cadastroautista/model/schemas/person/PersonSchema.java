package br.org.amigosdoautista.cadastroautista.model.schemas.person;

import java.io.Serializable;
import java.time.LocalDateTime;

import br.org.amigosdoautista.cadastroautista.model.schemas.general.AddressSchema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "pes_pessoa")
public class PersonSchema implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_pessoa")
    private Integer id;

    @ManyToOne(optional = false, targetEntity = AddressSchema.class)
    @JoinColumn(name = "id_endereco", referencedColumnName = "id_endereco", foreignKey = @ForeignKey(name = "fk_endereco_pessoa"))
    private AddressSchema address;

    @Column(name = "ds_email", nullable = true, length = 100)
    @Email(message = "Forneça um e-mail válido")
    @Size(max = 100, message = "E-mail deve possuir até 100 caracteres")
    private String email;

    @Column(name = "dt_cadastro", nullable = false)
    private LocalDateTime creationDateTime = LocalDateTime.now();

}
