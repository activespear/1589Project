package miem.projects.vulnerabilities.MINOR.FB;

public class FB_NP_ALWAYS_NULL {

    static class Unsafe {
        public void myMethod() {
            String str = null;
            int length = str.length();
        }
    }

    static class Safe {
        public void myMethod() {
            String str = null;
            if (str != null) {
                int length = str.length();
            } else {
                System.out.println("Строка пуста");
            }
        }
    }

    public static void main(String[] args) {
        Safe safe = new Safe();
        safe.myMethod();
    }
}
