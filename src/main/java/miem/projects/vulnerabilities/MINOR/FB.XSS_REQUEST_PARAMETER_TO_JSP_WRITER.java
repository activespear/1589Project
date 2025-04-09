package miem.projects.vulnerabilities.MINOR.FB;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.jsp.JspWriter;

public class FB_XSS_REQUEST_PARAMETER_TO_JSP_WRITER {
    public static void main(String[] args) {
        // Для тестирования можно использовать mock-объекты
        incorrectTest(null, null);
        correctTest(null, null);
    }

    public static void incorrectTest(HttpServletRequest request, JspWriter out) {
        // Некорректно: прямой вывод пользовательского ввода
        if (request != null && out != null) {
            try {
                String userInput = request.getParameter("comment");
                out.print(userInput); // Уязвимость XSS!
                System.out.println("Content printed (INSECURE)");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void correctTest(HttpServletRequest request, JspWriter out) {
        // Корректно: экранирование HTML-сущностей
        if (request != null && out != null) {
            try {
                String userInput = request.getParameter("comment");
                String safeOutput = escapeHtml(userInput);
                out.print(safeOutput);
                System.out.println("Content printed securely");
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