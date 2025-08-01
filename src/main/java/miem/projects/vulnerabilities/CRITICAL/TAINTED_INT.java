package miem.projects.vulnerabilities.CRITICAL;

public class TAINTED_INT {

    private long size;
    private byte[] res;

    // Небезопасная версия — без проверки размера
    public void unsafeTest() {
        String env = System.getenv("QQQ");
        System.out.println("Небезопасная версия, получено значение QQQ: " + env);
        size = Long.parseLong(env);
        // Может привести к ошибке, если size отрицательное или больше Integer.MAX_VALUE
        res = new byte[(int) size];
        System.out.println("Массив создан размером: " + res.length);
    }

    // Безопасная версия — с проверками
    public void safeTest() {
        String env = System.getenv("QQQ");
        System.out.println("Безопасная версия, получено значение QQQ: " + env);
        try {
            size = Long.parseLong(env);
            if (size < 0 || size > Integer.MAX_VALUE) {
                throw new IllegalArgumentException("Invalid size");
            }
            res = new byte[(int) size];
            System.out.println("Массив создан размером: " + res.length);
        } catch (IllegalArgumentException e) {
            System.err.println("Ошибка: неверный ввод или размер - " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        TAINTED_INT example = new TAINTED_INT();

        try {
            System.out.println("=== Запуск небезопасной версии ===");
            example.unsafeTest();
        } catch (Exception e) {
            System.err.println("Поймано исключение в небезопасной версии: " + e);
        }

        System.out.println("\n=== Запуск безопасной версии ===");
        example.safeTest();
    }
}

