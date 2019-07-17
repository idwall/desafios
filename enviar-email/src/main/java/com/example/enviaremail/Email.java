package com.example.enviaremail;

import lombok.Data;

@Data
public class Email {
    String usuarioEmail;
    String senha;
    String caminhoArquivo;
    String nomeArquivo;
    String destino;
    String assunto;
    String mensagem;


}
