package miem.projects.vulnerabilities.MINOR.FB;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class FB_HRS_REQUEST_PARAMETER_TO_HTTP_HEADER {
    public static void main(String[] args) {
        // Для тестирования можно использовать mock-объекты
        incorrectTest(null, null);
        correctTest(null, null);
    }

    public static void incorrectTest(HttpServletRequest request, HttpServletResponse response) {
        // Некорректно: прямое использование параметра запроса в заголовке
        if (request != null && response != null) {
            String lang = request.getParameter("lang");
            response.setHeader("Content-Language", lang);  // Без валидации
            System.out.println("Header set without validation (INSECURE)");
        }
    }

    public static void correctTest(HttpServletRequest request, HttpServletResponse response) {
        // Корректно: валидация и фильтрация входных данных
        if (request != null && response != null) {
            String lang = request.getParameter("lang");
            String sanitized = sanitizeLanguageHeader(lang);
            
            if (isValidLanguage(sanitized)) {
                response.setHeader("Content-Language", sanitized);
                System.out.println("Secure header set with validation");
            } else {
                response.setHeader("Content-Language", "en");  // Значение по умолчанию
                System.out.println("Invalid input, using default header");
            }
        }
    }

    private static String sanitizeLanguageHeader(String input) {
        if (input == null) return "en";
        // Удаляем опасные символы (CR, LF и т.д.)
        return input.replaceAll("[\r\n]", "");
    }

    private static boolean isValidLanguage(String lang) {
        // Проверяем допустимые языковые коды
        return lang != null && lang.matches("^[a-zA-Z]{2}(-[a-zA-Z]{2})?$");
    }
}