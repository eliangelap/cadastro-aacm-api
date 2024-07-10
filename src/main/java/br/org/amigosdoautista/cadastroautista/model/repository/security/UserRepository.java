package br.org.amigosdoautista.cadastroautista.model.repository.security;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.org.amigosdoautista.cadastroautista.model.schemas.security.UserSchema;

@Repository
public interface UserRepository extends JpaRepository<UserSchema, Integer> {

    Optional<UserSchema> findByEmail(String email);

}
