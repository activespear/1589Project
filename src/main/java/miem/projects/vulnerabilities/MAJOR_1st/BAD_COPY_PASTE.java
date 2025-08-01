package miem.projects.vulnerabilities.MAJOR_1st;

public class BAD_COPY_PASTE {

    // Небезопасная конструкция с ошибочным дублирующимся условием
    public boolean unsafeCheckAccess(String role) {
        if ("admin".equals(role)) {
            return true;
        } else if ("user".equals(role)) {
            return true;
        } else if ("user".equals(role)) { // Дублирование вместо "guest"
            return false;
        }
        return false;
    }

    // Безопасная конструкция с корректной логикой
    public boolean safeCheckAccess(String role) {
        if ("admin".equals(role)) {
            return true;
        } else if ("user".equals(role)) {
            return true;
        } else if ("guest".equals(role)) {
            return false;
        }
        return false;
    }

    public static void main(String[] args) {
        BAD_COPY_PASTE example = new BAD_COPY_PASTE();

        System.out.println("=== Небезопасная версия ===");
        System.out.println("admin: " + example.unsafeCheckAccess("admin")); // true
        System.out.println("user: " + example.unsafeCheckAccess("user"));   // true
        System.out.println("guest: " + example.unsafeCheckAccess("guest")); // false (но причина в ошибке)

        System.out.println("\n=== Безопасная версия ===");
        System.out.println("admin: " + example.safeCheckAccess("admin"));   // true
        System.out.println("user: " + example.safeCheckAccess("user"));     // true
        System.out.println("guest: " + example.safeCheckAccess("guest"));   // false (корректно)
    }
}
