package miem.projects.vulnerabilities.MINOR.FB;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class FB_STRUTS1_ENDPOINT {

    // Потенциально небезопасное использование
    public static class UnsafeLoginAction extends Action {
        @Override
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                                     throws Exception {
            LoginForm loginForm = (LoginForm) form;
            String username = loginForm.getUsername();
            // использование данных без проверки
            authenticate(username);
            return mapping.findForward("success");
        }
    }

    // Корректная конструкция
    public static class SafeLoginAction extends Action {
        @Override
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                                     throws Exception {
            LoginForm loginForm = (LoginForm) form;
            String username = loginForm.getUsername();
            // Проверка данных пользователя
            if (username == null || !username.matches("^[a-zA-Z0-9_]{3,20}$")) {
                return mapping.findForward("error");
            }
            authenticate(username);
            return mapping.findForward("success");
        }
    }

    // Заглушка метода authenticate
    private static void authenticate(String username) {
        System.out.println("Authenticating: " + username);
    }

    // Заглушка класса LoginForm
    public static class LoginForm extends ActionForm {
        private String username;
        public String getUsername() { return username; }
        public void setUsername(String username) { this.username = username; }
    }
}
