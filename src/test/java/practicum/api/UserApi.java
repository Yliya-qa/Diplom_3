package practicum.api;

import io.restassured.response.Response;

import static practicum.Constants.*;
import static practicum.Constants.AUTH_USER_PATH;

public class UserApi extends BaseHTTPClient {

    public Response userLogin(UserLogin body) {
        return makePostRequest(USER_LOGIN_PATH, body);
    }

    public Response userRegister(UserRegister body) {
        // Используем метод makePostRequest для отправки POST запроса
        return makePostRequest(USER_REGISTER_PATH, body);
    }

    public Response deleteUser(String authToken) {
        return makeDeleteRequest(AUTH_USER_PATH, authToken);
    }
}