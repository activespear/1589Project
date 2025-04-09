package miem.projects.vulnerabilities.MINOR.FB;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FB_DM_USELESS_THREAD {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    // Некорректная реализация с созданием бесполезных потоков
    public static void incorrectTest() {
        // Плохо: создание потока без реальной необходимости
        new Thread(() -> {
            System.out.println("Этот поток не делает ничего полезного");
        }).start();

        // Плохо: создание потока, который сразу завершается
        new Thread(() -> {}).start();

        // Плохо: создание потока без сохранения ссылки (нельзя контролировать)
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                System.out.println("Бесполезный поток #" + Thread.currentThread().getId());
            }).start();
        }
    }

    // Корректная реализация с использованием пула потоков
    public static void correctTest() {
        // Хорошо: использование пула потоков
        ExecutorService executor = Executors.newFixedThreadPool(2);

        // Полезная работа в потоке
        executor.submit(() -> {
            System.out.println("Полезная задача 1 выполняется в пуле потоков");
        });

        // Еще одна полезная задача
        executor.submit(() -> {
            System.out.println("Полезная задача 2 выполняется в пуле потоков");
        });

        // Завершение работы пула
        executor.shutdown();
    }

    // Альтернатива: выполнение в текущем потоке, если задача простая
    public static void correctAlternative() {
        // Если задача небольшая, можно выполнить в текущем потоке
        System.out.println("Простая задача выполняется в основном потоке");
    }
}