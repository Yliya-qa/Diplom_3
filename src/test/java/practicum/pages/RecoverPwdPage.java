package practicum.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration; // Импортируем Duration

import static practicum.Constants.*;

public class RecoverPwdPage extends BasePage {

    private final By loginLink = By.xpath(LOGIN_LINK_XPATH);

    public RecoverPwdPage(WebDriver driver) {
        super(driver);
    }

    @Step("Открытие сайта на странице восстановления пароля")
    public RecoverPwdPage open() {
        driver.get(URL + RECOVER_PWD_PATH);
        return this;
    }

    @Step("Клик по ссылке для перехода на форму Вход")
    public RecoverPwdPage clickLoginLink() {
        waitForElementToBeClickable(loginLink, 2);
        driver.findElement(loginLink).click();
        return this;
    }

    private void waitForElementToBeClickable(By locator, long seconds) {
        new WebDriverWait(driver, Duration.ofSeconds(seconds)) // Используем Duration вместо long
                .until(ExpectedConditions.elementToBeClickable(locator));
    }
}