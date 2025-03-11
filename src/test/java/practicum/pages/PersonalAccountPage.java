package practicum.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration; // Импортируем Duration

import static practicum.Constants.*;

public class PersonalAccountPage extends BasePage {

    public PersonalAccountPage(WebDriver driver) {
        super(driver);
    }

    private final By logoutButton = By.xpath(LOGOUT_BTN_XPATH);
    private final By profileTab = By.xpath(PROFILE_TAB_XPATH);

    @Step("Клик по кнопке Выход на странице Личного Кабинета")
    public PersonalAccountPage logout() {
        driver.findElement(logoutButton).click();
        return this;
    }

    @Step("Клик по кнопке 'Личный Кабинет' на верхней панели")
    public PersonalAccountPage backToPersonalAccount() {
        goToPersonalArea();
        waitForElementToBeClickable(profileTab, 2);
        return this;
    }

    @Step("Получение наименования кнопки 'Профиль' на странице Личного Кабинета")
    public String getProfileTabTxt() {
        waitForElementToBeClickable(profileTab, 2);
        return driver.findElement(profileTab).getText();
    }

    @Step("Клик по кнопке 'Конструктор' на верхней панели")
    public PersonalAccountPage constructorBtnClick() {
        goToConstructor();
        return this;
    }

    @Step("Клик по логотипу Stellar Burger на верхней панели")
    public PersonalAccountPage burgerLogoClick() {
        goToMainPage();
        return this;
    }

    private void waitForElementToBeClickable(By locator, long seconds) {
        new WebDriverWait(driver, Duration.ofSeconds(seconds)) // Используем Duration вместо long
                .until(ExpectedConditions.elementToBeClickable(locator));
    }
}
