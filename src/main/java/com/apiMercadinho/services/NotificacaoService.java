package com.apiMercadinho.services;


import com.apiMercadinho.domain.Usuarios.Usuario;
import org.springframework.stereotype.Service;

@Service
public class NotificacaoService {

        public void enviaNotificacao(Usuario usuario, String mensagem){
            String email = usuario.getEmail();

            System.out.println(email+mensagem); //enviar a mensagem
        }
}
