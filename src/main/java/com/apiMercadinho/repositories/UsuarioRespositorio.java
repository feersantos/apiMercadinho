package com.apiMercadinho.repositories;

import com.apiMercadinho.domain.Usuarios.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//entidade e o tipo id
public interface UsuarioRespositorio extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findUserByCpf(String cpf); //metodo para pesquisar por cpf

    Optional<Usuario> findUserById(Long id); //metodo para pesquisar por id
}
