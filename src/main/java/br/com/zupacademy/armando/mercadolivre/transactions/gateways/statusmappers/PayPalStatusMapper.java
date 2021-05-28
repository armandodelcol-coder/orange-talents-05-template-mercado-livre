package br.com.zupacademy.armando.mercadolivre.transactions.gateways.statusmappers;

import br.com.zupacademy.armando.mercadolivre.transactions.enums.TransactionStatus;

import java.util.HashMap;
import java.util.Map;

public abstract class PayPalStatusMapper {

    public static Map<String, TransactionStatus> statusMap;
    static {
        statusMap = new HashMap<>();
        statusMap.put("0", TransactionStatus.Falha);
        statusMap.put("1", TransactionStatus.Sucesso);
    }


}
