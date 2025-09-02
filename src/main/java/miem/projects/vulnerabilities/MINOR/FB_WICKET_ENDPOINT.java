package miem.projects.vulnerabilities.MINOR.FB;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.commons.text.StringEscapeUtils;

// Заглушка для сервиса пользователей
class UserService {
    public String getUserById(String userId) {
        return "User_" + userId;
    }
}

public class FB_WICKET_ENDPOINT {

    private final UserService userService = new UserService();

    // Потенциально небезопасная страница
    public class UserPageUnsafe extends WebPage {
        public UserPageUnsafe(final PageParameters parameters) {
            String userId = parameters.get("id").toString();
            add(new Label("user", userService.getUserById(userId)));
        }
    }

    // Корректная страница с проверкой и экранированием
    public class UserPageSafe extends WebPage {
        public UserPageSafe(final PageParameters parameters) {
            String userId = parameters.get("id").toString();
            if (userId == null || !userId.matches("\\d+")) {
                throw new IllegalArgumentException("Invalid user ID");
            }
            add(new Label("user", StringEscapeUtils.escapeHtml4(userService.getUserById(userId))));
        }
    }

    // Демонстрация работы
    public static void main(String[] args) {
        FB_WICKET_ENDPOINT endpoint = new FB_WICKET_ENDPOINT();

        PageParameters params = new PageParameters();
        params.add("id", "123");

        System.out.println("Unsafe page call:");
        UserPageUnsafe unsafePage = endpoint.new UserPageUnsafe(params);

        System.out.println("\nSafe page call:");
        UserPageSafe safePage = endpoint.new UserPageSafe(params);

        System.out.println("\nSafe page call with invalid ID:");
        params.add("id", "abc!");  // некорректный ID
        try {
            UserPageSafe invalidPage = endpoint.new UserPageSafe(params);
        } catch (IllegalArgumentException e) {
            System.out.println("Caught exception: " + e.getMessage());
        }
    }
}
