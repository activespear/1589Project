package miem.projects.vulnerabilities.MINOR.FB;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FB_HRS_REQUEST_PARAMETER_TO_COOKIE {
    public static void main(String[] args) {
        // Для тестирования можно использовать mock-объекты
        incorrectTest(null, null);
        correctTest(null, null);
    }

    public static void incorrectTest(HttpServletRequest request, HttpServletResponse response) {
        // Некорректно: прямое сохранение параметра запроса в cookie
        if (request != null && response != null) {
            String userInput = request.getParameter("userPref");
            Cookie cookie = new Cookie("userPreference", userInput);  // Без валидации
            cookie.setMaxAge(3600);
            response.addCookie(cookie);
            System.out.println("Cookie set without validation (INSECURE)");
        }
    }

    public static void correctTest(HttpServletRequest request, HttpServletResponse response) {
        // Корректно: валидация и санация входных данных
        if (request != null && response != null) {
            String userInput = request.getParameter("userPref");
            // Валидация и очистка входных данных
            String sanitized = sanitizeInput(userInput);
            if (isValidPreference(sanitized)) {
                Cookie cookie = new Cookie("userPreference", sanitized);
                cookie.setHttpOnly(true);
                cookie.setSecure(true);
                cookie.setMaxAge(3600);
                response.addCookie(cookie);
                System.out.println("Secure cookie set with validation");
            } else {
                System.out.println("Invalid input rejected");
            }
        }
    }

    private static String sanitizeInput(String input) {
        // Удаляем потенциально опасные символы
        return input != null ? input.replaceAll("[^a-zA-Z0-9-_]", "") : "";
    }

    private static boolean isValidPreference(String input) {
        // Проверяем допустимые значения
        return input != null && !input.isEmpty() && input.length() <= 100;
    }
}