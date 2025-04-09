package miem.projects.vulnerabilities.MINOR.FB;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.lang.ref.WeakReference;

public class LG_LOST_LOGGER_DUE_TO_WEAK_REFERENCE {

    // Проблемная реализация
    private WeakReference<Logger> unsafeLoggerRef = 
        new WeakReference<>(LoggerFactory.getLogger(getClass()));

    // Корректная реализация
    private static final Logger SAFE_LOGGER = 
        LoggerFactory.getLogger(LG_LOST_LOGGER_DUE_TO_WEAK_REFERENCE.class);

    public static void main(String[] args) {
        new LG_LOST_LOGGER_DUE_TO_WEAK_REFERENCE().runTests();
    }

    public void runTests() {
        testUnsafeLogging();
        testSafeLogging();
    }

    // Ненадёжное логирование
    public void testUnsafeLogging() {
        Logger logger = unsafeLoggerRef.get();
        if (logger != null) {  // Логгер может быть собран GC
            logger.info("Unsafe log message");
        }
        
        // Принудительная очистка для демонстрации
        System.gc();
        
        if (unsafeLoggerRef.get() == null) {
            System.out.println("Logger was garbage collected!");
        }
    }

    // Надёжное логирование
    public void testSafeLogging() {
        SAFE_LOGGER.info("Safe log message");
        
        // Гарантированно доступен
        assert SAFE_LOGGER != null : "Logger is null!";
    }
}