package br.com.zupacademy.armando.mercadolivre.transactions.helpers;

public class NotificationEmailData {

    private String subject;
    private String bodyMessage;
    private String destinyEmail;

    public NotificationEmailData(String subject, String bodyMessage, String destinyEmail) {
        this.subject = subject;
        this.bodyMessage = bodyMessage;
        this.destinyEmail = destinyEmail;
    }

    public String getSubject() {
        return subject;
    }

    public String getBodyMessage() {
        return bodyMessage;
    }

    public String getDestinyEmail() {
        return destinyEmail;
    }

}
