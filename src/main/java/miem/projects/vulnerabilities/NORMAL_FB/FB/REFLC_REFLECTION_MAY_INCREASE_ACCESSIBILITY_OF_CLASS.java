package miem.projects.vulnerabilities.NORMAL.FB;

import java.beans.Beans;

public class REFLC_REFLECTION_MAY_INCREASE_ACCESSIBILITY_OF_CLASS {
    public static void main(String[] args) throws Exception {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() throws Exception {
        class UnsafeFactory {
            public Object createInstance(Class<?> clazz) throws Exception {
                return clazz.newInstance();
            }
        }

        UnsafeFactory factory = new UnsafeFactory();
        Object obj = factory.createInstance(String.class);
        System.out.println(obj);
    }

    public static void correctTest() throws Exception {
        Object obj = createBean(String.class.getClassLoader(), "java.lang.String");
        System.out.println(obj);
    }

    public static Object createBean(ClassLoader loader, String className) throws Exception {
        return Beans.instantiate(loader, className);
    }
}
