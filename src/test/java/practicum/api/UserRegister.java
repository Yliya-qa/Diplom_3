package practicum.api;

public class UserRegister extends UserLogin {

    private String name;

    public UserRegister(String email, String password, String name) {
        super(email, password);
        this.name = name;
    }

    // Удален метод getEmail(), так как он уже есть в родительском классе
    // Если нужно, можно добавить метод для получения имени
    public String getName() {
        return name;
    }
}