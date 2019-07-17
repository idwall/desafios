package com.example.enviaremail;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EnviarEmail {

    Properties emailProperties;
    Session mailSession;
    MimeMessage emailMessage;

    public static void main(String args[]) throws AddressException,
            MessagingException {

        EnviarEmail javaEmail = new EnviarEmail();

        javaEmail.setMailServerProperties();
        javaEmail.createEmailMessage();
        javaEmail.sendEmail();
    }

    public void setMailServerProperties() {

        String emailPort = "587";//gmail smtp porta

        //definindo Propriedades do email
        emailProperties = System.getProperties();
        emailProperties.put("mail.smtp.port", emailPort);
        emailProperties.put("mail.smtp.auth", "true");
        emailProperties.put("mail.smtp.starttls.enable", "true");

    }

    //Criando mensafem de email, defidindo o desrinatário, assunto e mensagem
    public void createEmailMessage() throws AddressException,
            MessagingException {
        String[] toEmails = { "vittoriaborotto@gmail.com" };
        String emailSubject = "Java Email";
        String emailBody = "In the beginning God created the heavens and   the  earth.   Now  the  earth  was formless  and empty,  darkness was  over the  surface of the deep, and the Spirit of  God was  hovering over  the  waters. And  God said, Let there be light, and there  was light. God saw that the light was  good, and  he separated  the  light from  the darkness. God called the light day, and  the   darkness  he  called night. And  there  was  evening,  and there  was  morning  -  the  first  day.";
        String novoTexto = "";
        int contadorQntLetras = 0;
        int limiteLinha = 40;

        //limitando quantidade de caracteres por linha
        for( int i = 0; i < emailBody.length(); i++ ) {
            novoTexto += emailBody.charAt(i);
            contadorQntLetras++;

            if( contadorQntLetras >= limiteLinha && emailBody.charAt(i) == ' ') {
                contadorQntLetras = 0;
                novoTexto += "\n";
            }
        }
        System.out.println(novoTexto);

        //Tentativa de Justificar o texto
//        String linhaAtual = "";
//        while(emailBody.hasNext()) {
//            if ((linhaAtual.length() + emailBody.length() + 1) > 40) {
//                int espacosFaltantes = 40 - linhaAtual.length();
//                // aqui voce faz um for na string, acha os espacos e vai adicionando
//                // espaco novo e fazendo espacosFaltantes--;
//
//                // depois continua na proxima linha:
//                linhaAtual = emailBody; // criou nova linha
//            } else {
//                linhaAtual = linhaAtual + " " + emailBody;
//            }
//        }

            mailSession = Session.getDefaultInstance(emailProperties, null);
            emailMessage = new MimeMessage(mailSession);


            for (int i = 0; i < toEmails.length; i++) {
                emailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmails[i]));
            }

            //Inserindo o Assunto e corpo de texto
            emailMessage.setSubject(emailSubject);
            emailMessage.setContent(emailBody, "text/html");//para email html
            //emailMessage.setText(emailBody);// para um texto de e-mail
    }

    //Metodo de envio do email, definindo Host e Id do email, alem de senha de app
    public void sendEmail() throws AddressException, MessagingException {

        String emailHost = "smtp.gmail.com";
        String fromUser = "vittoriaborotto";//id sem o @endereço.com
        String fromUserEmailPassword = "makvsonilkqoxcne"; //senha de applicação gerada pelo host

        Transport transport = mailSession.getTransport("smtp");

        transport.connect(emailHost, fromUser, fromUserEmailPassword);
        transport.sendMessage(emailMessage, emailMessage.getAllRecipients());
        transport.close();
        System.out.println("Email sent successfully.");
    }

}

