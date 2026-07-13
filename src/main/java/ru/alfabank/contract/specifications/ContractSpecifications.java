package ru.alfabank.contract.specifications;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import ru.alfabank.configs.TestConfig;

public final class ContractSpecifications {

    private ContractSpecifications() {
    }

    public static RequestSpecification requestSpec(String token) {
        return new RequestSpecBuilder()
                .setBaseUri(TestConfig.getBaseUrl())
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .addHeader("Authorization", "Bearer " + token)
                .addHeader("A-userId", TestConfig.getHeaderUserId())
                .addHeader("A-customerId", TestConfig.getHeaderCustomerId())
                .addHeader("A-clientType", TestConfig.getHeaderClientType())
                .addHeader("A-channelId", TestConfig.getHeaderChannelId())
                .build();
    }

    public static ResponseSpecification responseSpec201Json() {
        return new ResponseSpecBuilder()
                .expectStatusCode(201)
                .expectContentType(ContentType.JSON)
                .build();
    }

    public static ResponseSpecification responseSpec201() {
        return new ResponseSpecBuilder()
                .expectStatusCode(201)
                .build();
    }
}