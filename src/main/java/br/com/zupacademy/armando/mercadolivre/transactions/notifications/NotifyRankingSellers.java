package br.com.zupacademy.armando.mercadolivre.transactions.notifications;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public abstract class NotifyRankingSellers {

    public static void sendNotification(String purchaseCode, Long productOwnerId) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/api/notifica-ranking-sellers";

        // create headers
        HttpHeaders headers = new HttpHeaders();
        // set `content-type` header
        headers.setContentType(MediaType.APPLICATION_JSON);
        // set `accept` header
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        // create a map for post parameters
        Map<String, Object> map = new HashMap<>();
        map.put("purchaseCode", purchaseCode);
        map.put("productOwnerId", productOwnerId);

        // build the request
        HttpEntity<Map<String, Object>> request = new HttpEntity<>(map, headers);

        restTemplate.postForLocation(url, request);
    }

}
