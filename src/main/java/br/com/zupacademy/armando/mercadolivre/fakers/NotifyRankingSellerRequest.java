package br.com.zupacademy.armando.mercadolivre.fakers;

public class NotifyRankingSellerRequest {

    private String purchaseCode;
    private Long productOwnerId;

    public NotifyRankingSellerRequest(String purchaseCode, Long productOwnerId) {
        this.purchaseCode = purchaseCode;
        this.productOwnerId = productOwnerId;
    }

    @Override
    public String toString() {
        return "NotifyMarketingDPRequest{" +
                "purchaseCode='" + purchaseCode + '\'' +
                ", productOwnerId=" + productOwnerId +
                '}';
    }

}
