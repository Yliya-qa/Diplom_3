package practicum.test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import practicum.pages.*;

import static practicum.Constants.ACCOUNT_PROFILE_PATH;
import static practicum.Constants.USER_PWD;
import static practicum.WebDriverFactory.getDriver;

public class UserLoginTests {

    private WebDriver driver; // Объявляем driver как поле класса
    private MainPage mainPage;
    private LoginPage loginPage;
    private PersonalAccountPage personalArea;
    private RegPage regPage;
    private RecoverPwdPage recoverPwdPage;
    private UserSteps userSteps;

    @Before
    public void prepare() {
        driver = getDriver(); // Инициализируем driver в методе prepare
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        regPage = new RegPage(driver);
        personalArea = new PersonalAccountPage(driver);
        recoverPwdPage = new RecoverPwdPage(driver);
        userSteps = new UserSteps(); // Инициализируем userSteps
        userSteps.createRandomUser().registerUser();
    }

    @Test
    public void loginOnMainPageTest() {
        mainPage.open().enterAccountBtnClick();
        loginPage.fillInEmail(userSteps.getUserEmail()).fillInPwd(USER_PWD).enterBtnClick();
        Assert.assertEquals("Профиль", personalArea.backToPersonalAccount().getProfileTabTxt());
        Assert.assertTrue(driver.getCurrentUrl().contains(ACCOUNT_PROFILE_PATH));
    }

    @Test
    public void loginViaPersonalAccountBtnTest() {
        mainPage.open().personalAreaClick();
        loginPage.fillInEmail(userSteps.getUserEmail()).fillInPwd(USER_PWD).enterBtnClick();
        Assert.assertEquals("Профиль", personalArea.backToPersonalAccount().getProfileTabTxt());
        Assert.assertTrue(driver.getCurrentUrl().contains(ACCOUNT_PROFILE_PATH));
    }

    @Test
    public void loginViaButtonOnRegFormTest() {
        regPage.open().clickLoginLink();
        loginPage.fillInEmail(userSteps.getUserEmail()).fillInPwd(USER_PWD).enterBtnClick();
        Assert.assertEquals("Профиль", personalArea.backToPersonalAccount().getProfileTabTxt());
        Assert.assertTrue(driver.getCurrentUrl().contains(ACCOUNT_PROFILE_PATH));
    }

    @Test
    public void loginViaLinkOnRecoverPwdPageTest() {
        recoverPwdPage.open().clickLoginLink();
        loginPage.fillInEmail(userSteps.getUserEmail()).fillInPwd(USER_PWD).enterBtnClick();
        Assert.assertEquals("Профиль", personalArea.backToPersonalAccount().getProfileTabTxt());
        Assert.assertTrue(driver.getCurrentUrl().contains(ACCOUNT_PROFILE_PATH));
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