package com.apiMercadinho.services;

import com.apiMercadinho.domain.Transacao.Transacao;
import com.apiMercadinho.domain.Usuarios.Usuario;
import com.apiMercadinho.dtos.TransacaoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Service
public class TransacaoService {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired

    private TransacaoService repositorio;

    @Autowired
    private RestTemplate template;

    @Autowired
    private NotificacaoService notificacaoService;

    public Transacao criacaoTransacao(TransacaoDTO transacao) throws Exception {

        Usuario usuario = this.usuarioService.findUserById(transacao.clienteId());
        Usuario lojista = this.usuarioService.findUserById(transacao.lojistaId());

        // Validar a transação de acordo com saldo do cliente
        usuarioService.validaTransacao(usuario, transacao.valor());

        boolean autorizada = this.autorizaTransacao(usuario, transacao.valor());
        if (!autorizada) {//se não estiver autorizado
            throw new Exception("Transação não autorizada!");
        }

        //criar uma nova instancia de transação, informando o valor, lojista, cliente e o horário da transaçao
        Transacao novaTransacao = new Transacao();
        novaTransacao.setConta(transacao.valor());
        novaTransacao.setCliente(usuario);
        novaTransacao.setLojista(lojista);

        //atualizar os saldos
        usuario.setSaldo(usuario.getSaldo().subtract(transacao.valor()));
        lojista.setSaldo(lojista.getSaldo().add(transacao.valor()));

        //salvar a transação feita
        usuarioService.salvaUsuario(usuario);
        usuarioService.salvaUsuario(lojista);

        //enviar os emails
        notificacaoService.enviaNotificacao(usuario, "Transação realizada!");
        notificacaoService.enviaNotificacao(lojista, "Transação recebida!");

        return novaTransacao;
    }


    public boolean autorizaTransacao(Usuario cliente, BigDecimal valor) {
        //criar um mapa" para representar a transaçao autorizada
        Map<String, Object> respostaAutorizada = new HashMap<>();
        respostaAutorizada.put("mensagem", "Autorizado");

        //resposta do http autorizado
        ResponseEntity<Map<String, Object>> responseEntity = new ResponseEntity<>(respostaAutorizada, HttpStatus.OK);

        // Validar a resposta, se der ok vai comparar a mensagem
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            Map<String, Object> responseBody = responseEntity.getBody();
            String mensagem = (String) responseBody.get("mensagem");
            return "Autorizado".equalsIgnoreCase(mensagem); //faz a comparação da string com o que tem na mensagem
        } else {
            return false;
        }
    }
}

