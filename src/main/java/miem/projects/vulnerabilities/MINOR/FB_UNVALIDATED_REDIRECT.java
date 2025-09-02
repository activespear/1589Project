package miem.projects.vulnerabilities.MINOR.FB;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class FB_UNVALIDATED_REDIRECT {

    // Имитация HTTP сервлета для демонстрации
    public static void main(String[] args) {
        try {
            // Создаем mock объекты для демонстрации
            MockHttpServletRequest request = new MockHttpServletRequest();
            MockHttpServletResponse response = new MockHttpServletResponse();

            incorrectTest(request, response);

            // Сбрасываем response для следующего теста
            response = new MockHttpServletResponse();
            correctTest(request, response);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void incorrectTest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("=== INSECURE REDIRECT ===");

        // Некорректно: перенаправление без проверки URL
        String redirectUrl = request.getParameter("redirectUrl");
        System.out.println("Redirecting to: " + redirectUrl + " (NO VALIDATION)");

        // В реальном сервлете: response.sendRedirect(redirectUrl);
        System.out.println("INSECURE: Redirect executed without validation");
    }

    public static void correctTest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("\n=== SECURE REDIRECT ===");

        // Корректно: проверка перед перенаправлением
        String redirectUrl = request.getParameter("redirectUrl");
        System.out.println("Requested redirect: " + redirectUrl);

        if (isValidRedirect(redirectUrl)) {
            // В реальном сервлете: response.sendRedirect(redirectUrl);
            System.out.println("SECURE: Redirect to validated URL: " + redirectUrl);
        } else {
            // В реальном сервлете: response.sendRedirect("/defaultPage");
            System.out.println("SECURE: Invalid redirect, going to default page");
        }
    }

    private static boolean isValidRedirect(String url) {
        // Проверка URL на допустимость
        return url != null && url.startsWith("https://trusted-domain.com");
    }

    // Mock классы для демонстрации
    static class MockHttpServletRequest {
        public String getParameter(String name) {
            if ("redirectUrl".equals(name)) {
                return "https://trusted-domain.com/safe-page"; // Можно изменить на небезопасный URL для теста
            }
            return null;
        }
    }

    static class MockHttpServletResponse {
        public void sendRedirect(String url) {
            System.out.println("Redirecting to: " + url);
        }
    }
}