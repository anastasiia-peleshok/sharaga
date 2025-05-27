package com.example.sharagasystem.utility;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.codec.digest.DigestUtils;

@Getter
@Setter
public class WayforpayRequest {
    private String merchantAccount;
    private String merchantDomainName;
    private String orderReference;
    private long orderDate;
    private String amount;
    private String currency;
    private String[] productName;
    private String[] productCount;
    private String[] productPrice;
    private String serviceUrl;
    private String returnUrl;
    private String merchantSignature;
    private String apiVersion = "1";
    private String transactionType ="CREATE_INVOICE";

}
