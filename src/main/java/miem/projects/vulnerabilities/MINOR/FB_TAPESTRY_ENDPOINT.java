package miem.projects.vulnerabilities.MINOR.FB;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.PageActivationContext;

// Заглушка для сервиса пользователей
class UserService {
    public void load(String username) {
        System.out.println("Loading user: " + username);
    }
}

public class FB_TAPESTRY_ENDPOINT {

    private final UserService userService = new UserService();

    @Property
    @PageActivationContext
    private String username;

    // Потенциально небезопасный метод
    void onActivateIncorrect() {
        // username используется без проверки
        userService.load(username);
    }

    // Корректный метод с валидацией
    void onActivateCorrect() {
        if (username == null || !username.matches("[a-zA-Z0-9_]+")) {
            throw new IllegalArgumentException("Invalid username");
        }
        userService.load(username);
    }

    // Демонстрация вызова методов
    public static void main(String[] args) {
        FB_TAPESTRY_ENDPOINT page = new FB_TAPESTRY_ENDPOINT();

        // Некорректный вызов
        page.username = "admin123";
        System.out.println("Incorrect call:");
        page.onActivateIncorrect();

        // Корректный вызов
        page.username = "user_01";
        System.out.println("\nCorrect call:");
        page.onActivateCorrect();

        // Корректный вызов с недопустимым username
        page.username = "invalid!name";
        System.out.println("\nCorrect call with invalid username:");
        try {
            page.onActivateCorrect();
        } catch (IllegalArgumentException e) {
            System.out.println("Caught exception: " + e.getMessage());
        }
    }
}
