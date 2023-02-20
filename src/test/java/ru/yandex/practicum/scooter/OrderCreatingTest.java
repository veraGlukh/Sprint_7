package ru.yandex.practicum.scooter;

import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import static org.apache.http.HttpStatus.SC_CREATED;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;

@RunWith(Parameterized.class)
public class OrderCreatingTest {

    /* Тест 1 Идеи проверки: - можно указать один из цветов — BLACK или GREY; - можно указать оба цвета;
    - можно совсем не указывать цвет; - тело ответа содержит track. */

    private OrderClient orderClient;
    private int track;
    final Order order;

    @Before
    public void setUp() {
        orderClient = new OrderClient();
    }

    @After
    public void cleanUp() {
        if(track != 0) {
            orderClient.cancel(track);
        }
    }

    public OrderCreatingTest(Order order) {
        this.order = order;
    }

    @Parameterized.Parameters
    public static Object[][] data() {
        return new Object[][] {
                {OrderGenerator.getValidOneColor()},
                {OrderGenerator.getValidBothColors()},
                {OrderGenerator.getValidNoColor()}
        };
    }

    @Test
    @DisplayName("Check status code and track number return of POST /api/v1/orders") // имя теста
    @Description("Basic test for POST /api/v1/orders with 3 scooter color options")
    public void orderCanBeCreated(){
        ValidatableResponse createResponse = orderClient.create(order);

        int statusCode = createResponse.extract().statusCode();
        assertEquals("Код ответа не соответствует:", SC_CREATED, statusCode);

        track = createResponse.extract().path("track");
        assertTrue("В ответе нет track-номера:", track != 0);
    }
}
