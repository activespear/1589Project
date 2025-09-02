package miem.projects.vulnerabilities.MINOR.FB;

import junit.framework.TestCase;

public class FB_IJU_NO_TESTS {

    public static class MyTestCaseUnsafe extends TestCase {
        // Пустой класс — тестов нет
    }

    public static class MyTestCaseSafe extends TestCase {

        public void testExample() { // Метод с префиксом "test"
            assertTrue(true);
        }
    }
}
