package br.edu.ifpb.praticas.service;

import br.edu.ifpb.praticas.model.Bid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import static java.lang.Thread.sleep;

/**
 * Created by <a href="http://dijalmasilva.github.io" target="_blank">dijalma</a> on 06/05/17.
 */
@Service
public class EmailTask {

    private JavaMailSender mailSender;

    @Autowired
    public EmailTask(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }


    public void prepareAndSend(String recipient, String subject, String message) {

        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);

            messageHelper.setFrom("projetostestesifpb@gmail.com");
            messageHelper.setTo(recipient);
            messageHelper.setSubject(subject);
            messageHelper.setText(message, true);
        };

        Runnable run = new Runnable() {
            private int count = 0;
            private boolean send = false;

            @Override
            public void run() {

                while (!send && count++ < 20) {
                    try {
                        mailSender.send(messagePreparator);
                        send = true;
                    } catch (MailException e) {
                        e.printStackTrace();
                    }

                    try {
                        sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        new Thread(run).start();
    }


    public void sendEMailToUpdateStatusProvider(String recipient) {

        String subject = "BICO - Conta ativada!";
        String txtHtml = "<div>" +
                "<h2>Sua conta foi ativada e agora você pode utilizar nosso sistema!</h2>" +
                "<h3>Para acessar o sistema <a href='http://localhost:8080/' target='_blank'>clique aqui</a>!</h3>" +
                "</div>";

        prepareAndSend(recipient, subject, txtHtml);
    }

    public void sendEmailToProvidersRefusedBid(String recipient, Bid bid) {

        String subject = "BICO - Resultado da proposta!";
        String txtHtml = "<div>" +
                "<h2>Infelizmente outra proposta foi aceita para o serviço do cliente "
                + bid.getJob().getClient().getName() + bid.getJob().getClient().getLastName() + "." +
                "</h2>" +
                "<label>Detalhes: " + bid.getJob().getDetails() + "</label>" +
                "</div>";

        prepareAndSend(recipient, subject, txtHtml);
    }

    public void sendEmailToProvidersAccept(String recipient, Bid bid) {

        String subject = "BICO - Resultado da proposta!";
        String txtHtml = "<div>" +
                "<h2>Parabéns! A sua proposta foi aceita para o serviço do cliente "
                + bid.getJob().getClient().getName() + bid.getJob().getClient().getLastName() + "." +
                "</h2>" +
                "<label>Detalhes: " + bid.getJob().getDetails() + "</label>" +
                "</div>";

        prepareAndSend(recipient, subject, txtHtml);
    }

    public void sendEmailProviderToRememberAboutDateOfJob(String recipient, String nameClient, String nameProvider) {

        String subject = "BICO - Lembrete do serviço!";
        String txtHtml = "<div>" +
                "<h2>Olá, " + nameProvider + "! </h2>" +
                "<h3>Amanhã está marcado o serviço com o(a) cliente " + nameClient + "!</h3>" +
                "</div>";

        prepareAndSend(recipient, subject, txtHtml);
    }

    public void sendEmailClientToRememberAboutDateOfJob(String recipient, String nameProvider, String nameClient) {

        String subject = "BICO - Lembrete do serviço!";
        String txtHtml = "<div>" +
                "<h2>Olá, " + nameClient + "! </h2>" +
                "<h3>Amanhã está marcado o serviço com o(a) Prestador(a) de servico, " + nameProvider + "!</h3>" +
                "</div>";

        prepareAndSend(recipient, subject, txtHtml);
    }
}
