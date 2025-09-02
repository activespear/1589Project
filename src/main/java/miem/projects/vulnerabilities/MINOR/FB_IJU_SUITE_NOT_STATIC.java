package miem.projects.vulnerabilities.MINOR.FB;

import junit.framework.TestCase;
import junit.framework.Test;
import junit.framework.TestSuite;

public class FB_IJU_SUITE_NOT_STATIC {

    public static class MyTestCaseUnsafe extends TestCase {

        // Метод не статический — может вызвать ошибки при запуске тестов
        public Test suite() {
            TestSuite suite = new TestSuite();
            suite.addTest(new MyTestCaseUnsafe("testMethod1"));
            return suite;
        }

        public void testMethod1() {
            assertTrue(true);
        }
    }

    public static class MyTestCaseSafe extends TestCase {

        // Метод статический — корректно используется TestSuite
        public static Test suite() {
            TestSuite suite = new TestSuite();
            suite.addTest(new MyTestCaseSafe("testMethod1"));
            return suite;
        }

        public void testMethod1() {
            assertTrue(true);
        }
    }
}
