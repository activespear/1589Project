package miem.projects.vulnerabilities.NORMAL;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class NO_BASE_CALL_LIB extends HttpServlet {

    public static void incorrectTest(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        class MyServlet extends HttpServlet {
            @Override
            protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
                // Не вызван super.service — может сломать поведение HTTP методов (GET/POST)
                resp.getWriter().write("...");
            }
        }

        new MyServlet().service(req, resp);
    }

    public static void correctTest(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        class MyServlet extends HttpServlet {
            @Override
            protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
                super.service(req, resp);  // Вызываем родительский метод
                // Обычно переопределяется doGet/doPost, а не service напрямую
            }

            @Override
            protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
                resp.getWriter().write("...");
            }
        }

        new MyServlet().service(req, resp);
    }
}