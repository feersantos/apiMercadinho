package com.apiMercadinho.services;

import com.apiMercadinho.domain.Usuarios.Usuario;
import com.apiMercadinho.domain.Usuarios.TipoUsuario;
import com.apiMercadinho.dtos.UsuarioDTO;
import com.apiMercadinho.repositories.UsuarioRespositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRespositorio repositorio;

    public void validaTransacao(Usuario usuario, BigDecimal conta) throws Exception {
        if (usuario.getTipoUsuario() == TipoUsuario.LOJISTA) { //validacao para verificar se é LOJISTA
            throw new Exception("Lojistas NÃO podem realizar transações!");
        }
        if (usuario.getSaldo().compareTo(conta) < 0) { //verifica se o valor do balance for menor que conta
            throw new Exception("Saldo insuficiente.");
        }
    }

    public Usuario findUserById(Long id) throws Exception { //validacao para achar por Id
        return this.repositorio.findUserById(id).orElseThrow(() -> new Exception("Usuário não encontrado."));
    }

    //salva o usuario
    public Usuario criacaoUsuario(UsuarioDTO dto){
        Usuario novoUsuario = new Usuario(dto);
        this.salvaUsuario(novoUsuario);
        return novoUsuario;
    }
    public void salvaUsuario(Usuario usuario){
        this.repositorio.save(usuario); //salvar as alterações do usuário
    }

    //retorna a lista de usuário
    public List<Usuario> getAllUsuarios() {
       return this.repositorio.findAll();
    }
}