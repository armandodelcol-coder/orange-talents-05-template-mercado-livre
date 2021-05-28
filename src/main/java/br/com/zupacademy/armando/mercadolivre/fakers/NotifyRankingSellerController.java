package br.com.zupacademy.armando.mercadolivre.fakers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotifyRankingSellerController {

    @PostMapping("/api/notifica-ranking-sellers")
    public void send(@RequestBody NotifyRankingSellerRequest notifyRankingSellerRequest) {
        System.out.println("Notificação enviada para a equipe de marketing");
        System.out.println(notifyRankingSellerRequest.toString());
    }

}
