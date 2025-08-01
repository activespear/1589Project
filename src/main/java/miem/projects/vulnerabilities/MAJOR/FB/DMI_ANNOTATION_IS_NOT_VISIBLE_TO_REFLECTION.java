package miem.projects.vulnerabilities.MAJOR.FB;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class DMI_ANNOTATION_IS_NOT_VISIBLE_TO_REFLECTION {

    // Небезопасная аннотация без RetentionPolicy.RUNTIME
    @interface UnsafeAnnotation {}

    @UnsafeAnnotation
    static class UnsafeClass {}

    // Безопасная аннотация с RetentionPolicy.RUNTIME
    @Retention(RetentionPolicy.RUNTIME)
    @interface SafeAnnotation {}

    @SafeAnnotation
    static class SafeClass {}

    public static boolean unsafeCheck() {
        // Проверка аннотации без runtime retention — всегда false
        return UnsafeClass.class.isAnnotationPresent(UnsafeAnnotation.class);
    }

    public static boolean safeCheck() {
        // Проверка аннотации с runtime retention — true
        return SafeClass.class.isAnnotationPresent(SafeAnnotation.class);
    }

    public static void main(String[] args) {
        System.out.println("Unsafe annotation present: " + unsafeCheck());
        System.out.println("Safe annotation present: " + safeCheck());
    }
}

