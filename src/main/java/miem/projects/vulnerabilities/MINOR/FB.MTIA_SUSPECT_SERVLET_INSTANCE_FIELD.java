package miem.projects.vulnerabilities.MINOR.FB;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FB_MTIA_SUSPECT_SERVLET_INSTANCE_FIELD {
    public static void main(String[] args) {
        // Для тестирования можно использовать mock-объекты
        incorrectTest(null, null);
        correctTest(null, null);
    }

    // Некорректно: изменяемое поле экземпляра сервлета
    static class UserServletIncorrect extends HttpServlet {
        private String currentUser;  // Опасное поле экземпляра

        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws IOException {
            currentUser = req.getParameter("user");
            resp.getWriter().write("Current user: " + currentUser);
        }
    }

    // Корректно: использование локальных переменных
    static class UserServletCorrect extends HttpServlet {
        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws IOException {
            String currentUser = req.getParameter("user");  // Локальная переменная
            resp.getWriter().write("Current user: " + currentUser);
        }
    }

    public static void incorrectTest(HttpServletRequest req, HttpServletResponse resp) {
        if (req != null && resp != null) {
            try {
                new UserServletIncorrect().doGet(req, resp);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void correctTest(HttpServletRequest req, HttpServletResponse resp) {
        if (req != null && resp != null) {
            try {
                new UserServletCorrect().doGet(req, resp);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}