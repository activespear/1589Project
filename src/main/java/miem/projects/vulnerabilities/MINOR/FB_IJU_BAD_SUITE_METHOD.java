package miem.projects.vulnerabilities.MINOR.FB;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class FB_IJU_BAD_SUITE_METHOD {

    public static class MyTestCaseUnsafe extends TestCase {

        public Test suite() { // Ошибка: должен быть static
            TestSuite suite = new TestSuite();
            suite.addTest(new MyTestCaseUnsafe("testMethod1"));
            return suite;
        }

        public MyTestCaseUnsafe(String name) {
            super(name);
        }

        public void testMethod1() {
            assertTrue(true);
        }
    }

    public static class MyTestCaseSafe extends TestCase {

        public static Test suite() { // Исправлено: метод статический
            TestSuite suite = new TestSuite();
            suite.addTest(new MyTestCaseSafe("testMethod1"));
            return suite;
        }

        public MyTestCaseSafe(String name) {
            super(name);
        }

        public void testMethod1() {
            assertTrue(true);
        }
    }
}
