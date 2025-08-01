package miem.projects.vulnerabilities.MAJOR.FB;

public class BIT_SIGNED_CHECK {

    // Имитация флага, аналогичного SWT.SELECTED
    public static final int SELECTED = 0x80000000; // Самый старший бит (отрицательное значение при интерпретации как signed int)

    static class Event {
        int detail;

        Event(int detail) {
            this.detail = detail;
        }
    }

    // Небезопасная проверка: использование > 0 на результате побитовой операции
    public static void unsafeBitCheck(Event event) {
        if ((event.detail & SELECTED) > 0) {
            System.out.println("Unsafe: Action performed");
        } else {
            System.out.println("Unsafe: Condition not met");
        }
    }

    // Безопасная проверка: сравнение с нулём
    public static void safeBitCheck(Event event) {
        if ((event.detail & SELECTED) != 0) {
            System.out.println("Safe: Action performed");
        } else {
            System.out.println("Safe: Condition not met");
        }
    }

    public static void main(String[] args) {
        // Пример, когда установлен флаг SELECTED (в виде старшего бита)
        Event eventWithFlag = new Event(SELECTED);

        System.out.println("Running unsafeBitCheck:");
        unsafeBitCheck(eventWithFlag);

        System.out.println("\nRunning safeBitCheck:");
        safeBitCheck(eventWithFlag);
    }
}

