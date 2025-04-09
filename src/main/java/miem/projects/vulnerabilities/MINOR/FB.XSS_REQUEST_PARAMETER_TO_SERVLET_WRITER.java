package miem.projects.vulnerabilities.MINOR.FB;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class FB_XSS_REQUEST_PARAMETER_TO_SERVLET_WRITER {
    public static void main(String[] args) {
        // Для тестирования можно использовать mock-объекты
        incorrectTest(null, null);
        correctTest(null, null);
    }

    public static void incorrectTest(HttpServletRequest request, HttpServletResponse response) {
        // Некорректно: прямой вывод пользовательского ввода
        if (request != null && response != null) {
            try {
                String userContent = request.getParameter("content");
                PrintWriter out = response.getWriter();
                out.println("<div>" + userContent + "</div>"); // Уязвимость XSS!
                System.out.println("Content written (INSECURE)");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void correctTest(HttpServletRequest request, HttpServletResponse response) {
        // Корректно: экранирование HTML и CSP-заголовки
        if (request != null && response != null) {
            try {
                String userContent = request.getParameter("content");
                response.setContentType("text/html");
                response.setHeader("Content-Security-Policy", "default-src 'self'");
                
                PrintWriter out = response.getWriter();
                out.println("<div>" + escapeHtml(userContent) + "</div>");
                System.out.println("Content written securely");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static String escapeHtml(String input) {
        if (input == null) return "";
        return input.replace("&", "&amp;")
                   .replace("<", "&lt;")
                   .replace(">", "&gt;")
                   .replace("\"", "&quot;")
                   .replace("'", "&#39;");
    }
}