package br.com.zupacademy.armando.mercadolivre.products.emaildispatchers;

import br.com.zupacademy.armando.mercadolivre.core.notifiers.NotifyByEmail;
import br.com.zupacademy.armando.mercadolivre.products.entities.Question;
import org.springframework.stereotype.Component;

@Component
public class EmailDispatcherToProductOwner {

    private NotifyByEmail notifier;

    public EmailDispatcherToProductOwner(NotifyByEmail notifier) {
        this.notifier = notifier;
    }

    public void sendQuestion(Question question) {
        this.notifier.sendNotification(
                question.getUserName(),
                "Pergunta - " + question.getProductName(),
                question.getTitle(),
                question.getProductOwnerName()
        );
    }

}
