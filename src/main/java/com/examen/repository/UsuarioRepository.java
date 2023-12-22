package com.examen.repository;

import com.examen.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Map;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {
    @Query(
            value = "SELECT * FROM usuario u where u.email = :email",
            nativeQuery = true
    )
    Optional<Usuario> findByEmail(String email);
}
