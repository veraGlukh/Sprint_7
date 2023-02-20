package ru.yandex.practicum.scooter;

import org.apache.commons.lang3.RandomStringUtils;

public class CourierCredentials {

    private String login;
    private String password;

    static int length = 1;
    static boolean useLetters = true;
    static boolean useNumbers = true;
    static String randomSymbol = RandomStringUtils.random(length, useLetters, useNumbers);

    public CourierCredentials(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public static CourierCredentials from(Courier courier) {
        return new CourierCredentials(courier.getLogin(), courier.getPassword());
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static CourierCredentials returnCredentialsWithInvalidLogin (Courier courier) {
        return new CourierCredentials(courier.getLogin() + randomSymbol, courier.getPassword());
    }

    public static CourierCredentials returnCredentialsWithInvalidPassword (Courier courier) {
        return new CourierCredentials(courier.getLogin(), courier.getPassword() + randomSymbol);
    }
}
