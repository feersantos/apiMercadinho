package com.apiMercadinho.repositories;

import com.apiMercadinho.domain.Transacao.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransacaoRepositorio extends JpaRepository<Transacao, Long> {
}
