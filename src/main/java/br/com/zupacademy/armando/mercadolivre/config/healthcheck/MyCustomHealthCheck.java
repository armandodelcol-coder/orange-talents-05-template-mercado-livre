package br.com.zupacademy.armando.mercadolivre.config.healthcheck;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MyCustomHealthCheck implements HealthIndicator {

    @Override
    public Health health() {
        Map<String, Object> details = new HashMap<>();
        // Aqui eu posso colocar algumas informações de detalhes que julgar necessário.
        details.put("versão", "1.2.3");
        details.put("descrição", "Meu primeiro Health Check customizado!");
        details.put("endereço", "127.0.0.1");

        // Aqui é o status desse meu HealthCheck, aqui eu posso criar alguma lógica
        // para definir os possíveis status
        /*
            DOWN: Status indicando que o componente ou subsistema sofreu uma falha inesperada.
            OUT_OF_SERVICE: Status indicando que o componente ou subsistema foi retirado de serviço e não deve ser usado.
            UNKNOWN: Status indicando que o componente ou subsistema está em um estado desconhecido.
            UP: Status indicando que o componente ou subsistema está funcionando conforme o esperado.
         */
        // return Health.status(Status.UP).withDetails(details).build();
        return Health.status(Status.UNKNOWN).withDetails(details).build();
    }

}