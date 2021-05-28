package br.com.zupacademy.armando.mercadolivre.fakers;

public class NotifyFiscalDepartmentRequest {

    private String purchaseCode;
    private Long purchaserId;

    public NotifyFiscalDepartmentRequest(String purchaseCode, Long purchaserId) {
        this.purchaseCode = purchaseCode;
        this.purchaserId = purchaserId;
    }

    @Override
    public String toString() {
        return "NotifyMarketingDPRequest{" +
                "purchaseCode='" + purchaseCode + '\'' +
                ", purchaserId=" + purchaserId +
                '}';
    }

}
