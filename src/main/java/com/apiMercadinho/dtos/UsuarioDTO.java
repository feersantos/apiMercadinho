package com.apiMercadinho.dtos;

import java.math.BigDecimal;

public record UsuarioDTO(String nome,
                         String cpf,
                         BigDecimal saldo,
                         String email,
                         String senha,
                         String tipoUsuario) {
}
