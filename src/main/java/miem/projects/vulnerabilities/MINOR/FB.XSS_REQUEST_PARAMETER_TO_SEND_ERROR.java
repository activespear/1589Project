package miem.projects.vulnerabilities.MINOR.FB;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class FB_XSS_REQUEST_PARAMETER_TO_SEND_ERROR {
    public static void main(String[] args) {
        // Для тестирования можно использовать mock-объекты
        incorrectTest(null, null);
        correctTest(null, null);
    }

    public static void incorrectTest(HttpServletRequest request, HttpServletResponse response) {
        // Некорректно: прямой вывод пользовательского ввода в ошибку
        if (request != null && response != null) {
            try {
                String errorMsg = request.getParameter("errorMsg");
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, 
                    "Invalid input: " + errorMsg); // Уязвимость XSS!
                System.out.println("Error sent (INSECURE)");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void correctTest(HttpServletRequest request, HttpServletResponse response) {
        // Корректно: экранирование и валидация сообщения
        if (request != null && response != null) {
            try {
                String errorMsg = request.getParameter("errorMsg");
                String safeMsg = sanitizeErrorMessage(errorMsg);
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, 
                    "Invalid input: " + safeMsg);
                System.out.println("Error sent securely");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static String sanitizeErrorMessage(String input) {
        if (input == null) return "";
        return input.replace("&", "&amp;")
                   .replace("<", "&lt;")
                   .replace(">", "&gt;");
    }
}