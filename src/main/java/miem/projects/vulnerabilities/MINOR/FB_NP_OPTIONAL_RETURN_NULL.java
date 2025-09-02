package miem.projects.vulnerabilities.MINOR.FB;

import java.util.Optional;

public class FB_NP_OPTIONAL_RETURN_NULL {

    // Потенциально небезопасное использование
    static class ExampleUnsafe {

        public Optional<String> getUserName(boolean userExists) {
            if (userExists) {
                return Optional.of("John Doe");
            } else {
                // ❌ Нарушение контракта: возвращается null вместо Optional.empty()
                return null;
            }
        }
    }

    // Корректная конструкция
    static class ExampleSafe {

        public Optional<String> getUserName(boolean userExists) {
            if (userExists) {
                return Optional.of("John Doe");
            } else {
                // ✅ Возвращаем Optional.empty() для отсутствующего значения
                return Optional.empty();
            }
        }
    }

    public static void main(String[] args) {
        ExampleSafe example = new ExampleSafe();

        Optional<String> user1 = example.getUserName(true);
        Optional<String> user2 = example.getUserName(false);

        System.out.println(user1.orElse("No user")); // John Doe
        System.out.println(user2.orElse("No user")); // No user
    }
}

