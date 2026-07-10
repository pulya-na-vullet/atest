package ru.alfabank.contract.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountOwner {
    private String ownerId;
    private String inn;
    private String phoneNumber;
    private String email;
    private String legalAddress;
}