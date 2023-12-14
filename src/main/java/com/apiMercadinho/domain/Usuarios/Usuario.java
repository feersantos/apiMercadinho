package com.apiMercadinho.domain.Usuarios;

import com.apiMercadinho.dtos.UsuarioDTO;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity(name = "usuarios") //entidade
@Table(name = "usuarios") //tabela do banco
@Getter
@Setter
@AllArgsConstructor //aceita todos os campos da classe como argumentos
@NoArgsConstructor //gera automaticamente um construtor sem argumentos
@EqualsAndHashCode(of = "id")//considerar o id
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //valor gerado automaticamente
    private long id;

    private String nome;

    @Column(unique = true) //cpf unico
    private String cpf;

    @Column(unique = true) //email unico
    private String email;

    private String senha;

    @Enumerated(EnumType.STRING) //no bd, aparecer como string, tipo lojista/cliente
    private TipoUsuario tipoUsuario;

    private BigDecimal saldo;

    //criar instancias a partir dos dados do UsuarioDTO
    public Usuario(UsuarioDTO dto) {
        this.nome = dto.nome();
        this.cpf = dto.cpf();
        this.email = dto.email();
        this.senha = dto.senha();
        this.saldo = dto.saldo();
        this.tipoUsuario = TipoUsuario.valueOf(dto.tipoUsuario());
    }
}
