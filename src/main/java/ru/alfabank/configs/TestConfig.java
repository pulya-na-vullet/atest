package ru.alfabank.configs;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class TestConfig {

    private static final Properties PROPERTIES = new Properties();

    static {
            try (InputStream inputStream = TestConfig.class
                    .getClassLoader()
                    .getResourceAsStream("application.properties")) {

                if (inputStream == null) {
                    throw new IllegalStateException("Файл application.properties не найден");
                }

                PROPERTIES.load(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
    }

    private static String getRequiredProperty(String key) {
        String value = PROPERTIES.getProperty(key);

        if (value == null || value.isBlank()) {
            throw new IllegalStateException("Отсутствует обязательное свойство: " + key);
        }

        return value;
    }

    public static String getBaseUrl() {
        return getRequiredProperty("base.url");
    }

    public static String getClientId() {
        return getRequiredProperty("client.id");
    }

    public static String getClientSecret() {
        return getRequiredProperty("client.secret");
    }

    public static Long getAccountProgramId() {
        return Long.valueOf(getRequiredProperty("ab.contract.account-program-id"));
    }

    public static String getDebitAccountNumber() {
        return getRequiredProperty("ab.contract.debit-account-number");
    }

    public static String getSellerId() {
        return getRequiredProperty("ab.contract.seller-id");
    }

    public static Integer getAccountDuration() {
        return Integer.valueOf(getRequiredProperty("ab.contract.account.duration"));
    }

    public static String getAccountPaymentType() {
        return getRequiredProperty("ab.contract.account.payment-type");
    }

    public static BigDecimal getAccountInsuranceSum() {
        return new BigDecimal(getRequiredProperty("ab.contract.account.insurance-sum"));
    }

    public static BigDecimal getAccountInsurancePremium() {
        return new BigDecimal(getRequiredProperty("ab.contract.account.insurance-premium"));
    }

    public static String getAccountSellerChannel() {
        return getRequiredProperty("ab.contract.account.seller-channel");
    }

    public static String getAccountContractLink() {
        return getRequiredProperty("ab.contract.account.contract-link");
    }

    public static String getAccountPolicyLink() {
        return getRequiredProperty("ab.contract.account.policy-link");
    }

    public static String getAccountAgreementLink() {
        return getRequiredProperty("ab.contract.account.agreement-link");
    }

    public static String getAccountOwnerInn() {
        return getRequiredProperty("ab.contract.account.owner-inn");
    }

    public static String getAccountOwnerPhoneNumber() {
        return getRequiredProperty("ab.contract.account.owner-phone-number");
    }

    public static String getAccountOwnerEmail() {
        return getRequiredProperty("ab.contract.account.owner-email");
    }

    public static String getAccountOwnerLegalAddress() {
        return getRequiredProperty("ab.contract.account.owner-legal-address");
    }

    public static String getAccountPaymentAccount() {
        return getRequiredProperty("ab.contract.account.payment-account");
    }

    public static String getHeaderUserId() {
        return getRequiredProperty("ab.header.user-id");
    }

    public static String getHeaderCustomerId() {
        return getRequiredProperty("ab.header.customer-id");
    }

    public static String getHeaderClientType() {
        return getRequiredProperty("ab.header.client-type");
    }

    public static String getHeaderChannelId() {
        return getRequiredProperty("ab.header.channel-id");
    }
}
