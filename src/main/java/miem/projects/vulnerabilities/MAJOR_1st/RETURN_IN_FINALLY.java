package miem.projects.vulnerabilities.MAJOR_1st;

public class RETURN_IN_FINALLY {

    static class UnsafeReturn {
        static int getValue() {
            try {
                return 10;
            } finally {
                return 20;  // Перезаписывает возвращаемое значение — небезопасно
            }
        }
    }

    static class SafeReturn {
        static int getValue() {
            int result = 10;
            try {
                return result;
            } finally {
                System.out.println("Cleanup done");
                // Нет перезаписи возвращаемого значения
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("UnsafeReturn.getValue() возвращает: " + UnsafeReturn.getValue());
        System.out.println("SafeReturn.getValue() возвращает: " + SafeReturn.getValue());
    }
}

