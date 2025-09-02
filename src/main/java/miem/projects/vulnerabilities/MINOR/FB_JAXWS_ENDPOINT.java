package miem.projects.vulnerabilities.MINOR.FB;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.logging.Logger;

public class FB_JAXWS_ENDPOINT {

    private static final Logger logger = Logger.getLogger(FB_JAXWS_ENDPOINT.class.getName());

    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    // Потенциально небезопасное веб-сервисное обращение
    public static void incorrectTest() {
        MyServiceUnsafe service = new MyServiceUnsafe();
        String data = service.getData("user123");
        logger.warning("Fetched data without authentication: " + data);
    }

    // Корректная реализация с проверкой токена
    public static void correctTest() {
        MyServiceSafe service = new MyServiceSafe();
        try {
            String token = "validToken";
            String data = service.getData("user123", token);
            logger.info("Fetched data with authentication: " + data);
        } catch (UnauthorizedException e) {
            logger.severe("Access denied: " + e.getMessage());
        }
    }

    // Небезопасный веб-сервис
    @WebService
    public static class MyServiceUnsafe {
        @WebMethod
        public String getData(String userId) {
            return Database.fetchUserData(userId);
        }
    }

    // Безопасный веб-сервис
    @WebService
    public static class MyServiceSafe {
        @WebMethod
        public String getData(String userId, String token) throws UnauthorizedException {
            if (!AuthManager.isValidToken(token, userId)) {
                throw new UnauthorizedException("Access denied");
            }
            return Database.fetchUserData(userId);
        }
    }

    // Исключение для неавторизованного доступа
    public static class UnauthorizedException extends Exception {
        public UnauthorizedException(String message) {
            super(message);
        }
    }

    // Заглушки для демонстрации
    public static class Database {
        public static String fetchUserData(String userId) {
            return "UserData(" + userId + ")";
        }
    }

    public static class AuthManager {
        public static boolean isValidToken(String token, String userId) {
            return "validToken".equals(token);
        }
    }
}
