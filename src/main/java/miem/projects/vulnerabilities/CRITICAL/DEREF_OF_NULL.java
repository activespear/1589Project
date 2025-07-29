package miem.projects.vulnerabilities.CRITICAL;

public class DEREF_OF_NULL {

    // Интерфейс, имитирующий dvfs.getAvailGovernor
    interface GovernorGetter {
        int getAvailGovernor(String resName, String[] availGovernor);
    }

    // Класс DVFS с полем getAvailGovernor
    static class DVFS {
        GovernorGetter getAvailGovernor;
    }

    // Небезопасная конструкция: логическая ошибка в проверке null
    public static int unsafeDereference(DVFS dvfs, String resName, String[] availGovernor) {
        if (dvfs.getAvailGovernor != null)
            return -1;

        // Потенциальный NullPointerException, если getAvailGovernor == null
        return dvfs.getAvailGovernor.getAvailGovernor(resName, availGovernor);
    }

    // Безопасная конструкция: корректная проверка на null
    public static int safeDereference(DVFS dvfs, String resName, String[] availGovernor) {
        if (dvfs.getAvailGovernor == null)
            return -1;

        return dvfs.getAvailGovernor.getAvailGovernor(resName, availGovernor);
    }

    public static void main(String[] args) {
        // Пример данных
        String resName = "cpu0";
        String[] governors = new String[1];

        // DVFS с неназначенным getAvailGovernor (null)
        DVFS dvfsNull = new DVFS();

        // DVFS с корректным getAvailGovernor
        DVFS dvfsValid = new DVFS();
        dvfsValid.getAvailGovernor = (r, g) -> {
            g[0] = "performance";
            return 0;
        };

        System.out.println("Вызов безопасной конструкции с null:");
        int resultSafeNull = safeDereference(dvfsNull, resName, governors);
        System.out.println("Результат: " + resultSafeNull);

        System.out.println("\nВызов безопасной конструкции с корректным объектом:");
        int resultSafeValid = safeDereference(dvfsValid, resName, governors);
        System.out.println("Результат: " + resultSafeValid + ", Governor: " + governors[0]);

        System.out.println("\nВызов небезопасной конструкции с null:");
        try {
            int resultUnsafeNull = unsafeDereference(dvfsNull, resName, governors);
            System.out.println("Результат: " + resultUnsafeNull);
        } catch (NullPointerException e) {
            System.out.println("Произошло исключение: " + e);
        }
    }
}

