package ru.yandex.practicum.scooter;

import org.apache.commons.lang3.RandomStringUtils;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class OrderGenerator {

    static int length = 10;
    static boolean useLetters = true;
    static boolean useNumbers = true;
    static Random random = new Random();
    static int maxRentTime = 7;
    static int maxColorNum = 2;
    static int maxDayAfter = 30;

    // Метод создания валидного заказа - указан один из цветов — BLACK или GREY
    public static Order getValidOneColor() {

        String randomFirstName = RandomStringUtils.random(length, useLetters, useNumbers);
        String randomLastName = RandomStringUtils.random(length, useLetters, useNumbers);
        String randomAddress = RandomStringUtils.random(length, useLetters, useNumbers);
        String randomMetro = RandomStringUtils.random(length, useLetters, useNumbers);
        String randomPhone = RandomStringUtils.random(length, useLetters, useNumbers);

        int randomRentTime = random.nextInt(maxRentTime + 1);

        LocalDate currentDate = LocalDate.now();
        int dayAfter = random.nextInt(maxDayAfter + 1);
        String randomDeliveryDate = currentDate.plusDays(dayAfter).toString();

        String randomComment = RandomStringUtils.random(length, useLetters, useNumbers);

        int color = random.nextInt(maxColorNum);
        List<String> randomColor = new ArrayList<>();
        if (color == 1) {
            randomColor.add("BLACK");
        } else {
            randomColor.add("GREY");
        }

        return new Order(randomFirstName, randomLastName, randomAddress, randomMetro, randomPhone, randomRentTime,
                randomDeliveryDate, randomComment, randomColor);
    }

    // Метод создания валидного заказа - указаны оба цвета
    public static Order getValidBothColors() {

        String randomFirstName = RandomStringUtils.random(length, useLetters, useNumbers);
        String randomLastName = RandomStringUtils.random(length, useLetters, useNumbers);
        String randomAddress = RandomStringUtils.random(length, useLetters, useNumbers);
        String randomMetro = RandomStringUtils.random(length, useLetters, useNumbers);
        String randomPhone = RandomStringUtils.random(length, useLetters, useNumbers);

        int randomRentTime = random.nextInt(maxRentTime + 1);

        LocalDate currentDate = LocalDate.now();
        int dayAfter = random.nextInt(maxDayAfter + 1);
        String randomDeliveryDate = currentDate.plusDays(dayAfter).toString();

        String randomComment = RandomStringUtils.random(length, useLetters, useNumbers);

        List<String> bothColors = new ArrayList<>();
        bothColors.add("BLACK");
        bothColors.add("GREY");

        return new Order(randomFirstName, randomLastName, randomAddress, randomMetro, randomPhone, randomRentTime,
                randomDeliveryDate, randomComment, bothColors);
    }

    // Метод создания валидного заказа - не указан цвет
    public static Order getValidNoColor() {

        String randomFirstName = RandomStringUtils.random(length, useLetters, useNumbers);
        String randomLastName = RandomStringUtils.random(length, useLetters, useNumbers);
        String randomAddress = RandomStringUtils.random(length, useLetters, useNumbers);
        String randomMetro = RandomStringUtils.random(length, useLetters, useNumbers);
        String randomPhone = RandomStringUtils.random(length, useLetters, useNumbers);

        int randomRentTime = random.nextInt(maxRentTime + 1);

        LocalDate currentDate = LocalDate.now();
        int dayAfter = random.nextInt(maxDayAfter + 1);
        String randomDeliveryDate = currentDate.plusDays(dayAfter).toString();

        String randomComment = RandomStringUtils.random(length, useLetters, useNumbers);
        List<String> noColor = new ArrayList<>();

        return new Order(randomFirstName, randomLastName, randomAddress, randomMetro, randomPhone, randomRentTime, randomDeliveryDate, randomComment, noColor);
    }
}
