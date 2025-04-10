package miem.projects.vulnerabilities.MAJOR.FB;

public class FI_PUBLIC_SHOULD_BE_PROTECTED {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        class Test {
            @Override
            public void finalize() throws Throwable {
                try {
                    System.out.println("Finalize method called");
                } finally {
                    super.finalize();
                }
            }
        }
    }

    public static void correctTest() {
        class Test {
            @Override
            protected void finalize() throws Throwable {
                try {
                    System.out.println("Finalize method called");
                } finally {
                    super.finalize();
                }
            }
        }
    }
}
