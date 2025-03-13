package com.ifpi.biblioteca.entidades;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailUtil {

    public static void enviarEmail(String destinatario, String assunto, String corpo) {
        //configurações do servidor SMTP
        String host = "smtp.gmail.com"; // Substitua pelo host do seu provedor de e-mail
        String usuario = "blibliojava.spring@gmail.com"; // Seu e-mail
        String senha = "ffyw fwxi irzw uztq"; // Sua senha

        //propriedades para configurar a sessão
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587"); // Porta do SMTP (geralmente 587 para TLS)

        // Cria uma sessão com autenticação
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(usuario, senha);
            }
        });

        try {
            //cria a mensagem de e-mail
            Message mensagem = new MimeMessage(session);
            mensagem.setFrom(new InternetAddress(usuario));
            mensagem.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
            mensagem.setSubject(assunto);
            mensagem.setText(corpo);

            //envia o e-mail
            Transport.send(mensagem);

            System.out.println("E-mail enviado com sucesso para: " + destinatario);
        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("Erro ao enviar e-mail: " + e.getMessage());
        }
    }
}