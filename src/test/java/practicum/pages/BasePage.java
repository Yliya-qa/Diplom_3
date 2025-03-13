package practicum.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.NoSuchElementException;

import static practicum.Constants.*;

public abstract class BasePage {

    protected static final String URL = SITE_URL;

    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    private final By burgersLogo = By.xpath(LOGO_XPATH);
    private final By personalArea = By.xpath(PERSONAL_AREA_BTN_XPATH);
    private final By constructorButton = By.xpath(CONSTRUCTOR_BTN_XPATH);

    @Step("Клик по логотипу Stellar Burger на верхней панели")
    protected BasePage goToMainPage() {
        clickElement(burgersLogo);
        return this;
    }

    @Step("Клик по кнопке 'Личный Кабинет' на верхней панели")
    protected BasePage goToPersonalArea() {
        clickElement(personalArea);
        return this;
    }

    @Step("Клик по кнопке 'Конструктор' на верхней панели")
    protected BasePage goToConstructor() {
        clickElement(constructorButton);
        return this;
    }

    private void clickElement(By locator) {
        try {
            WebElement element = driver.findElement(locator);
            element.click();
        } catch (NoSuchElementException e) {
            // Логирование или обработка ошибки
            System.err.println("Element not found: " + locator);
        }
    }
}
