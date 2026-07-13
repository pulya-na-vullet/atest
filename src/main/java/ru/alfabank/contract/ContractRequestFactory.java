package ru.alfabank.contract;

import ru.alfabank.configs.TestConfig;
import ru.alfabank.contract.dto.AccountInsuranceObject;
import ru.alfabank.contract.dto.AccountOwner;
import ru.alfabank.contract.dto.CreateContractRequest;

import java.time.OffsetDateTime;
import java.util.List;

public class ContractRequestFactory {

    public static CreateContractRequest accountRequest(String contractNumber, String ownerId) {
        OffsetDateTime now = OffsetDateTime.now().withNano(0);

        return CreateContractRequest.builder()
                .programId(TestConfig.getAccountProgramId())
                .contractNumber(contractNumber)
                .signDate(now.toString())
                .beginDate(now.toString())
                .endDate(now.plusYears(1).toString())
                .duration(TestConfig.getAccountDuration())
                .paymentType(TestConfig.getAccountPaymentType())
                .insuranceSum(TestConfig.getAccountInsuranceSum())
                .insurancePremium(TestConfig.getAccountInsurancePremium())
                .debitAccount(TestConfig.getDebitAccountNumber())
                .sellerId(TestConfig.getSellerId())
                .sellerChannel(TestConfig.getAccountSellerChannel())
                .contractLink(TestConfig.getAccountContractLink())
                .policyLink(TestConfig.getAccountPolicyLink())
                .agreementLink(TestConfig.getAccountAgreementLink())
                .owner(accountOwner(ownerId))
                .insuranceObjects(List.of(accountInsuranceObject()))
                .build();
    }

    private static AccountOwner accountOwner(String ownerId) {
        return AccountOwner.builder()
                .ownerId(ownerId)
                .inn(TestConfig.getAccountOwnerInn())
                .phoneNumber(TestConfig.getAccountOwnerPhoneNumber())
                .email(TestConfig.getAccountOwnerEmail())
                .legalAddress(TestConfig.getAccountOwnerLegalAddress())
                .build();
    }

    private static AccountInsuranceObject accountInsuranceObject() {
        return AccountInsuranceObject.builder()
                .paymentAccount(TestConfig.getAccountPaymentAccount())
                .build();
    }
}
