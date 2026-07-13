package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.alfabank.configs.TestConfig;
import ru.alfabank.contract.ContractRequestFactory;
import ru.alfabank.contract.clients.ContractClient;
import ru.alfabank.contract.dto.ContractNumberResponse;
import ru.alfabank.contract.dto.CreateContractRequest;
import ru.alfabank.contract.specifications.ContractSpecifications;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("АБ.СтрахованиеУчет")
@Feature("Страховые договоры")
@Story("Генерация номера страхового договора")
public class CreateContractTests extends BaseApiTest {

    private final ContractClient contractClient = new ContractClient();

    @Test
    @DisplayName("Генерация номера страхового договора ACCOUNT - 201")
    @Description("Проверка, что сервис возвращает 201 и валидный contractNumber для программы ACCOUNT.")
    public void generateContractNumber_ACCOUNT_returns201AndValidContractNumber() {
        Response response = contractClient.generateContractNumber(token, TestConfig.getAccountProgramId());

        ContractNumberResponse contractNumberResponse = response.then()
                .log().ifValidationFails()
                .spec(ContractSpecifications.responseSpec201Json())
                .extract()
                .as(ContractNumberResponse.class);

        assertNotNull(contractNumberResponse.getContractNumber(), "contractNumber не должен быть null");
        assertFalse(contractNumberResponse.getContractNumber().isBlank(), "contractNumber не должен быть пустым");
        assertTrue(
                contractNumberResponse.getContractNumber().matches("^Z6922/888/ABR\\d{5,}/6$"),
                "contractNumber должен соответствовать формату ACCOUNT"
        );
    }

    @Test
    @DisplayName("Создание страхового договора ACCOUNT - 201")
    @Description("Проверка, что договор ACCOUNT успешно создается по валидному запросу.")
    public void createContract_ACCOUNT_returns201() {
        Response contractNumberResponse = contractClient.generateContractNumber(token, TestConfig.getAccountProgramId());

        String contractNumber = contractNumberResponse.then()
                .log().ifValidationFails()
                .spec(ContractSpecifications.responseSpec201Json())
                .extract()
                .as(ContractNumberResponse.class)
                .getContractNumber();

        assertNotNull(contractNumber, "contractNumber не должен быть null");
        assertFalse(contractNumber.isBlank(), "contractNumber не должен быть пустым");

        String ownerId = "TEST_OWNER_ACCOUNT_" + System.currentTimeMillis();

        CreateContractRequest request = ContractRequestFactory.accountRequest(contractNumber, ownerId);

        Response response = contractClient.createContract(token, request);

        response.then()
                .log().ifValidationFails()
                .spec(ContractSpecifications.responseSpec201());

        assertTrue(response.getBody().asString().isBlank(), "Тело ответа должно быть пустым");
    }
}