/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gomi.business;

import java.util.Properties;
import java.util.Random;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Administrador
 */
public class RandomPasswordGenerator {

    //Caracteres permitidos em uma senha
    static Character[] vetor
            = {
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
                'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
                '!', '@', '#', '$', '%', '&', '*', '-'
            };

    //Quantidade de campos em uma senha
    public static String geraSenha() {
        StringBuilder senha = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 8; i++) {
            senha.append(vetor[random.nextInt(vetor.length)]);
        }
        return senha.toString();
    }

    //Enviar email, o email automatico sendo do endereço "gomiprojeto@gmail.com"
    public static void enviarEmail(String email, String senha, String nome) {
        Properties props = new Properties();
        /**
         * Par�metros de conexão com servidor Gmail
         */
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("gomiprojeto@gmail.com", "projectgomi");
            }
        });

        /**
         * Ativa Debug para sessão
         */
        session.setDebug(true);

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("gomiprojeto@gmail.com"));//Remetente

            Address[] toUser = InternetAddress.parse(email);

            message.setRecipients(Message.RecipientType.TO, toUser);
            message.setSubject("Sua nova senha da plataforma Gomi");//Assunto
            message.setText("Olá " + nome + "!\n\n Você solicitou a renovação de sua senha na plataforma Gomi!\nSegue abaixo a sua senha\n\n" + senha + "\n\nInsira essa senha na próxima vez que fizer login.\nNão se esqueça de alterar a senha caso não deseje mais utilizar essa\n\nAtenciosamente,\nEquipe Gomi\n\nIt's Dangerous to go alone! Take This: -|----");

            /**
             * Método para enviar a mensagem criada
             */
            Transport.send(message);

            System.out.println("Feito!!!");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
