package ru.yandex.practicum.scooter;

import io.restassured.response.ValidatableResponse;
import static io.restassured.RestAssured.given;

public class CourierClient extends Client {

    private static final String COURIER_CREATE_PATH = "/api/v1/courier";
    private static final String COURIER_LOGIN_PATH = "/api/v1/courier/login";
    private static final String COURIER_DELETE_PATH = "/api/v1/courier/";


    public ValidatableResponse create(Courier courier) {
        return given()
                .spec(getSpec())
                .body(courier)
                .when()
                .post(COURIER_CREATE_PATH)
                .then();
    }

    public ValidatableResponse login(CourierCredentials CourierCredentials) {
        return given()
                .spec(getSpec())
                .body(CourierCredentials)
                .when()
                .post(COURIER_LOGIN_PATH)
                .then();
    }

    public ValidatableResponse delete(int id) {
        return given()
                .spec(getSpec())
                .when()
                .delete(COURIER_DELETE_PATH + id)
                .then();
    }
}
