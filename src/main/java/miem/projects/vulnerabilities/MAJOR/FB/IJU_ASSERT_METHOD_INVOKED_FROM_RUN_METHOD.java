package miem.projects.vulnerabilities.MAJOR.FB;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class IJU_ASSERT_METHOD_INVOKED_FROM_RUN_METHOD {

    @Test
    public void incorrectTest() throws InterruptedException {
        Thread thread = new Thread(
                () -> {
                    // Не выполнится
                    assertEquals(1, 2);
                });

        thread.start();
        thread.join();
    }

    @Test
    public void correctTest() throws InterruptedException {
        final int[] result = new int[1];

        Thread thread = new Thread(
                () -> {
                    result[0] = 1;
                }
        );

        thread.start();
        thread.join();

        // В основном потоке выполнится
        assertEquals(1, result[0]);
    }
}
