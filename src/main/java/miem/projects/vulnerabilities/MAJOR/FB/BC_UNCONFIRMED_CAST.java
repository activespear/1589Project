package miem.projects.vulnerabilities.MAJOR.FB;

public class BC_UNCONFIRMED_CAST {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        class MyClass {
            public void processObject(Object obj) {
                // Непроверенное приведение типа
                // Может вызвать ClassCastException, если obj не является String
                String str = (String) obj;
                System.out.println(str.length());
            }
        }

        Object obj = 42;
        MyClass myClass = new MyClass();
        myClass.processObject(obj); // Ошибка во время выполнения
    }

    public static void correctTest() {
        class MyClass {
            public void processObject(Object obj) {
                // Проверяем, действительно ли obj является строкой
                if (obj instanceof String) {
                    String str = (String) obj;
                    System.out.println(str.length());
                } else {
                    System.out.println(false);
                }
            }
        }

        Object obj = 42;
        MyClass myClass = new MyClass();
        myClass.processObject(obj); // Безопасная обработка
    }
}