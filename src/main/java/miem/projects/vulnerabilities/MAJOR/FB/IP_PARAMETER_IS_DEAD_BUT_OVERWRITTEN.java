package miem.projects.vulnerabilities.MAJOR.FB;

import java.util.concurrent.atomic.AtomicInteger;

public class IP_PARAMETER_IS_DEAD_BUT_OVERWRITTEN {
    public static void main(String[] args) {

    }

    public static void incorrectTest(int value) {
        value = 42;
        // ...
        // В Java примитивные типы передаются по значению,
        // поэтому изменение никак не повлияет на
        // вызывающий код
    }

    public static void correctTest(int value) {
        // ... преобразования с value
        // или же передать AtomicInteger вместо int
        // и изменять его:
        AtomicInteger x = new AtomicInteger(value);
        x.set(42);
    }
}