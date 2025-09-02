package miem.projects.vulnerabilities.MINOR.FB;

public class FB_NM_WRONG_PACKAGE_INTENTIONAL {

    static class SuperClass {
        public void processData(String data) {
            System.out.println("Processing data in SuperClass");
        }
    }

    static class SubClass extends SuperClass {
        @Override
        public void processData(String data) {
            System.out.println("Processing data in SubClass");
        }
    }

    public static void main(String[] args) {
        SubClass obj = new SubClass();
        obj.processData("Test");  // Processing data in SubClass
    }
}
