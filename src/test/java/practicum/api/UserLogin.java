package practicum.api;

public class UserLogin {

    private String email;
    private String password;

    public UserLogin(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password; // Добавлен метод для получения пароля
    }

    @Override
    public String toString() {
        return "UserLogin{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}'; // Переопределение toString для удобства отладки
    }
}