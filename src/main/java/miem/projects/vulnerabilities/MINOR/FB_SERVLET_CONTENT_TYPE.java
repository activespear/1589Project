package miem.projects.vulnerabilities.MINOR.FB;

import java.util.Set;

public class FB_SERVLET_CONTENT_TYPE {

    // Потенциально небезопасное
    public void unsafeContentHandling(String contentType, String payload) {
        // Ненадежный источник: доверяем строке без проверки
        if ("application/xml".equals(contentType)) {
            processXML(payload); // Прямое доверие данным от клиента
        }
    }

    // Корректная конструкция
    private static final Set<String> ALLOWED_TYPES = Set.of(
            "application/json",
            "text/xml"
    );

    public boolean isAllowedContentType(String contentType) {
        return contentType != null && ALLOWED_TYPES.contains(contentType.split(";")[0].trim());
    }

    public void safeContentHandling(String contentType, String payload) {
        if (isAllowedContentType(contentType)) {
            if (contentType.startsWith("application/json")) {
                processJSON(payload);
            } else if (contentType.startsWith("text/xml")) {
                processXML(payload);
            }
        } else {
            throw new IllegalArgumentException("Unsupported Content-Type: " + contentType);
        }
    }

    // Заглушки обработчиков
    private void processXML(String data) {
        System.out.println("Processing XML safely: " + data);
    }

    private void processJSON(String data) {
        System.out.println("Processing JSON safely: " + data);
    }
}
