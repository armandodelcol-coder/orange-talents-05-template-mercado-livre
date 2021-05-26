package br.com.zupacademy.armando.mercadolivre.core.notifiers;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary
@Component
public class FakerEmailNotifier implements NotifyByEmail {

    public void sendNotification(String messageFrom,
                                 String subject,
                                 String messageBody,
                                 String destinyEmail) {
        // Montar e enviar o email...
        StringBuilder message = new StringBuilder();
        message.append("Você recebeu uma mensagem de " + messageFrom);
        message.append("\n com o assunto: " + subject);
        message.append("\n corpo da mensagem: " + messageBody);
        message.append("\n esse email seria enviado para " + destinyEmail);

        fakeShip(message.toString());
    }

    private void fakeShip(String message) {
        System.out.println(message);
    }

}
