package com.marcossousadev.projeto_cadastro_usuarios.infrastructure.repository;

import com.marcossousadev.projeto_cadastro_usuarios.infrastructure.entitys.Usuario;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    // toda vez que eu uso o optional
    // eu sou obrigado a criar uma exceção ou alternativa caso o email não exista
    Optional<Usuario> findByEmail(String email);
    // deve conter sempre o mesmo nome da coluna
    @Transactional // se der qualquer erro, ele não pode deletar o usuário!
    void deleteByEmail(String email);
}
