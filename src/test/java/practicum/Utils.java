package practicum;

import com.github.javafaker.Faker;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

import static practicum.Constants.getDomain;

public class Utils {

    private static final Faker faker = new Faker(); // Создаем один экземпляр Faker для повторного использования
    private static final Random rnd = new Random(); // Создаем один экземпляр Random для повторного использования

    public static String generateFirstName() {
        return faker.name().firstName();
    }

    public static String generateLastName() {
        return faker.name().lastName();
    }

    public static String generateEmail(@Nullable String userName) {
        StringBuilder sb = new StringBuilder();

        // Если userName не передан, генерируем его
        if (userName == null) {
            userName = generateFirstName().toLowerCase();
        }

        // Формируем email
        sb.append(userName.toLowerCase())
                .append(".")
                .append(generateLastName().toLowerCase())
                .append(rnd.nextInt(100))
                .append("@")
                .append(String.format("hostname-%d.", rnd.nextInt(100)))
                .append(getDomain());

        return sb.toString();
    }
}
