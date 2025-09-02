package miem.projects.vulnerabilities.MINOR.FB;

public class FB_DLS_DEAD_STORE_OF_CLASS_LITERAL {

    static class ExampleUnsafe {
        public void example() {
            // ❌ Потенциально небезопасно: присваивание класса не используется
            Class<?> unused = String.class;
        }
    }

    static class ExampleSafe {
        public void example() {
            // ✅ Лишнее присваивание удалено
        }
    }

    public static void main(String[] args) {
        ExampleSafe safe = new ExampleSafe();
        safe.example();
    }
}
