package miem.projects.vulnerabilities.MINOR;

public class UNUSED_PARAM {
    public static void main(String[] args) {
        UserProcessor processor = new UserProcessor();
        processor.processUser("Alice", 25);
        processor.processUser(30);
    }

    // Потенциально небезопасное: параметр name не используется
    static class UserProcessor {
        public void processUserUnsafe(String name, int age) {
            name = "Default User";  // Перезапись без использования исходного значения
            System.out.println("Processing: " + name + ", Age: " + age);
        }

        // Корректная конструкция, вариант 1: параметр используется
        public void processUser(String name, int age) {
            if (name == null) {
                name = "Default User";
            }
            System.out.println("Processing: " + name + ", Age: " + age);
        }

        // Корректная конструкция, вариант 2: параметр удален
        public void processUser(int age) {
            String name = "Default User";
            System.out.println("Processing: " + name + ", Age: " + age);
        }
    }
}
