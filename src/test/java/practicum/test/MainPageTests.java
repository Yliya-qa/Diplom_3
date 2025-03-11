package practicum.test;


import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import practicum.pages.LoginPage;
import practicum.pages.MainPage;
import practicum.pages.PersonalAccountPage;

import static practicum.Constants.USER_PWD;
import static practicum.WebDriverFactory.getDriver;

public class MainPageTests {
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
    public void bunsBtnClickTest() {
        mainPage.saucesBtnClick();
        mainPage.bunsBtnClick().scrollIngredientsMenu();
        Assert.assertTrue("Заголовок булок не виден", mainPage.isHeaderVisible());
    }

    @Test
    public void saucesBtnTest() {
        mainPage.saucesBtnClick();
        Assert.assertTrue("Кнопка 'Соусы' не нажата", mainPage.isSaucesBtnPushed());
    }

    @Test
    public void fillingsBtnClickTest() {
        mainPage.fillingsBtnClick();
        Assert.assertTrue("Кнопка 'Начинки' не нажата", mainPage.isFillingsBtnPushed());
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
