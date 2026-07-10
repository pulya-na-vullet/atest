package ru.alfabank.contract.clients;

import io.restassured.response.Response;
import ru.alfabank.configs.Endpoints;
import ru.alfabank.contract.dto.ContractNumberRequest;
import ru.alfabank.contract.dto.CreateContractRequest;
import ru.alfabank.contract.specifications.ContractSpecifications;

import static io.restassured.RestAssured.given;

public class ContractClient {

    public Response createContract(String token, CreateContractRequest request) {
        return given()
                .spec(ContractSpecifications.requestSpec(token))
                .body(request)
                .when()
                .post(Endpoints.CONTRACT_PROGRAMS)
                .then()
                .extract()
                .response();
    }

    public Response generateContractNumber(String token, Long programId) {
        ContractNumberRequest request = ContractNumberRequest.builder()
                .programId(programId)
                .build();

        return given()
                .spec(ContractSpecifications.requestSpec(token))
                .body(request)
                .when()
                .post(Endpoints.CONTRACT_NUMBER_GENERATION)
                .then()
                .extract()
                .response();
    }
}
