package miem.projects.vulnerabilities.MINOR.FB;

import java.io.Serializable;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import jakarta.servlet.ServletException;

public class FB_J2EE_STORE_OF_NON_SERIALIZABLE_OBJECT_INTO_SESSION {

    public static void main(String[] args) {
        System.out.println("Этот пример демонстрирует проблему и решение для хранения объектов в сессии в J2EE.");
    }

    // Потенциально небезопасный класс: несериализуемый объект
    static class NonSerializableObject {
        private String data;

        public NonSerializableObject(String data) {
            this.data = data;
        }

        public String getData() {
            return data;
        }
    }

    // Корректный класс: сериализуемый объект
    static class SerializableObject implements Serializable {
        private static final long serialVersionUID = 1L;
        private String data;

        public SerializableObject(String data) {
            this.data = data;
        }

        public String getData() {
            return data;
        }
    }

    // Потенциально небезопасный вариант использования сессии
    static class UnsafeServlet extends HttpServlet {
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            NonSerializableObject obj = new NonSerializableObject("Test Data");
            HttpSession session = request.getSession();
            session.setAttribute("myObject", obj);  // ❌ Несериализуемый объект в сессии
        }
    }

    // Корректный вариант использования сессии
    static class SafeServlet extends HttpServlet {
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            SerializableObject obj = new SerializableObject("Test Data");
            HttpSession session = request.getSession();
            session.setAttribute("myObject", obj);  // ✅ Сериализуемый объект
        }
    }
}
