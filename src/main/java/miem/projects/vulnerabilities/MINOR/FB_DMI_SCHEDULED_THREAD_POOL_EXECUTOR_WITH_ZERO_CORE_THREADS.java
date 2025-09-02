package miem.projects.vulnerabilities.MINOR.FB;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class FB_DMI_SCHEDULED_THREAD_POOL_EXECUTOR_WITH_ZERO_CORE_THREADS {

    static class ExampleUnsafe {
        public void startTask() {
            Runnable task = () -> System.out.println("Running task (unsafe)");

            // ❌ Ошибка: пул с 0 потоками не сможет выполнять задачи
            ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(0);
            executor.scheduleAtFixedRate(task, 0, 1, TimeUnit.SECONDS);
        }
    }

    static class ExampleSafe {
        public void startTask() {
            Runnable task = () -> System.out.println("Running task (safe)");

            // ✅ Минимум 1 поток для выполнения задач
            ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1);
            executor.scheduleAtFixedRate(task, 0, 1, TimeUnit.SECONDS);
        }
    }

    public static void main(String[] args) {
        new ExampleUnsafe().startTask();
        new ExampleSafe().startTask();
    }
}
