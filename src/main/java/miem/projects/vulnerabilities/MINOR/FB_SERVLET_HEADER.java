package miem.projects.vulnerabilities.MINOR.FB;

import java.util.logging.Logger;

public class FB_SERVLET_HEADER {

    private static final Logger logger = Logger.getLogger(FB_SERVLET_HEADER.class.getName());

    // Потенциально небезопасное
    public void unsafeAction(String userAgent) {
        if (userAgent != null && userAgent.contains("TrustedBrowser")) {
            allowSensitiveAction();
        }
    }

    // Корректная конструкция
    public void safeAction(UserSession session, User user) {
        if (session.isUserAuthenticated() && hasPermission(user)) {
            allowSensitiveAction();
        } else {
            logger.warning("Unauthorized attempt to perform sensitive action");
        }
    }

    private boolean hasPermission(User user) {
        return user != null && user.hasRole("ADMIN");
    }

    private void allowSensitiveAction() {
        logger.info("Sensitive action executed securely");
    }

    // Вспомогательные классы для эмуляции реального приложения
    public static class UserSession {
        private final boolean authenticated;
        public UserSession(boolean authenticated) {
            this.authenticated = authenticated;
        }
        public boolean isUserAuthenticated() {
            return authenticated;
        }
    }

    public static class User {
        private final String role;
        public User(String role) {
            this.role = role;
        }
        public boolean hasRole(String expectedRole) {
            return expectedRole.equals(this.role);
        }
    }
}
