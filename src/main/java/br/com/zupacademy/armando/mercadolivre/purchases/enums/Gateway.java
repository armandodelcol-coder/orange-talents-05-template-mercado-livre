package br.com.zupacademy.armando.mercadolivre.purchases.enums;

public enum Gateway {

    PayPal {
        @Override
        public String getUrl(String purchaseCode) {
            return "paypal.com?buyerId=" + purchaseCode + "&redirectUrl=https://zupacademy/ot5/ml/purchases/success";
        }
    },
    PagSeguro {
        @Override
        public String getUrl(String purchaseCode) {
            return " pagseguro.com?returnId=" + purchaseCode + "&redirectUrl=https://zupacademy/ot5/ml/purchases/success";
        }
    };

    public abstract String getUrl(String purchaseCode);
}
