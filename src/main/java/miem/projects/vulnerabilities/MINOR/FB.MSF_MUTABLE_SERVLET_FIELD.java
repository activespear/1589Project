package miem.projects.vulnerabilities.MINOR.FB;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FB_MSF_MUTABLE_SERVLET_FIELD {
    public static void main(String[] args) {
        // Тестирование через mock-запросы в реальном коде
    }

    // Некорректная реализация сервлета с изменяемым полем
    public static class UserProfileServletIncorrect extends HttpServlet {
        private String currentUser;  // Опасное изменяемое поле
        
        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws IOException {
            currentUser = req.getParameter("username");
            resp.getWriter().write("Profile for: " + currentUser);
        }
    }

    // Корректная реализация с локальной переменной
    public static class UserProfileServletCorrect extends HttpServlet {
        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws IOException {
            String currentUser = req.getParameter("username");  // Локальная переменная
            resp.getWriter().write("Profile for: " + currentUser);
        }
    }

    // Безопасное использование final поля
    public static class ConfigServletSafe extends HttpServlet {
        private final String appVersion;  // Безопасное final поле
        
        public ConfigServletSafe() {
            this.appVersion = "1.0.0";
        }
        
        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws IOException {
            resp.getWriter().write("App version: " + appVersion);
        }
    }

    // Потокобезопасное поле с синхронизацией
    public static class CounterServletSafe extends HttpServlet {
        private int visitCount;
        private final Object lock = new Object();  // Объект для синхронизации
        
        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws IOException {
            synchronized (lock) {
                visitCount++;
                resp.getWriter().write("Visit count: " + visitCount);
            }
        }
    }
}