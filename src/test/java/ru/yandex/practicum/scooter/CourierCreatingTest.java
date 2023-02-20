package ru.yandex.practicum.scooter;

import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.apache.http.HttpStatus.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;

public class CourierCreatingTest {

    private Courier courier;
    private CourierClient courierClient;
    private int id;

    @Before
    public void setUp() {
        courierClient = new CourierClient();
    }

    @After
    public void cleanUp() {
        if(id != 0) {
            courierClient.delete(id);
        }
    }

    /* Тесты 1 - 3 Идеи проверки: - курьера можно создать; - запрос возвращает правильный код ответа;
    - успешный запрос возвращает ok: true; */

    @Test
    @DisplayName("Check status code and message of POST /api/v1/courier") // имя теста
    @Description("Basic test for POST /api/v1/courier with valid courier with all required fields filled in")
    public void courierCanBeCreated(){
        courier = CourierGenerator.getValidAllFields();
        ValidatableResponse createResponse = courierClient.create(courier);
        ValidatableResponse loginResponse = courierClient.login(CourierCredentials.from(courier));

        id = loginResponse.extract().path("id");

        int statusCode = createResponse.extract().statusCode();
        assertEquals("Код ответа не соответствует:", SC_CREATED, statusCode);

        boolean message = createResponse.extract().path("ok");
        assertTrue("Тело ответа не соответствует:", message);
    }

    @Test
    @DisplayName("Check status code and message of POST /api/v1/courier") // имя теста
    @Description("Basic test for POST /api/v1/courier with valid courier with null name")
    public void courierCanBeCreatedWithoutNameNull(){
        courier = CourierGenerator.getValidWithNameNull();
        ValidatableResponse createResponse = courierClient.create(courier);
        ValidatableResponse loginResponse = courierClient.login(CourierCredentials.from(courier));

        id = loginResponse.extract().path("id");

        int statusCode = createResponse.extract().statusCode();
        assertEquals("Код ответа не соответствует:", SC_CREATED, statusCode);

        boolean message = createResponse.extract().path("ok");
        assertTrue("Тело ответа не соответствует:", message);
    }

    @Test
    @DisplayName("Check status code and message of POST /api/v1/courier") // имя теста
    @Description("Basic test for POST /api/v1/courier with valid courier with empty name")
    public void courierCanBeCreatedWithoutNameEmpty(){
        courier = CourierGenerator.getValidWithNameEmpty();
        ValidatableResponse createResponse = courierClient.create(courier);
        ValidatableResponse loginResponse = courierClient.login(CourierCredentials.from(courier));

        id = loginResponse.extract().path("id");

        int statusCode = createResponse.extract().statusCode();
        assertEquals("Код ответа не соответствует:", SC_CREATED, statusCode);

        boolean message = createResponse.extract().path("ok");
        assertTrue("Тело ответа не соответствует:", message);
    }

    /* Тест 4 Идеи проверки: - нельзя создать двух одинаковых курьеров;
    - если создать пользователя с логином, который уже есть, возвращается ошибка. */

    @Test
    @DisplayName("Check status code and message of POST /api/v1/courier") // имя теста
    @Description("Negative test for POST /api/v1/courier with invalid courier with existent login")
    public void twoSameCouriersCanNotBeCreated() {
        courier = CourierGenerator.getValidAllFields();
        courierClient.create(courier);
        ValidatableResponse createResponse = courierClient.create(courier);

        int statusCode = createResponse.extract().statusCode();
        assertEquals("Код ответа не соответствует:", SC_CONFLICT, statusCode);

        String expectedMessage = "Этот логин уже используется";
        String actualMessage = createResponse.extract().path("message");
        assertEquals("Тело ответа не соответствует:", expectedMessage, actualMessage);
    }

    /*  Тесты 5 - 8 Идеи проверки: - чтобы создать курьера, нужно передать в ручку все обязательные поля;
    - если одного из полей нет, запрос возвращает ошибку; */

    @Test
    @DisplayName("Check status code and message of POST /api/v1/courier") // имя теста
    @Description("Negative test for POST /api/v1/courier with invalid courier with null login")
    public void courierCanNotBeCreatedWithLoginNull(){
        courier = CourierGenerator.getInvalidWithLoginNull();
        courierClient.create(courier);
        ValidatableResponse createResponse = courierClient.create(courier);

        int statusCode = createResponse.extract().statusCode();
        assertEquals("Код ответа не соответствует:", SC_BAD_REQUEST, statusCode);

        String expectedMessage = "Недостаточно данных для создания учетной записи";
        String actualMessage = createResponse.extract().path("message");
        assertEquals("Тело ответа не соответствует:", expectedMessage, actualMessage);
    }

    @Test
    @DisplayName("Check status code and message of POST /api/v1/courier") // имя теста
    @Description("Negative test for POST /api/v1/courier with invalid courier with empty login")
    public void courierCanNotBeCreatedWithLoginEmpty(){
        courier = CourierGenerator.getInvalidWithLoginEmpty();
        courierClient.create(courier);
        ValidatableResponse createResponse = courierClient.create(courier);

        int statusCode = createResponse.extract().statusCode();
        assertEquals("Код ответа не соответствует:", SC_BAD_REQUEST, statusCode);

        String expectedMessage = "Недостаточно данных для создания учетной записи";
        String actualMessage = createResponse.extract().path("message");
        assertEquals("Тело ответа не соответствует:", expectedMessage, actualMessage);
    }

    @Test
    @DisplayName("Check status code and message of POST /api/v1/courier") // имя теста
    @Description("Negative test for POST /api/v1/courier with invalid courier with null password")
    public void courierCanNotBeCreatedWithPasswordNull(){
        courier = CourierGenerator.getInvalidWithPasswordNull();
        courierClient.create(courier);
        ValidatableResponse createResponse = courierClient.create(courier);

        int statusCode = createResponse.extract().statusCode();
        assertEquals("Код ответа не соответствует:", SC_BAD_REQUEST, statusCode);

        String expectedMessage = "Недостаточно данных для создания учетной записи";
        String actualMessage = createResponse.extract().path("message");
        assertEquals("Тело ответа не соответствует:", expectedMessage, actualMessage);
    }

    @Test
    @DisplayName("Check status code and message of POST /api/v1/courier") // имя теста
    @Description("Negative test for POST /api/v1/courier with invalid courier with empty password")
    public void courierCanNotBeCreatedWithPasswordEmpty(){
        courier = CourierGenerator.getInvalidWithPasswordEmpty();
        courierClient.create(courier);
        ValidatableResponse createResponse = courierClient.create(courier);

        int statusCode = createResponse.extract().statusCode();
        assertEquals("Код ответа не соответствует:", SC_BAD_REQUEST, statusCode);

        String expectedMessage = "Недостаточно данных для создания учетной записи";
        String actualMessage = createResponse.extract().path("message");
        assertEquals("Тело ответа не соответствует:", expectedMessage, actualMessage);
    }
}
