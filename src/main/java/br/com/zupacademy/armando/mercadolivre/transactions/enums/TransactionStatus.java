package br.com.zupacademy.armando.mercadolivre.transactions.enums;

import br.com.zupacademy.armando.mercadolivre.purchases.entities.Purchase;
import br.com.zupacademy.armando.mercadolivre.transactions.entities.Transaction;
import br.com.zupacademy.armando.mercadolivre.transactions.helpers.NotificationEmailData;
import br.com.zupacademy.armando.mercadolivre.transactions.notifications.NotifyFiscalDepartment;
import br.com.zupacademy.armando.mercadolivre.transactions.notifications.NotifyRankingSellers;

public enum TransactionStatus {

    Sucesso {
        @Override
        public NotificationEmailData generateNotificationEmailData(Transaction newTransaction) {
            StringBuilder bodyMessage = new StringBuilder();
            bodyMessage.append("Recebemos seu pagamento com sucesso.");
            bodyMessage.append("\n Em breve você receberá seu produto!!");
            bodyMessage.append("\n  Produto adquirido: " + newTransaction.getProductName());
            bodyMessage.append("\n Quantidade: " + newTransaction.getProductQuantity());
            bodyMessage.append("\n Vendedor: " + newTransaction.getProductOwner());
            return new NotificationEmailData(
                    "Pagamento Recebido",
                    bodyMessage.toString(),
                    newTransaction.getPurchaserEmail()
            );
        }

        @Override
        public void setPurchaseStatus(Purchase purchase) {
            purchase.statusIsFinalized();
        }

        @Override
        public void sendNotificationsRequests(Purchase purchase) {
            NotifyRankingSellers.sendNotification(purchase.getCode(), purchase.getProductOwnerId());
            NotifyFiscalDepartment.sendNotification(purchase.getCode(), purchase.getPurchaserId());
        }
    },
    Falha {
        @Override
        public NotificationEmailData generateNotificationEmailData(Transaction newTransaction) {
            StringBuilder bodyMessage = new StringBuilder();
            bodyMessage.append("Recebemos seu pagamento mas houve algum problema com ele =(.");
            bodyMessage.append("\n Produto: " + newTransaction.getProductName());
            bodyMessage.append("\n Quantidade: " + newTransaction.getProductQuantity());
            bodyMessage.append("\n Vendedor: " + newTransaction.getProductOwner());
            bodyMessage.append("\n Você pode tentar novamente acessando esse link: " + "http://fakelink.com/nova-tentativa-pagamento");
            return new NotificationEmailData(
                    "Falha ao efetuar pagamento",
                    bodyMessage.toString(),
                    newTransaction.getPurchaserEmail()
            );
        }

        @Override
        public void setPurchaseStatus(Purchase purchase) {
            purchase.statusIsFail();
        }

        @Override
        public void sendNotificationsRequests(Purchase purchase) {
            // No Notifications At Moment
        }

    };

    public abstract NotificationEmailData generateNotificationEmailData(Transaction newTransaction);

    public abstract void setPurchaseStatus(Purchase purchase);

    public abstract void sendNotificationsRequests(Purchase purchase);
}
