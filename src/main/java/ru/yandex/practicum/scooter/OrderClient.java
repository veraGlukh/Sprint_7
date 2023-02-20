package ru.yandex.practicum.scooter;

import io.restassured.response.ValidatableResponse;
import static io.restassured.RestAssured.given;

public class OrderClient extends Client {

    private static final String ORDER_CREATE_PATH = "/api/v1/orders";
    private static final String ORDER_LIST_PATH = "/api/v1/orders";
    private static final String ORDER_CANCEL_PATH = "/api/v1/orders/cancel";

    public ValidatableResponse create(Order order) {
        return given()
                .spec(getSpec())
                .body(order)
                .when()
                .post(ORDER_CREATE_PATH)
                .then();
    }

    public ValidatableResponse list() {
        return given()
                .spec(getSpec())
                .when()
                .get(ORDER_LIST_PATH)
                .then();
    }

    public ValidatableResponse cancel(int track) {
        return given()
                .spec(getSpec())
                .when()
                .delete(ORDER_CANCEL_PATH + track)
                .then();
    }
}
