package br.com.zupacademy.armando.mercadolivre.fakers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotifyFiscalDPController {

    @PostMapping("/api/notifica-fiscal")
    public void send(@RequestBody NotifyFiscalDepartmentRequest notifyFiscalDepartmentRequest) {
        System.out.println("Notificação enviada para o departmento fiscal");
        System.out.println(notifyFiscalDepartmentRequest.toString());
    }

}
