package br.com.zupacademy.armando.mercadolivre.purchases.enums;

public enum Gateway {

    PayPal {
        @Override
        public String getUrl(String purchaseCode) {
            return "paypal.com?buyerId=" + purchaseCode + "&redirectUrl=http://localhost:8080/api/transacao/paypal";
        }
    },
    PagSeguro {
        @Override
        public String getUrl(String purchaseCode) {
            return " pagseguro.com?returnId=" + purchaseCode + "&redirectUrl=http://localhost:8080/api/transacao/pagseguro";
        }
    };

    public abstract String getUrl(String purchaseCode);
}
