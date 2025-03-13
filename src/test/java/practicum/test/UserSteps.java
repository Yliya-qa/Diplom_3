package practicum.test;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import practicum.Utils;
import practicum.api.UserApi;
import practicum.api.UserLogin;
import practicum.api.UserRegister;

import static practicum.Constants.USER_PWD;

public class UserSteps {

    private Response response;
    private UserRegister userRegister;
    private UserApi userApi = new UserApi();

    public String getUserEmail() {
        // Проверяем, что userRegister и email не равны null
        return userRegister != null ? userRegister.getEmail() : null;
    }

    @Step("Аутентификация пользователя в системе. Данные берутся из объекта UserSteps")
    public UserSteps userLogin(String email, String pwd) {
        UserLogin body = new UserLogin(email, pwd);
        response = userApi.userLogin(body);
        return this;
    }

    @Step("Удаляем пользователя из системы")
    public UserSteps deleteUser() {
        // Проверяем, что response и токен не равны null
        if (response != null && response.getBody().jsonPath().get("accessToken") != null) {
            String token = response.getBody().jsonPath().get("accessToken").toString().split(" ")[1];
            userApi.deleteUser(token);
        }
        return this;
    }

    @Step("Генерируем данные пользователя и создаем объект")
    public UserSteps createRandomUser() {
        String userName = Utils.generateFirstName();
        userRegister = new UserRegister(Utils.generateEmail(userName), USER_PWD, userName);
        return this;
    }

    @Step("Отправляем запрос на регистрацию пользователя в системе")
    public UserSteps registerUser() {
        response = userApi.userRegister(userRegister);
        return this;
    }
}