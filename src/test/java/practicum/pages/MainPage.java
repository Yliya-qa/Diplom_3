package practicum.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration; // Импортируем Duration

import static practicum.Constants.*;

public class MainPage extends BasePage {

    public MainPage(WebDriver driver) {
        super(driver);
    }

    private final By loginBtn = By.xpath(ENTER_ACCOUNT_BTN_XPATH);
    private final By assembleBurger = By.xpath(ASSEMBLE_BURGER_HEADER_XPATH);
    private final By bunsBtn = By.xpath(BUNS_BTN_XPATH);
    private final By saucesBtn = By.xpath(SAUCES_BTN_XPATH);
    private final By fillingsBtn = By.xpath(FILLINGS_BTN_XPATH);
    private final By activeTab = By.xpath(ACTIVE_TAB_XPATH);
    private final By bunsHeaderAtIngredientsList = By.xpath(INGREDIENTS_LIST_BUNS_HEADER_XPATH);
    private final By ingredientsMenu = By.className(INGREDIENTS_MENU_CLASSNAME);

    @Step("Открываем сайт на главной странице")
    public MainPage open() {
        driver.get(URL);
        waitForTextToBe(assembleBurger, "Соберите бургер", 2);
        return this;
    }

    @Step("Клик по кнопке Вход в аккаунт")
    public MainPage enterAccountBtnClick() {
        waitForElementToBeClickable(loginBtn, 2);
        driver.findElement(loginBtn).click();
        return this;
    }

    @Step("Клик по кнопке Булки списка Меню ингредиентов")
    public MainPage bunsBtnClick() {
        waitForElementToBeClickable(bunsBtn, 2);
        driver.findElement(bunsBtn).click();
        return this;
    }

    @Step("Проверка видимости заголовка Булки в списке ингредиентов")
    public boolean isHeaderVisible() {
        return driver.findElement(bunsHeaderAtIngredientsList).isDisplayed();
    }

    @Step("Прокрутка списка ингредиентов в самый верх")
    public MainPage scrollIngredientsMenu() {
        driver.findElement(ingredientsMenu).sendKeys(Keys.ARROW_UP, Keys.ARROW_UP);
        return this;
    }

    @Step("Клик по кнопке 'Соусы' Меню ингредиентов ")
    public MainPage saucesBtnClick() {
        waitForElementToBeClickable(saucesBtn, 3);
        driver.findElement(saucesBtn).click();
        return this;
    }

    @Step("Получение кнопки 'Соусы'")
    public WebElement getSaucesButton() {
        return driver.findElement(saucesBtn);
    }

    @Step("Проверка, что кнопка 'Соусы' нажата")
    public boolean isSaucesBtnPushed() {
        return driver.findElement(saucesBtn).getText().equals(getPushedBtnName());
    }

    @Step("Клик по кнопке 'Начинки' Меню ингредиентов")
    public MainPage fillingsBtnClick() {
        waitForElementToBeClickable(fillingsBtn, 3);
        driver.findElement(fillingsBtn).click();
        return this;
    }

    @Step("Проверка, что кнопка 'Начинки' нажата")
    public boolean isFillingsBtnPushed() {
        return driver.findElement(fillingsBtn).getText().equals(getPushedBtnName());
    }

    @Step("Клик по кнопке 'Личный Кабинет' на верхней панели")
    public MainPage personalAreaClick() {
        goToPersonalArea();
        return this;
    }

    @Step("Получение текста из заголовка конструктора бургеров")
    public String getAssembleBurgerHeaderTxt() {
        waitForTextToBe(assembleBurger, "Соберите бургер", 2);
        return driver.findElement(assembleBurger).getText();
    }

    @Step("Получение наименования нажатой в текущий момент кнопки Меню ингредиентов")
    public String getPushedBtnName() {
        return driver.findElement(activeTab).getText();
    }

    private void waitForElementToBeClickable(By locator, long seconds) {
        new WebDriverWait(driver, Duration.ofSeconds(seconds)) // Используем Duration вместо long
                .until(ExpectedConditions.elementToBeClickable(locator));
    }

    private void waitForTextToBe(By locator, String text, long seconds) {
        new WebDriverWait(driver, Duration.ofSeconds(seconds)) // Используем Duration вместо long
                .until(ExpectedConditions.textToBe(locator, text));
    }
}

