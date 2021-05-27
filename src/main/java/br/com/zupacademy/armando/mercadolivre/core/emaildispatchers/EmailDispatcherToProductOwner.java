package br.com.zupacademy.armando.mercadolivre.core.emaildispatchers;

import br.com.zupacademy.armando.mercadolivre.core.notifiers.NotifyByEmail;
import br.com.zupacademy.armando.mercadolivre.products.entities.Question;
import br.com.zupacademy.armando.mercadolivre.purchases.entities.Purchase;
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

    public void sendPurchaseNotification(Purchase newPurchase) {
        this.notifier.sendNotification(
                newPurchase.getPurchaserName(),
                "Nova Compra - " + newPurchase.getProductName(),
                "Você recebeu uma nova compra de " + newPurchase.getQuantity() + " unidades do produto " + newPurchase.getProductName(),
                newPurchase.getProductOwnerName()
        );
    }

}
