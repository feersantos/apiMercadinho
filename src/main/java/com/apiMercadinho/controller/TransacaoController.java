package com.apiMercadinho.controller;

import com.apiMercadinho.domain.Transacao.Transacao;
import com.apiMercadinho.dtos.TransacaoDTO;
import com.apiMercadinho.services.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transacao")
public class TransacaoController {

    @Autowired //instanciar automaticamente a implementação
    private TransacaoController transacaoController;

    @Autowired
    private TransacaoService transactionService;

    @PostMapping
    public ResponseEntity<Transacao> criacaoTransacao(@RequestBody TransacaoDTO transacao) throws Exception{
        Transacao novaTransacao = this.transactionService.criacaoTransacao(transacao);
        return new ResponseEntity<>(novaTransacao, HttpStatus.OK);
    }
}
