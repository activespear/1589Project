package miem.projects.vulnerabilities.MINOR.FB;

import junit.framework.TestCase;

public class FB_IJU_SETUP_NO_SUPER {

    public static class MyTestCaseUnsafe extends TestCase {

        @Override
        protected void setUp() throws Exception {
            // Не вызывается super.setUp()
            System.out.println("Custom setUp");
        }
    }

    public static class MyTestCaseSafe extends TestCase {

        @Override
        protected void setUp() throws Exception {
            super.setUp();  // Вызов родительского метода setUp
            System.out.println("Custom setUp");
        }
    }
}
