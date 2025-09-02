package miem.projects.vulnerabilities.MINOR.FB;

import java.util.concurrent.ScheduledThreadPoolExecutor;

public class FB_DMI_FUTILE_ATTEMPT_TO_CHANGE_MAXPOOL_SIZE_OF_SCHEDULED_THREAD_POOL_EXECUTOR {

    static class ExampleUnsafe {
        public void createExecutor() {
            ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(5);
            executor.setMaximumPoolSize(10);
            System.out.println("Executor created (unsafe)");
        }
    }

    static class ExampleSafe {
        public void createExecutor() {
            ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(5);
            System.out.println("Executor created (safe)");
        }
    }

    public static void main(String[] args) {
        new ExampleUnsafe().createExecutor();
        new ExampleSafe().createExecutor();
    }
}
