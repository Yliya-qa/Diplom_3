package practicum.test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import practicum.pages.LoginPage;
import practicum.pages.MainPage;
import practicum.pages.PersonalAccountPage;

import static practicum.Constants.*;
import static practicum.WebDriverFactory.getDriver;

public class PersonalAccountTests {
    private WebDriver driver; // Объявляем driver как поле класса
    private MainPage mainPage;
    private LoginPage loginPage;
    private PersonalAccountPage personalArea;
    private UserSteps userSteps;

    @Before
    public void prepare() {
        driver = getDriver(); // Инициализируем driver в методе prepare
        userSteps = new UserSteps(); // Инициализируем userSteps
        userSteps.createRandomUser().registerUser();

        loginPage = new LoginPage(driver);
        loginPage.open()
                .fillInEmail(userSteps.getUserEmail())
                .fillInPwd(USER_PWD)
                .enterBtnClick();

        mainPage = new MainPage(driver);
        personalArea = new PersonalAccountPage(driver);
    }

    @Test
    public void loginViaPersonalAccountBtnTest() {
        mainPage.personalAreaClick();
        Assert.assertEquals("Профиль", personalArea.backToPersonalAccount().getProfileTabTxt());
        Assert.assertTrue("URL не содержит путь к профилю", driver.getCurrentUrl().contains(ACCOUNT_PROFILE_PATH));
    }

    @Test
    public void jumpToMainFromPersAccountViaConstructorTest() {
        personalArea.constructorBtnClick();
        Assert.assertEquals("Соберите бургер", mainPage.getAssembleBurgerHeaderTxt());
        Assert.assertEquals(SITE_URL + "/", driver.getCurrentUrl());
    }

    @Test
    public void jumpToMainFromPersAccountViaLogoTest() {
        personalArea.burgerLogoClick();
        Assert.assertEquals("Соберите бургер", mainPage.getAssembleBurgerHeaderTxt());
        Assert.assertEquals(SITE_URL + "/", driver.getCurrentUrl());
    }

    @Test
    public void logoutTest() {
        personalArea.backToPersonalAccount().logout();
        Assert.assertEquals("Вход", loginPage.getHeaderTxt());
        Assert.assertTrue("URL не содержит путь к логину", driver.getCurrentUrl().contains(LOGIN_PATH));
    }

    @After
    public void clear() {
        if (driver != null) { // Проверяем, что driver не равен null перед закрытием
            driver.close();
            driver.quit();
        }
        userSteps.deleteUser();
    }
}