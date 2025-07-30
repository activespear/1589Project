package miem.projects.vulnerabilities.MAJOR_1st;

public class DEFAULT_CASE_MISSING {

    enum Status { OK, ERROR, UNKNOWN }

    // Небезопасная версия — не обрабатывает UNKNOWN и нет default
    public static void handleUnsafe(Status status) {
        switch (status) {
            case OK:
                System.out.println("OK");
                break;
            case ERROR:
                System.out.println("ERROR");
                break;
            // UNKNOWN не обработан, нет default — может привести к ошибкам логики
        }
    }

    // Безопасная версия — обрабатывает все enum значения и добавляет default
    public static void handleSafe(Status status) {
        switch (status) {
            case OK:
                System.out.println("OK");
                break;
            case ERROR:
                System.out.println("ERROR");
                break;
            case UNKNOWN:
                System.out.println("UNKNOWN");
                break;
            default:
                throw new IllegalArgumentException("Unhandled status: " + status);
        }
    }

    public static void main(String[] args) {
        System.out.println("Unsafe handling:");
        handleUnsafe(Status.OK);
        handleUnsafe(Status.ERROR);
        handleUnsafe(Status.UNKNOWN); // Здесь ничего не выведется

        System.out.println("\nSafe handling:");
        handleSafe(Status.OK);
        handleSafe(Status.ERROR);
        handleSafe(Status.UNKNOWN);
    }
}

