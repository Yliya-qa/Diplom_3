package practicum.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static practicum.Constants.*;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    private final By loginHeader = By.xpath(LOGIN_HEADER_TEXT);

    @Step("Открываем форму Вход")
    public LoginPage open() {
        driver.get(URL + LOGIN_PATH);
        return this;
    }

    @Step("Получаем из объекта страницы заголовок формы Вход")
    public String getHeaderTxt() {
        waitForElementToBeClickable(loginHeader);
        return driver.findElement(loginHeader).getText();
    }

    @Step("Очищаем и заполняем поле Email")
    public LoginPage fillInEmail(String email) {
        WebElement emailField = waitForElementToBeClickable(By.xpath(INPUT_EMAIL_XPATH));
        emailField.clear();
        emailField.sendKeys(email);
        return this;
    }

    @Step("Очищаем и заполняем поле Пароль")
    public LoginPage fillInPwd(String pwd) {
        WebElement pwdField = waitForElementToBeClickable(By.xpath(INPUT_PWD_XPATH));
        pwdField.clear();
        pwdField.sendKeys(pwd);
        return this;
    }

    @Step("Нажимаем кнопку Войти")
    public LoginPage enterBtnClick() {
        waitForElementToBeClickable(By.xpath(ENTER_BTN_XPATH)).click();
        return this;
    }

    private WebElement waitForElementToBeClickable(By locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(locator));
    }
}

