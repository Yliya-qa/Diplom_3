package practicum.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration; // Импортируем Duration

import static practicum.Constants.*;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    private final By loginHeader = By.xpath(LOGIN_HEADER_TEXT);
    private final By emailInput = By.xpath(INPUT_EMAIL_XPATH);
    private final By pwdInput = By.xpath(INPUT_PWD_XPATH);
    private final By enterBtn = By.xpath(ENTER_BTN_XPATH);
    private final By recoverPwdLink = By.xpath(RECOVER_PWD_LINK_XPATH);

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
        WebElement emailField = waitForElementToBeClickable(emailInput);
        emailField.clear();
        emailField.sendKeys(email);
        return this;
    }

    @Step("Очищаем и заполняем поле Пароль")
    public LoginPage fillInPwd(String pwd) {
        WebElement pwdField = waitForElementToBeClickable(pwdInput);
        pwdField.clear();
        pwdField.sendKeys(pwd);
        return this;
    }

    @Step("Нажимаем кнопку Войти")
    public LoginPage enterBtnClick() {
        waitForElementToBeClickable(enterBtn).click();
        return this;
    }

    private WebElement waitForElementToBeClickable(By locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(5)) // Используем Duration вместо long
                .until(ExpectedConditions.elementToBeClickable(locator));
    }
}
