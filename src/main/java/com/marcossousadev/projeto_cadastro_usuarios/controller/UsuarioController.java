package com.marcossousadev.projeto_cadastro_usuarios.controller;

import com.marcossousadev.projeto_cadastro_usuarios.business.UsuarioService;
import com.marcossousadev.projeto_cadastro_usuarios.infrastructure.entitys.repository.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// existem três forma de injetar uma dependência
@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UsuarioController {
    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody Usuario body) {
        usuarioService.saveUser(body);
        return ResponseEntity.ok().build();
    }

    @GetMapping()
    public ResponseEntity<Usuario> getUserByEmail(@RequestParam String email) {
        return ResponseEntity.ok(usuarioService.getUserByEmail(email));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteUserByEmail(@RequestParam String email) {
        usuarioService.deleteUserByEmail(email);
        return ResponseEntity.ok().build();
    }
    @PutMapping
    public ResponseEntity<Void> updateUserByEmail(@RequestParam Integer id, @RequestBody Usuario body){
        usuarioService.updateUserById(id, body);
        return ResponseEntity.ok().build();
    }
}
