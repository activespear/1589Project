package miem.projects.vulnerabilities.MINOR.FB;

public class FB_DMI_LONG_BITS_TO_DOUBLE_INVOKED_ON_INT {

    static class ExampleUnsafe {
        public void convert() {
            int x = 12345;
            double result = Double.longBitsToDouble(x);
            System.out.println("Result (unsafe): " + result);
        }
    }

    static class ExampleSafe {
        public void convert() {
            int x = 12345;
            double result = Double.longBitsToDouble((long) x);
            System.out.println("Result (safe): " + result);
        }
    }

    public static void main(String[] args) {
        new ExampleUnsafe().convert();
        new ExampleSafe().convert();
    }
}
