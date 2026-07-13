package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.alfabank.configs.Endpoints;
import ru.alfabank.configs.TestConfig;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Epic("Аутентификация")
@Feature("Получение токена")
@Story("Негативные сценарии")
public class AuthTokenTests {

    @Test
    @DisplayName("Получение токена - 400 неверный grant_type")
    @Description("Проверка сценария из Postman: при неверном grant_type сервис возвращает 400 и unsupported_grant_type.")
    public void getToken_invalidGrantType_returns400AndUnsupportedGrantType() {
        Response response = given()
                .baseUri(TestConfig.getBaseUrl())
                .contentType(ContentType.URLENC)
                .formParam("grant_type", "test")
                .formParam("client_id", TestConfig.getClientId())
                .formParam("client_secret", TestConfig.getClientSecret())
                .when()
                .post(Endpoints.TOKEN)
                .then()
                .extract()
                .response();

        assertEquals(400, response.statusCode(), "Ожидался статус 400");
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
