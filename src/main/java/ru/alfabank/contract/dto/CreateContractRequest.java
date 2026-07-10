package ru.alfabank.contract.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class CreateContractRequest {

    private Long programId;
    private String contractNumber;

    private String signDate;
    private String beginDate;
    private String endDate;

    private Integer duration;
    private String paymentType;

    private BigDecimal insuranceSum;
    private BigDecimal insurancePremium;

    private String debitAccount;
    private String sellerId;
    private String sellerChannel;

    private String contractLink;
    private String policyLink;
    private String agreementLink;

    private AccountOwner owner;

    private List<AccountInsuranceObject> insuranceObjects;
}