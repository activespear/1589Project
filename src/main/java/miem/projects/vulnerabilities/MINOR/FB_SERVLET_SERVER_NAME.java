package miem.projects.vulnerabilities.MINOR.FB;

import java.util.Properties;

public class FB_SERVLET_SERVER_NAME {

    // Потенциально небезопасное
    public void unsafeAccess(String serverName) {
        // Доверие данным запроса
        if ("trusted.com".equals(serverName)) {
            grantAdminAccess();
        }
    }

    // Корректная конструкция
    private final Properties config;

    public FB_SERVLET_SERVER_NAME(Properties config) {
        this.config = config;
    }

    public void safeAccess(String serverName) {
        String trustedDomain = config.getProperty("app.trustedDomain");
        if (trustedDomain != null && trustedDomain.equals(serverName)) {
            grantAdminAccess();
        } else {
            System.out.println("Access denied for: " + serverName);
        }
    }

    // Заглушка
    private void grantAdminAccess() {
        System.out.println("Admin access granted!");
    }
}
