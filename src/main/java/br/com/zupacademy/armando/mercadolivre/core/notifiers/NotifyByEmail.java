package br.com.zupacademy.armando.mercadolivre.core.notifiers;

public interface NotifyByEmail {

    default void sendNotification(String messageFrom,
                                  String subject,
                                  String messageBody,
                                  String destinyEmail) {}

}
