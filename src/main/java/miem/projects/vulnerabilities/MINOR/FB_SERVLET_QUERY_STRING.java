package miem.projects.vulnerabilities.MINOR.FB;

import java.util.Set;
import java.util.logging.Logger;

public class FB_SERVLET_QUERY_STRING {

    private static final Logger logger = Logger.getLogger(FB_SERVLET_QUERY_STRING.class.getName());

    // Потенциально небезопасное
    public void unsafeAccess(String hostFromRequest) {
        if ("admin.example.com".equals(hostFromRequest)) {
            grantAdminAccess();
        }
    }

    // Белый список доверенных хостов
    private static final Set<String> WHITELISTED_HOSTS = Set.of(
            "app.example.com",
            "admin.example.com"
    );

    // Корректная конструкция
    public void safeAccess(String hostFromRequest) {
        if (isWhitelistedHost(hostFromRequest)) {
            proceedWithRequest();
        } else {
            rejectRequest();
        }
    }

    private boolean isWhitelistedHost(String host) {
        return host != null && WHITELISTED_HOSTS.contains(host.trim().toLowerCase());
    }

    private void grantAdminAccess() {
        logger.info("Admin access granted!");
    }

    private void proceedWithRequest() {
        logger.info("Request accepted and processed");
    }

    private void rejectRequest() {
        logger.warning("Rejected request from untrusted host");
    }
}
