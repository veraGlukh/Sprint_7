package ru.yandex.practicum.scooter;

import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;

public class OrderListTest {

    private OrderClient orderClient;

    @Before
    public void setUp() {
        orderClient = new OrderClient();
    }

    // Тест 1 Идея проверки: - в тело ответа возвращается список заказов.

    @Test
    @DisplayName("Check status code and order list return of GET /api/v1/orders") // имя теста
    @Description("Basic test for GET /api/v1/orders")
    public void orderListIsNotEmpty(){
        ValidatableResponse listResponse = orderClient.list();

        int statusCode = listResponse.extract().statusCode();
        assertEquals("Код ответа не соответствует:", SC_OK, statusCode);

        assertThat(listResponse.extract().path("orders"), notNullValue());
    }
}
