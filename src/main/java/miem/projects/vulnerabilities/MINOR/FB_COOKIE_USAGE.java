package miem.projects.vulnerabilities.MINOR.FB;

import java.util.logging.Logger;

public class FB_COOKIE_USAGE {

    private static final Logger logger = Logger.getLogger(FB_COOKIE_USAGE.class.getName());

    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    // Потенциально небезопасное использование cookie
    public static void incorrectTest() {
        User user = new User("user@example.com");
        // Прямое добавление cookie с чувствительными данными
        logger.warning("Adding unsafe cookie: " + user.getEmail());
        // В реальном приложении это было бы: response.addCookie(new Cookie("userEmail", user.getEmail()));
    }

    // Корректная конструкция с использованием сессии
    public static void correctTest() {
        User user = new User("user@example.com");
        Session session = new Session();
        session.setAttribute("userEmail", user.getEmail());
        logger.info("User email stored safely in session: " + session.getAttribute("userEmail"));
    }

    // Вспомогательный класс для имитации пользователя
    public static class User {
        private final String email;

        public User(String email) {
            this.email = email;
        }

        public String getEmail() {
            return email;
        }
    }

    // Вспомогательный класс для имитации сессии
    public static class Session {
        private String userEmail;

        public void setAttribute(String key, String value) {
            if ("userEmail".equals(key)) {
                this.userEmail = value;
            }
        }

        public String getAttribute(String key) {
            if ("userEmail".equals(key)) {
                return userEmail;
            }
            return null;
        }
    }
}
