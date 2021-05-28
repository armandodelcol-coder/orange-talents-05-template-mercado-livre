package br.com.zupacademy.armando.mercadolivre.transactions.gateways.statusmappers;

import br.com.zupacademy.armando.mercadolivre.transactions.enums.TransactionStatus;

import java.util.HashMap;
import java.util.Map;

public abstract class PagSeguroStatusMapper {

    public static Map<String, TransactionStatus> statusMap;
    static {
        statusMap = new HashMap<>();
        statusMap.put("SUCESSO", TransactionStatus.Sucesso);
        statusMap.put("ERRO", TransactionStatus.Falha);
    }


}
