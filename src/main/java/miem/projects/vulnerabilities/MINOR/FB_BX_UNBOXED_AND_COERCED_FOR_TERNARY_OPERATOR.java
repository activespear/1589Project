package miem.projects.vulnerabilities.MINOR.FB;

public class FB_BX_UNBOXED_AND_COERCED_FOR_TERNARY_OPERATOR {

    // Потенциально небезопасное использование тернарного оператора
    static class ExampleUnsafe {
        public Number compute(boolean condition) {
            // ❌ Integer распаковывается, приводится к float и снова упаковывается
            Number result = condition ? Integer.valueOf(10) : Float.valueOf(20.5f);
            return result;
        }
    }

    // Корректная конструкция
    static class ExampleSafe {
        public Number compute(boolean condition) {
            // ✅ Одинаковый тип Float — нет распаковки и приведения типов
            Number result = condition ? Float.valueOf(10.0f) : Float.valueOf(20.5f);
            return result;
        }
    }

    public static void main(String[] args) {
        ExampleSafe safe = new ExampleSafe();

        Number r1 = safe.compute(true);
        Number r2 = safe.compute(false);

        System.out.println(r1); // 10.0
        System.out.println(r2); // 20.5
    }
}
