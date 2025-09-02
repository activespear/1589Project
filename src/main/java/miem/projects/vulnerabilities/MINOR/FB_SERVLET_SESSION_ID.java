package miem.projects.vulnerabilities.MINOR.FB;

import java.util.UUID;
import java.util.logging.Logger;

public class FB_SERVLET_SESSION_ID {

    private static final Logger logger = Logger.getLogger(FB_SERVLET_SESSION_ID.class.getName());

    // Потенциально небезопасное
    public void unsafeSessionHandling(String requestedSessionId) {
        // Логирование идентификатора сессии — уязвимость (может попасть в логи и быть украденным)
        logger.info("User session: " + requestedSessionId);
    }

    // Корректная конструкция
    private String validSessionId = UUID.randomUUID().toString(); // "Хранимая на сервере" сессия

    public void safeSessionHandling(String requestedSessionId) {
        if (requestedSessionId != null && requestedSessionId.equals(validSessionId)) {
            grantAccess();
        } else {
            logger.warning("Invalid or missing session ID");
        }
    }

    private void grantAccess() {
        logger.info("Access granted!");
    }
}
