package miem.projects.vulnerabilities.MINOR.FB;

import java.util.logging.Logger;

public class FB_SERVLET_HEADER_USER_AGENT {

    private static final Logger logger = Logger.getLogger(FB_SERVLET_HEADER_USER_AGENT.class.getName());

    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    // Потенциально небезопасное использование
    public static void incorrectTest() {
        String userAgent = "ScannerBot";  // пример недоверенного заголовка
        if (userAgent != null && userAgent.contains("Scanner")) {
            blockRequest();
        } else {
            logger.info("Request allowed (unsafe test)");
        }
    }

    // Корректная конструкция
    public static void correctTest() {
        User user = new User(true, false);  // пользователь аутентифицирован, но нет прав
        if (!user.isAuthenticated() || !user.hasPermission()) {
            blockRequest();
        } else {
            processRequest();
        }
    }

    private static void blockRequest() {
        logger.warning("Request blocked due to security policy");
    }

    private static void processRequest() {
        logger.info("Request processed securely");
    }

    // Вспомогательный класс для имитации проверки пользователя
    public static class User {
        private final boolean authenticated;
        private final boolean permission;

        public User(boolean authenticated, boolean permission) {
            this.authenticated = authenticated;
            this.permission = permission;
        }

        public boolean isAuthenticated() {
            return authenticated;
        }

        public boolean hasPermission() {
            return permission;
        }
    }
}
