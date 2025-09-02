package miem.projects.vulnerabilities.MINOR.FB;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class FB_NP_METHOD_RETURN_RELAXING_ANNOTATION {

    // Потенциально небезопасное использование
    static class ParentClassUnsafe {
        @Nonnull
        public String getName() {
            return "Parent";
        }
    }

    static class ChildClassUnsafe extends ParentClassUnsafe {
        // ❌ Нарушение контракта: метод родителя возвращает @Nonnull, а здесь @Nullable
        @Nullable
        @Override
        public String getName() {
            return null;
        }
    }

    // Корректная конструкция
    static class ParentClassSafe {
        @Nonnull
        public String getName() {
            return "Parent";
        }
    }

    static class ChildClassSafe extends ParentClassSafe {
        // ✅ Соблюдение контракта: метод возвращает ненулевое значение
        @Nonnull
        @Override
        public String getName() {
            return "Child";
        }
    }

    public static void main(String[] args) {
        ParentClassSafe parent = new ParentClassSafe();
        ChildClassSafe child = new ChildClassSafe();

        System.out.println(parent.getName());
        System.out.println(child.getName());
    }
}
