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
    static List<String> randomColor = new ArrayList<>();

    // Метод создания валидного заказа
    public static Order getValid(int colorNum) throws Exception {

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

        switch(colorNum) {
            case 0:
                break;
            case 1:
                addOneColor();
                break;
            case 2:
                addBothColors();
                break;
            default:
                throw new Exception("Недопустимое значение количества цветов самоката");
            }

        return new Order(randomFirstName, randomLastName, randomAddress, randomMetro, randomPhone, randomRentTime,
                randomDeliveryDate, randomComment, randomColor);
        }

    public static void addOneColor() {
        int color = random.nextInt(maxColorNum);
        if (color == 1) {
            randomColor.add("BLACK");
        } else {
            randomColor.add("GREY");
        }
    }

    public static void addBothColors() {
        randomColor.add("BLACK");
        randomColor.add("GREY");
    }
}
