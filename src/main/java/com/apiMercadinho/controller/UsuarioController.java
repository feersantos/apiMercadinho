package com.apiMercadinho.controller;

import com.apiMercadinho.domain.Usuarios.Usuario;
import com.apiMercadinho.dtos.UsuarioDTO;
import com.apiMercadinho.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    //criar usuários
    @PostMapping
    public ResponseEntity<Usuario> criacaoUsuario(@RequestBody UsuarioDTO usuario){
        Usuario novoUsuario = usuarioService.criacaoUsuario(usuario);
        return new ResponseEntity<>(novoUsuario, HttpStatus.CREATED);
    }

    //listar usuários
    @GetMapping
    public ResponseEntity<List<Usuario>> getAllUsuarios(){
        List<Usuario> usuarios = this.usuarioService.getAllUsuarios();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }
}
