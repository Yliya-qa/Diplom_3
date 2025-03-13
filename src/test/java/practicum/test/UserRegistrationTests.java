package practicum.test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import static practicum.WebDriverFactory.getDriver;
import practicum.pages.LoginPage;
import practicum.pages.PersonalAccountPage;
import practicum.pages.RegPage;

import static practicum.Constants.*;

public class UserRegistrationTests {

    private WebDriver driver; // Объявляем driver как поле класса
    private RegPage regPage;
    private UserSteps userSteps;

    @Before
    public void prepare() {
        driver = getDriver(); // Инициализируем driver в методе prepare
        regPage = new RegPage(driver);
        userSteps = new UserSteps();
    }

    @Test
    public void successfulUserRegistrationTest() {
        regPage.registerRandomUser(USER_PWD);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open().fillInEmail(regPage.getUserEmail()).fillInPwd(USER_PWD).enterBtnClick();
        PersonalAccountPage personalArea = new PersonalAccountPage(driver);
        personalArea.backToPersonalAccount();
        Assert.assertTrue(driver.getCurrentUrl().contains(ACCOUNT_PROFILE_PATH));
        Assert.assertEquals("Профиль", personalArea.getProfileTabTxt());
        userSteps.userLogin(regPage.getUserEmail(), USER_PWD).deleteUser();
    }

    @Test
    public void unsuccessfulUserRegistrationTest() {
        regPage.registerRandomUser(INCORRECT_USER_PWD);
        Assert.assertTrue(driver.getCurrentUrl().contains(REG_PATH));
        Assert.assertEquals(WRONG_PWD_MSG, regPage.getErrorMsg());
    }

    @After
    public void clear() {
        if (driver != null) { // Проверяем, что driver не равен null перед закрытием
            driver.close();
            driver.quit();
        }
    }
}