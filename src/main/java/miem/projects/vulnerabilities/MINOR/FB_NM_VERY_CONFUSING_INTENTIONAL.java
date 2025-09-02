package miem.projects.vulnerabilities.MINOR.FB;

public class FB_NM_VERY_CONFUSING_INTENTIONAL {

    static class ExampleUnsafe {

        public void processData() {
            System.out.println("Processing data");
        }

        public void processdata() {
            System.out.println("Processing data");
        }
    }

    static class ExampleSafe {

        public void processData() {
            System.out.println("Processing data");
        }

        public void processDataFromFile() {
            System.out.println("Processing data from file");
        }
    }

    public static void main(String[] args) {
        ExampleSafe example = new ExampleSafe();

        example.processData();          // Processing data
        example.processDataFromFile();  // Processing data from file
    }


}