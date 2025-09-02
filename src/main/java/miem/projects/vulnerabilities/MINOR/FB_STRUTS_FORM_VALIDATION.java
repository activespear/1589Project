package miem.projects.vulnerabilities.MINOR.FB;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;

public class FB_STRUTS_FORM_VALIDATION {

    // ❌ Потенциально небезопасное: нет валидации
    public static class InsecureLoginForm extends ActionForm {
        private String username;
        private String password;

        // только геттеры и сеттеры — данные никак не проверяются
        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    // ✅ Корректная конструкция: добавлена серверная валидация
    public static class SecureLoginForm extends ActionForm {
        private String username;
        private String password;

        @Override
        public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
            ActionErrors errors = new ActionErrors();
            if (username == null || username.trim().isEmpty()) {
                errors.add("username", new ActionMessage("error.username.required"));
            }
            if (password == null || password.trim().isEmpty()) {
                errors.add("password", new ActionMessage("error.password.required"));
            }
            return errors;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
