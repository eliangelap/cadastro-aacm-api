package br.org.amigosdoautista.cadastroautista.model.schemas.security;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.org.amigosdoautista.cadastroautista.model.schemas.person.IndividualEntitySchema;
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
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "seg_usuario")
public class UserSchema implements UserDetails {

    private static final String SEG_USUARIO_SEQ = "seg_usuario_seq";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEG_USUARIO_SEQ)
    @SequenceGenerator(name = SEG_USUARIO_SEQ, allocationSize = 1, initialValue = 2)
    @Column(name = "id_usuario")
    private Integer id;

    @OneToOne(optional = false)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_pessoafisica_usuario"), name = "id_pessoafisica", referencedColumnName = "id_pessoafisica")
    private IndividualEntitySchema individualEntity;

    @Column(name = "ds_email", nullable = false, length = 100, unique = true)
    @Size(max = 100, message = "E-mail deve possuir até 100 caracteres")
    @Email(message = "Forneça um e-mail válido")
    @NotBlank(message = "É obrigatório informar um e-mail")
    private String email;

    @Column(name = "ds_senha", nullable = false, length = 250)
    @NotBlank(message = "É obrigatório informar uma senha")
    private String password;

    @Builder.Default
    @Column(name = "in_cancelado", nullable = false)
    private Boolean canceled = Boolean.FALSE;

    @Builder.Default
    @Column(name = "dt_cadastro", nullable = false)
    private LocalDateTime creationDateTime = LocalDateTime.now();

    @Column(name = "dt_expiracao", nullable = true)
    private LocalDate expirationDate;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ADMIN"));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return expirationDate == null || expirationDate.isAfter(LocalDate.now());
    }

    @Override
    public boolean isAccountNonLocked() {
        return !canceled;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return expirationDate == null || expirationDate.isAfter(LocalDate.now());
    }

    @Override
    public boolean isEnabled() {
        return !canceled;
    }

}
