package br.org.amigosdoautista.cadastroautista.model.schemas.formulary;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "cad_formulario")
public class FormSchema {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_formulario")
    private Integer id;

    @Size(max = 100, message = "Nome do formulário deve ter até 100 caracteres")
    @NotBlank(message = "Informe um nome para o formulário")
    @Column(name = "nm_formulario", nullable = false, length = 100)
    private String name;

    @Column(name = "in_cancelado", nullable = false)
    private Boolean canceled = Boolean.FALSE;

}
