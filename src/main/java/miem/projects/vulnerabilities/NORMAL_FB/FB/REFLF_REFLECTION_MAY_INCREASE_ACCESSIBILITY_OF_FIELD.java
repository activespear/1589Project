package miem.projects.vulnerabilities.NORMAL.FB;

import java.lang.reflect.Field;

public class REFLF_REFLECTION_MAY_INCREASE_ACCESSIBILITY_OF_FIELD {
    public static void main(String[] args) throws Exception {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() throws Exception {
        class UnsafeFieldAccessor {
            public void setFieldValue(Object target, Field field, Object value) throws Exception {
                field.setAccessible(true);
                field.set(target, value);
            }
        }

        class Example {
            private String secret = "hidden";
        }

        Example example = new Example();
        Field field = Example.class.getDeclaredField("secret");
        UnsafeFieldAccessor accessor = new UnsafeFieldAccessor();
        accessor.setFieldValue(example, field, "changed");
    }

    public static void correctTest() throws Exception {
        class TrustedClass {
            public String data = "safe";
        }

        TrustedClass trusted = new TrustedClass();
        Field field = TrustedClass.class.getDeclaredField("data");
        setSafeFieldValue(trusted, field, "updated");
    }

    public static void setSafeFieldValue(Object target, Field field, Object value) throws Exception {
        if (field.getDeclaringClass() != TrustedClass.class) {
            throw new SecurityException("Доступ запрещён");
        }
        field.set(target, value);
    }

    public static class TrustedClass {
        public String data = "safe";
    }
}