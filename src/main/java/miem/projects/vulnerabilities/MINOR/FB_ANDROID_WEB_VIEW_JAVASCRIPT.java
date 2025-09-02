package miem.projects.vulnerabilities.MINOR.FB;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

public class FB_ANDROID_WEB_VIEW_JAVASCRIPT extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        incorrectTest();
        correctTest();
    }

    public void incorrectTest() {
        // Некорректно: включен JavaScript без фильтрации URL
        WebView myWebView = (WebView) findViewById(R.id.webview);
        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.loadUrl("https://example.com"); // Потенциально уязвимо для XSS
        System.out.println("Loaded URL without sanitization (INSECURE)");
    }

    public void correctTest() {
        // Корректно: включен JavaScript с фильтрацией URL
        WebView myWebView = (WebView) findViewById(R.id.webview);
        myWebView.getSettings().setJavaScriptEnabled(true);

        String safeUrl = sanitizeUrl("https://example.com");
        myWebView.loadUrl(safeUrl);
        System.out.println("Loaded sanitized URL (SECURE)");
    }

    // Простейший пример фильтрации URL
    private String sanitizeUrl(String url) {
        if (url != null && (url.startsWith("https://") || url.startsWith("http://"))) {
            // Можно добавить дополнительные проверки, например, белый список доменов
            return url;
        }
        return "about:blank"; // Безопасный fallback
    }
}
