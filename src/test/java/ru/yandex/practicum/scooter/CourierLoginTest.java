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

public class CourierLoginTest {

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

    /* Тест 1 Идеи проверки: - курьер может авторизоваться; - для авторизации нужно передать все обязательные поля;
    - успешный запрос возвращает id */

    @Test
    @DisplayName("Check status code and order id return of POST /api/v1/courier/login") // имя теста
    @Description("Basic test for POST /api/v1/courier/login with valid courier with all required fields filled in")
    public void courierCanLogin(){
        courier = CourierGenerator.getValidAllFields();
        courierClient.create(courier);
        ValidatableResponse loginResponse = courierClient.login(CourierCredentials.from(courier));

        int statusCode = loginResponse.extract().statusCode();
        assertEquals("Код ответа не соответствует:", SC_OK, statusCode);

        id = loginResponse.extract().path("id");
        assertTrue("Запрос не возвращает id:", id != 0);
    }

    // Тесты 2-3 Идея проверки: - система вернёт ошибку, если неправильно указать логин или пароль;

    @Test
    @DisplayName("Check status code and message of POST /api/v1/courier/login") // имя теста
    @Description("Negative test for POST /api/v1/courier/login with invalid courier with invalid login")
    public void courierCanNotLoginWithFakeLogin() {
        courier = CourierGenerator.getValidAllFields();
        courierClient.create(courier);
        ValidatableResponse loginResponse = courierClient.login(CourierCredentials.returnCredentialsWithInvalidLogin(courier));

        int statusCode = loginResponse.extract().statusCode();
        assertEquals("Код ответа не соответствует:", SC_NOT_FOUND, statusCode);

        String expectedMessage = "Учетная запись не найдена";
        String actualMessage = loginResponse.extract().path("message");
        assertEquals("Тело ответа не соответствует:", expectedMessage, actualMessage);
    }

    @Test
    @DisplayName("Check status code and message of POST /api/v1/courier/login") // имя теста
    @Description("Negative test for POST /api/v1/courier/login with invalid courier with invalid password")
    public void courierCanNotLoginWithFakePassword() {
        courier = CourierGenerator.getValidAllFields();
        courierClient.create(courier);
        ValidatableResponse loginResponse = courierClient.login(CourierCredentials.returnCredentialsWithInvalidPassword(courier));

        int statusCode = loginResponse.extract().statusCode();
        assertEquals("Код ответа не соответствует:", SC_NOT_FOUND, statusCode);

        String expectedMessage = "Учетная запись не найдена";
        String actualMessage = loginResponse.extract().path("message");
        assertEquals("Тело ответа не соответствует:", expectedMessage, actualMessage);
    }

    // Тесты 4-7 Идея проверки: - если какого-то поля нет, запрос возвращает ошибку;

    @Test
    @DisplayName("Check status code and message of POST /api/v1/courier/login") // имя теста
    @Description("Negative test for POST /api/v1/courier/login with invalid courier with null login")
    public void courierCanNotLoginWithLoginNull(){
        courier = CourierGenerator.getInvalidWithLoginNull();
        courierClient.create(courier);
        ValidatableResponse loginResponse = courierClient.login(CourierCredentials.from(courier));

        int statusCode = loginResponse.extract().statusCode();
        assertEquals("Код ответа не соответствует:", SC_BAD_REQUEST, statusCode);

        String expectedMessage = "Недостаточно данных для входа";
        String actualMessage = loginResponse.extract().path("message");
        assertEquals("Тело ответа не соответствует:", expectedMessage, actualMessage);
    }

    @Test
    @DisplayName("Check status code and message of POST /api/v1/courier/login") // имя теста
    @Description("Negative test for POST /api/v1/courier/login with invalid courier with empty login")
    public void courierCanNotLoginWithLoginEmpty(){
        courier = CourierGenerator.getInvalidWithLoginEmpty();
        courierClient.create(courier);
        ValidatableResponse loginResponse = courierClient.login(CourierCredentials.from(courier));

        int statusCode = loginResponse.extract().statusCode();
        assertEquals("Код ответа не соответствует:", SC_BAD_REQUEST, statusCode);

        String expectedMessage = "Недостаточно данных для входа";
        String actualMessage = loginResponse.extract().path("message");
        assertEquals("Тело ответа не соответствует:", expectedMessage, actualMessage);
    }

    @Test
    @DisplayName("Check status code and message of POST /api/v1/courier/login") // имя теста
    @Description("Negative test for POST /api/v1/courier/login with invalid courier with null password")
    public void courierCanNotLoginWithPasswordNull(){
        courier = CourierGenerator.getInvalidWithPasswordNull();
        courierClient.create(courier);
        ValidatableResponse loginResponse = courierClient.login(CourierCredentials.from(courier));

        int statusCode = loginResponse.extract().statusCode();
        assertEquals("Код ответа не соответствует:", SC_BAD_REQUEST, statusCode);

        String expectedMessage = "Недостаточно данных для входа";
        String actualMessage = loginResponse.extract().path("message");
        assertEquals("Тело ответа не соответствует:", expectedMessage, actualMessage);
    }

    @Test
    @DisplayName("Check status code and message of POST /api/v1/courier/login") // имя теста
    @Description("Negative test for POST /api/v1/courier/login with invalid courier with empty password")
    public void courierCanNotLoginWithPasswordEmpty(){
        courier = CourierGenerator.getInvalidWithPasswordEmpty();
        courierClient.create(courier);
        ValidatableResponse loginResponse = courierClient.login(CourierCredentials.from(courier));

        int statusCode = loginResponse.extract().statusCode();
        assertEquals("Код ответа не соответствует:", SC_BAD_REQUEST, statusCode);

        String expectedMessage = "Недостаточно данных для входа";
        String actualMessage = loginResponse.extract().path("message");
        assertEquals("Тело ответа не соответствует:", expectedMessage, actualMessage);
    }

    // Тесты 8 Идея проверки: - если авторизоваться под несуществующим пользователем, запрос возвращает ошибку;

    @Test
    @DisplayName("Check status code and message of POST /api/v1/courier/login") // имя теста
    @Description("Negative test for POST /api/v1/courier/login with non-existent courier")
    public void notCreatedCourierCanNotLogin() {
        courier = CourierGenerator.getValidAllFields();
        ValidatableResponse loginResponse = courierClient.login(CourierCredentials.from(courier));

        int statusCode = loginResponse.extract().statusCode();
        assertEquals("Код ответа не соответствует:", SC_NOT_FOUND, statusCode);

        String expectedMessage = "Учетная запись не найдена";
        String actualMessage = loginResponse.extract().path("message");
        assertEquals("Тело ответа не соответствует:", expectedMessage, actualMessage);
    }
}
