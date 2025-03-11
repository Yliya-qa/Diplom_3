package practicum;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriverFactory {

    public static WebDriver getDriver() {
        String browser = System.getProperty("browser", "chrome").toLowerCase();
        ChromeOptions options = new ChromeOptions();

        if (browser.equals("yandex")) {
            // Используем WebDriverManager для автоматической загрузки ChromeDriver
            WebDriverManager.chromedriver().setup(); // Yandex использует тот же драйвер, что и Chrome
            // Укажите путь к Yandex Browser
            options.setBinary("C:\\Users\\yulia\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe");
        } else {
            // Используем WebDriverManager для автоматической загрузки ChromeDriver
            WebDriverManager.chromedriver().clearResolutionCache().setup();
        }

        options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
        return new ChromeDriver(options);
    }
}
