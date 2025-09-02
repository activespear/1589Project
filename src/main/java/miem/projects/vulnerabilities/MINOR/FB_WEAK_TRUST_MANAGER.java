package miem.projects.vulnerabilities.MINOR.FB;

import javax.net.ssl.*;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.logging.Logger;

public class FB_WEAK_TRUST_MANAGER {

    private static final Logger logger = Logger.getLogger(FB_WEAK_TRUST_MANAGER.class.getName());

    public static void main(String[] args) throws Exception {
        incorrectTest();
        correctTest();
    }

    // Потенциально небезопасное доверие ко всем сертификатам
    public static void incorrectTest() throws Exception {
        TrustManager[] trustAllCerts = new TrustManager[]{
                new X509TrustManager() {
                    public void checkClientTrusted(X509Certificate[] chain, String authType) {}
                    public void checkServerTrusted(X509Certificate[] chain, String authType) {}
                    public X509Certificate[] getAcceptedIssuers() { return new X509Certificate[0]; }
                }
        };
        SSLContext sc = SSLContext.getInstance("SSL");
        sc.init(null, trustAllCerts, new SecureRandom());
        logger.warning("SSL context initialized with trust-all certificates (unsafe)");
    }

    // Корректная конструкция с проверкой доверенного хранилища
    public static void correctTest() throws Exception {
        KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
        try (InputStream trustStream = new FileInputStream("truststore.jks")) {
            trustStore.load(trustStream, "password".toCharArray());
        }

        TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        tmf.init(trustStore);

        SSLContext sc = SSLContext.getInstance("TLS");
        sc.init(null, tmf.getTrustManagers(), new SecureRandom());
        logger.info("SSL context initialized with trusted KeyStore (safe)");
    }
}
