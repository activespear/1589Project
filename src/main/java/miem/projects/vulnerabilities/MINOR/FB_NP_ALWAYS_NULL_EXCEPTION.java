package miem.projects.vulnerabilities.MINOR.FB;

public class FB_NP_ALWAYS_NULL_EXCEPTION {

    static class Unsafe {
        public void myMethod() {
            String str = null;
            try {
                int length = str.length();
            } catch (NullPointerException e) {
                System.out.println(str.length());
            }
        }
    }

    static class Safe {
        public void myMethod() {
            String str = null;
            try {
                if (str != null) {
                    int length = str.length();
                } else {
                    System.out.println("Строка пуста");
                }
            } catch (NullPointerException e) {
                System.out.println("Ошибка: строка пуста");
            }
        }
    }

    public static void main(String[] args) {
        Safe safe = new Safe();
        safe.myMethod();
    }
}
