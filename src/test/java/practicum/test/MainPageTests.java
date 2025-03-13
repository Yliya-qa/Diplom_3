package practicum.test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import practicum.pages.LoginPage;
import practicum.pages.MainPage;

import static practicum.Constants.USER_PWD;
import static practicum.WebDriverFactory.getDriver;

public class MainPageTests {
    private WebDriver driver; // Поле для управления WebDriver
    private MainPage mainPage; // Главная страница
   private LoginPage loginPage; // Страница входа
    private UserSteps userSteps; // Шаги для работы с пользователем

    @Before
    public void prepare() {
        // Инициализация WebDriver и шагов пользователя
        driver = getDriver(); // Инициализация WebDriver
        userSteps = new UserSteps();

        // Создание и регистрация случайного пользователя
        userSteps.createRandomUser().registerUser();

        // Авторизация
       loginPage = new LoginPage(driver);
        loginPage.open()
               .fillInEmail(userSteps.getUserEmail()) // Используем email созданного пользователя
                .fillInPwd(USER_PWD) // Вводим пароль
                .enterBtnClick();

        // Переход на главную страницу
        mainPage = new MainPage(driver); // Инициализация MainPage
    }

    @Test
    public void bunsBtnClickTest() {
        // Тест на проверку работы кнопки "Булки"
        mainPage.saucesBtnClick(); // Нажимаем на "Соусы"
        mainPage.bunsBtnClick().scrollIngredientsMenu(); // Возвращаемся к "Булкам" и скроллим
        Assert.assertTrue("Заголовок 'Булки' не виден", mainPage.isHeaderVisible());
    }

    @Test
    public void saucesBtnTest() {
        // Тест на проверку работы кнопки "Соусы"
        mainPage.saucesBtnClick();
        Assert.assertTrue("Кнопка 'Соусы' не нажата", mainPage.isSaucesBtnPushed());
    }

    @Test
    public void fillingsBtnClickTest() {
        // Тест на проверку работы кнопки "Начинки"
        mainPage.fillingsBtnClick();
        Assert.assertTrue("Кнопка 'Начинки' не нажата", mainPage.isFillingsBtnPushed());
    }

    @After
    public void clear() {
        // Очистка: завершение работы WebDriver и удаление пользователя
        if (driver != null) {
            driver.close();
            driver.quit();
        }
        userSteps.deleteUser(); // Удаляем созданного пользователя
    }
}
