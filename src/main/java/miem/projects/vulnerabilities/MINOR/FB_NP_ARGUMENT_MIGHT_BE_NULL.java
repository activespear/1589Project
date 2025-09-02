package miem.projects.vulnerabilities.MINOR.FB;

public class FB_NP_ARGUMENT_MIGHT_BE_NULL {

    static class Unsafe {
        public void myMethod(String str) {
            int length = str.length();
            System.out.println("Длина строки: " + length);
        }
    }

    static class Safe {
        public void myMethod(String str) {
            if (str != null) {
                int length = str.length();
                System.out.println("Длина строки: " + length);
            } else {
                System.out.println("Строка пуста");
            }
        }
    }

    public static void main(String[] args) {
        Safe safe = new Safe();
        safe.myMethod(null);
        safe.myMethod("Пример");
    }
}
