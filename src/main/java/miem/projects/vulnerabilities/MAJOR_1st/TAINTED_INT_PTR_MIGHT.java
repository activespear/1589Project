package miem.projects.vulnerabilities.MAJOR_1st;

public class TAINTED_INT_PTR_MIGHT {

    // Небезопасный метод: парсит значение из окружения без проверки и индексирует массив без границ
    public void unsafeSetBase(char[] base, boolean flag) {
        String env = System.getenv("QQQ");
        int size = Integer.parseInt(env); // Может выбросить NumberFormatException
        if (flag) {
            base[size] = '\0'; // Может привести к ArrayIndexOutOfBoundsException
        }
    }

    // Безопасный метод: обрабатывает NumberFormatException и проверяет границы массива
    public void safeSetBase(char[] base, boolean flag) {
        String env = System.getenv("QQQ");
        int size;
        try {
            size = Integer.parseInt(env);
        } catch (NumberFormatException e) {
            System.out.println("Invalid integer in environment variable QQQ");
            return;
        }
        if (flag && size >= 0 && size < base.length) {
            base[size] = '\0';
        } else {
            System.out.println("Flag is false or size out of bounds");
        }
    }

    // Запуск обеих версий для демонстрации
    public static void main(String[] args) {
        TAINTED_INT_PTR_MIGHT example = new TAINTED_INT_PTR_MIGHT();

        char[] base = new char[10];

        System.out.println("Running unsafeSetBase:");
        try {
            example.unsafeSetBase(base, true);
            System.out.println("unsafeSetBase executed without exception");
        } catch (NumberFormatException e) {
            System.out.println("Caught NumberFormatException in unsafeSetBase");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Caught ArrayIndexOutOfBoundsException in unsafeSetBase");
        }

        System.out.println("\nRunning safeSetBase:");
        example.safeSetBase(base, true);
    }
}
