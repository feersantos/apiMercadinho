package com.apiMercadinho.dtos;

import java.math.BigDecimal;

public record TransacaoDTO(BigDecimal valor, Long clienteId, Long lojistaId)  {

}
