package com.marcossousadev.projeto_cadastro_usuarios.business;

import com.marcossousadev.projeto_cadastro_usuarios.infrastructure.entitys.repository.Usuario;
import com.marcossousadev.projeto_cadastro_usuarios.infrastructure.entitys.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.repository = usuarioRepository;
    }

    public void saveUser(Usuario usuario) {
        // salva e fecha a conexão com o banco de dados
        repository.saveAndFlush(usuario);
    }

    public Usuario getUserByEmail(String email) {
        return repository.findByEmail(email).orElseThrow(() ->
                new RuntimeException("Esse usuário não existe!")
        );
    }

    public void deleteUserByEmail(String email) {
        repository.deleteByEmail(email);
    }

    // dentro JPA não existe um método de atualizar de um dado por id
    // usamos por exemplo o sava
    // então se queremos atualizar apenas o email, temos que tomar cuidado, para não apagar os outros dados

    public void updateUserByEmail(String email, Usuario usuario) {
        // como o JPA não tem um método de update próprio
        // primeiro buscamos os dados desse usuário apartir do email
        // usando esse método que criamos no nosso service, que possui já tratamento de exceção
        Usuario userEntity = getUserByEmail(email);
        Usuario userNewData = Usuario.builder().
                email(email).
                nome(usuario.getNome() != null ? usuario.getNome() : userEntity.getNome()).
                id(userEntity.getId()).
                build();

    }
    public void updateUserById(Integer id, Usuario usuario) {
        Usuario userEntity = repository.findById(id).orElseThrow(() ->
                new RuntimeException("Usuário não existente")
                );
        Usuario userNewData = Usuario.builder()
                .email(usuario.getEmail() != null ? usuario.getEmail() : userEntity.getEmail())
                .nome( usuario.getNome() != null ? usuario.getNome() : userEntity.getNome())
                .id(userEntity.getId())
                .build();

        repository.saveAndFlush(userNewData);
    }
}
