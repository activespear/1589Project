package miem.projects.vulnerabilities.MINOR.FB;

import junit.framework.TestCase;

public class FB_IJU_TEARDOWN_NO_SUPER {

    public static class MyTestCaseUnsafe extends TestCase {

        @Override
        public void tearDown() {
            // Не вызывается super.tearDown(), возможны ошибки очистки
            System.out.println("Custom tearDown");
        }
    }

    public static class MyTestCaseSafe extends TestCase {

        @Override
        public void tearDown() {
            super.tearDown();  // Вызов родительского метода tearDown
            System.out.println("Custom tearDown");
        }
    }
}
