package miem.projects.vulnerabilities.MINOR.FB;

public class FB_DMI_DOH {

    static class ExampleUnsafe {
        public void doSomething() {
            someMethod(10);
        }

        public void someMethod(String value) {
            System.out.println("Value: " + value);
        }
    }

    static class ExampleSafe {
        public void doSomething() {
            someMethod("10");
        }

        public void someMethod(String value) {
            System.out.println("Value: " + value);
        }
    }

    public static void main(String[] args) {
        ExampleSafe safe = new ExampleSafe();
        safe.doSomething();
    }
}
