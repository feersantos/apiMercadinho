package com.apiMercadinho.domain.Transacao;

import com.apiMercadinho.domain.Usuarios.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = "transacoes") //entidade
@Table(name = "transacoes") //tabela do banco
@Getter
@Setter
@AllArgsConstructor //aceita todos os campos da classe como argumentos
@NoArgsConstructor //gera automaticamente um construtor sem argumentos
@EqualsAndHashCode(of = "id") //considerar o id
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //valor gerado automaticamente
    private long id;

    private BigDecimal conta;

    @ManyToOne //um cliente pode ter várias transações, mas uma transação só pode ter um lojista
    @JoinColumn(name = "cliente_id")
    private Usuario cliente;

    @ManyToOne
    @JoinColumn(name = "lojista_id")
    private Usuario lojista;

    private LocalDateTime horaTransacao;

}
