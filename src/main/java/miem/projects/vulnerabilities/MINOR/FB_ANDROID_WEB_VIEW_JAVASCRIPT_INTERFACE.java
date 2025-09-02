package miem.projects.vulnerabilities.MINOR.FB;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

public class FB_ANDROID_WEB_VIEW_JAVASCRIPT_INTERFACE extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        incorrectTest();
        correctTest();
    }

    public void incorrectTest() {
        // Некорректно: включен JavaScript с добавлением интерфейса
        WebView webView = findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.addJavascriptInterface(new Object() {
            @android.webkit.JavascriptInterface
            public void doSomething(String data) {
                // Потенциально опасный код
            }
        }, "Android");
        webView.loadUrl("https://example.com");
        System.out.println("WebView with JS interface loaded (INSECURE)");
    }

    public void correctTest() {
        // Корректно: JavaScript отключен, интерфейс не добавляется
        WebView webView = findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(false);
        webView.loadUrl("https://example.com");
        System.out.println("WebView loaded safely without JS interface (SECURE)");
    }
}
