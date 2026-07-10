package ru.alfabank.contract.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountInsuranceObject {
    private String paymentAccount;
}
