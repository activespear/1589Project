package miem.projects.vulnerabilities.MAJOR_1st;

public class OVERFLOW_AFTER_CHECK_MACRO {

    static class UnsafeLoopExample {
        static void unsafeLoop(int n) {
            int[] array = new int[10];
            for (int i = 0; i < n; i++) {
                // Индекс может выйти за пределы массива
                array[i] = i;
            }
        }
    }

    static class SafeLoopExample {
        static void safeLoop(int n) {
            int[] array = new int[10];
            for (int i = 0; i < n && i < array.length; i++) {
                // Защищаем доступ к элементам массива
                array[i] = i;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Запуск unsafeLoop с n=5 (безопасный вызов):");
        UnsafeLoopExample.unsafeLoop(5);

        System.out.println("Запуск unsafeLoop с n=15 (выход за пределы массива):");
        try {
            UnsafeLoopExample.unsafeLoop(15);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Поймано исключение: " + e);
        }

        System.out.println("Запуск safeLoop с n=15 (безопасный вызов):");
        SafeLoopExample.safeLoop(15);
    }
}

