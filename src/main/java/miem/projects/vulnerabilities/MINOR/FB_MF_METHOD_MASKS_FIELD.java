package miem.projects.vulnerabilities.MINOR.FB;

public class FB_MF_METHOD_MASKS_FIELD {

    static class Unsafe {
        private int value;

        public void setValue(int value) {
            int valueLocal = 10;
        }
    }

    static class Safe {
        private int value;

        public void setValue(int newValue) {
            value = newValue;
        }
    }

    public static void main(String[] args) {
        Unsafe unsafe = new Unsafe();
        Safe safe = new Safe();

        safe.setValue(20);

        System.out.println(safe.value);
    }
}
