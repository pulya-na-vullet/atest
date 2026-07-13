package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.alfabank.auth.clients.AuthClient;
import ru.alfabank.configs.TestConfig;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Epic("Аутентификация")
@Feature("Получение токена")
@Story("Негативные сценарии")
public class AuthTokenTests {

    private final AuthClient authClient = new AuthClient();

    @Test
    @DisplayName("Получение токена - 400 неверный grant_type")
    @Description("Проверка, что при неверном grant_type сервис возвращает статус 400 и ошибку unsupported_grant_type.")
    public void getToken_invalidGrantType_returns400AndUnsupportedGrantType() {
        Response response = authClient.requestToken(
                "test",
                TestConfig.getClientId(),
                TestConfig.getClientSecret()
        );

        response.then()
                .log().ifValidationFails()
                .statusCode(400);

        assertEquals(
                "unsupported_grant_type",
                response.jsonPath().getString("error"),
                "Ожидался код ошибки unsupported_grant_type"
        );

        String errorDescription = response.jsonPath().getString("error_description");
        assertNotNull(errorDescription, "error_description не должен быть null");
        assertFalse(errorDescription.isBlank(), "error_description не должен быть пустым");
    }
}
