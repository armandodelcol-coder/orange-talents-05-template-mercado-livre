package br.com.zupacademy.armando.mercadolivre.transactions.controllers;

import br.com.zupacademy.armando.mercadolivre.core.notifiers.NotifyByEmail;
import br.com.zupacademy.armando.mercadolivre.core.usefulclasses.ErrorResponseBody;
import br.com.zupacademy.armando.mercadolivre.purchases.entities.Purchase;
import br.com.zupacademy.armando.mercadolivre.purchases.enums.Gateway;
import br.com.zupacademy.armando.mercadolivre.purchases.repository.PurchaseRepository;
import br.com.zupacademy.armando.mercadolivre.transactions.entities.Transaction;
import br.com.zupacademy.armando.mercadolivre.transactions.enums.TransactionStatus;
import br.com.zupacademy.armando.mercadolivre.transactions.gateways.statusmappers.PayPalStatusMapper;
import br.com.zupacademy.armando.mercadolivre.transactions.helpers.NotificationEmailData;
import br.com.zupacademy.armando.mercadolivre.transactions.requests.NewTransactionPayPalRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
public class NewPayPalTransactionController {

    @Autowired
    private PurchaseRepository purchaseRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private NotifyByEmail notifyByEmail;

    @PostMapping("/api/transacao/paypal")
    @Transactional
    public ResponseEntity<?> register(@RequestBody @Valid NewTransactionPayPalRequest newTransactionPayPalRequest) {
        Purchase purchase = purchaseRepository.getByCode(newTransactionPayPalRequest.getPurchaseCode());
        TransactionStatus transactionStatus = PayPalStatusMapper.statusMap.get(newTransactionPayPalRequest.getStatus());
        if (transactionStatus == null) {
            return ResponseEntity.badRequest().body(
                    new ErrorResponseBody(
                            "Status não encontrado",
                            "O status informado não foi encontrado no sistema, entre em contato com o administrator."
                    )
            );
        }

        Transaction newTransaction = new Transaction(
                transactionStatus,
                purchase,
                newTransactionPayPalRequest.getTransactionCode(),
                Gateway.PayPal
        );
        entityManager.persist(newTransaction);
        transactionStatus.setPurchaseStatus(purchase);
        purchaseRepository.save(purchase);

        transactionStatus.sendNotificationsRequests(purchase);

        NotificationEmailData notificationEmailData = transactionStatus.generateNotificationEmailData(newTransaction);
        notifyByEmail.sendNotification(
                "mercadolivre",
                notificationEmailData.getSubject(),
                notificationEmailData.getBodyMessage(),
                notificationEmailData.getDestinyEmail()
        );

        return ResponseEntity.ok(transactionStatus);
    }

}
