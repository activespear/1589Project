package miem.projects.vulnerabilities.MINOR.FB;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class FB_STCAL_STATIC_CALENDAR_INSTANCE {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
        bestPracticeTest();
    }

    // Некорректная реализация со статическим Calendar
    public static class DateUtilsIncorrect {
        private static final Calendar sharedCalendar = Calendar.getInstance(); // Опасное статическое поле

        public static String formatCurrentDate() {
            sharedCalendar.setTime(new Date()); // Модификация в многопоточной среде
            return String.format("%tF", sharedCalendar);
        }
    }

    // Корректная реализация с созданием нового экземпляра
    public static class DateUtilsCorrect {
        public static String formatCurrentDate() {
            Calendar calendar = Calendar.getInstance(); // Новый экземпляр для каждого вызова
            calendar.setTime(new Date());
            return String.format("%tF", calendar);
        }
    }

    // Лучшая практика с ThreadLocal
    public static class DateUtilsThreadSafe {
        private static final ThreadLocal<Calendar> threadLocalCalendar = 
            ThreadLocal.withInitial(() -> Calendar.getInstance());

        public static String formatCurrentDate() {
            Calendar calendar = threadLocalCalendar.get();
            calendar.setTime(new Date());
            return String.format("%tF", calendar);
        }
    }

    // Современная альтернатива с java.time (Java 8+)
    public static class DateUtilsModern {
        public static String formatCurrentDate() {
            return java.time.LocalDate.now().toString(); // Неизменяемый и потокобезопасный
        }
    }

    public static void incorrectTest() {
        Runnable task = () -> {
            String date = DateUtilsIncorrect.formatCurrentDate();
            System.out.println(Thread.currentThread().getName() + ": " + date);
        };

        // В многопоточной среде могут быть некорректные результаты
        new Thread(task, "Thread-1").start();
        new Thread(task, "Thread-2").start();
    }

    public static void correctTest() {
        Runnable task = () -> {
            String date = DateUtilsCorrect.formatCurrentDate();
            System.out.println(Thread.currentThread().getName() + ": " + date);
        };

        new Thread(task, "Thread-1").start();
        new Thread(task, "Thread-2").start();
    }

    public static void bestPracticeTest() {
        Runnable task = () -> {
            String date = DateUtilsModern.formatCurrentDate();
            System.out.println(Thread.currentThread().getName() + ": " + date);
        };

        new Thread(task, "Thread-1").start();
        new Thread(task, "Thread-2").start();
    }
}