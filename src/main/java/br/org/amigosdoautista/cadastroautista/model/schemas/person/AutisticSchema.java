package br.org.amigosdoautista.cadastroautista.model.schemas.person;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "pes_autista")
public class AutisticSchema {

    @Id
    @Column(name = "id_autista")
    @SequenceGenerator(name = "autista_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer idAutistic;

    @Id
    @OneToOne(optional = false)
    @JoinColumn(name = "id_pessoafisica", referencedColumnName = "id_pessoafisica", foreignKey = @ForeignKey(name = "fk_pessoafisica_autista"))
    private IndividualEntitySchema individualEntity;

    @OneToOne(optional = true)
    @JoinColumns(value = {
            @JoinColumn(name = "id_pessoamae", referencedColumnName = "id_pessoa"),
            @JoinColumn(name = "id_mae", referencedColumnName = "id_pessoafisica")
    }, foreignKey = @ForeignKey(name = "fk_pessoafisica_mae"))
    private IndividualEntitySchema motherPerson;

    @OneToOne(optional = true)
    @JoinColumns(value = {
            @JoinColumn(name = "id_pessoapai", referencedColumnName = "id_pessoa"),
            @JoinColumn(name = "id_pai", referencedColumnName = "id_pessoafisica")
    }, foreignKey = @ForeignKey(name = "fk_pessoafisica_pai"))
    private IndividualEntitySchema fatherPerson;

    @OneToOne(optional = true)
    @JoinColumns(value = {
            @JoinColumn(name = "id_pessoaresponsavel", referencedColumnName = "id_pessoa"),
            @JoinColumn(name = "id_responsavel", referencedColumnName = "id_pessoafisica")
    }, foreignKey = @ForeignKey(name = "fk_pessoafisica_responsavel"))
    private IndividualEntitySchema guardianPerson;

}
