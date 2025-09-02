package miem.projects.vulnerabilities.MINOR.FB;

import java.util.logging.Logger;

public class FB_SERVLET_HEADER_REFERER {

    private static final Logger logger = Logger.getLogger(FB_SERVLET_HEADER_REFERER.class.getName());

    // Потенциально небезопасное
    public void unsafeAction(String referer) {
        if (referer != null && referer.contains("mytrusteddomain.com")) {
            processSensitiveAction();
        } else {
            logger.warning("Referer not trusted or missing");
        }
    }

    // Корректная конструкция
    public void safeAction(Session session, String csrfToken) {
        if (session.isValid() && isValidCsrfToken(csrfToken)) {
            processSensitiveAction();
        } else {
            logger.warning("Unauthorized attempt to perform sensitive action");
        }
    }

    private boolean isValidCsrfToken(String token) {
        return token != null && token.equals("expected_csrf_token"); // пример проверки
    }

    private void processSensitiveAction() {
        logger.info("Sensitive action executed securely");
    }

    // Вспомогательные классы для эмуляции реального приложения
    public static class Session {
        private final boolean valid;
        public Session(boolean valid) {
            this.valid = valid;
        }
        public boolean isValid() {
            return valid;
        }
    }
}
