package miem.projects.vulnerabilities.MAJOR.FB;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;


public class NP_METHOD_PARAMETER_TIGHTENS_ANNOTATION {

    // Небезопасная версия
    static class BaseUnsafe {
        void process(@Nullable String input) {
            System.out.println("BaseUnsafe.process called");
        }
    }

    static class DerivedUnsafe extends BaseUnsafe {
        @Override
        void process(@Nonnull String input) {
            // Параметр сужен до Nonnull — это нарушение Liskov Substitution Principle
            System.out.println("DerivedUnsafe.process length: " + input.length());
        }
    }

    // Безопасная версия
    static class BaseSafe {
        void process(@Nullable String input) {
            if (input != null) {
                System.out.println("BaseSafe.process length: " + input.length());
            } else {
                System.out.println("BaseSafe.process input is null");
            }
        }
    }

    static class DerivedSafe extends BaseSafe {
        @Override
        void process(@Nullable String input) {
            if (input != null) {
                System.out.println("DerivedSafe.process length: " + input.length());
            } else {
                System.out.println("DerivedSafe.process input is null");
            }
        }
    }

    // Запуск небезопасной версии (может вызвать NPE, если передать null)
    static void runUnsafe() {
        BaseUnsafe base = new DerivedUnsafe();
        System.out.println("Running unsafe with non-null input:");
        base.process("Hello");

        System.out.println("Running unsafe with null input:");
        // Здесь будет вызван метод DerivedUnsafe.process(@Nonnull), но параметр null передан!
        // В реальности при вызове через BaseUnsafe ссылка типом BaseUnsafe, аннотация не проверяется,
        // но логически это нарушение — Derived требует non-null, а базовый допускает null.
        base.process(null); // NullPointerException, тк DerivedUnsafe.process не проверяет null
    }

    // Запуск безопасной версии
    static void runSafe() {
        BaseSafe base = new DerivedSafe();
        System.out.println("Running safe with non-null input:");
        base.process("Hello");

        System.out.println("Running safe with null input:");
        base.process(null); // Безопасно, проверка есть
    }

    public static void main(String[] args) {
        System.out.println("=== Unsafe version ===");
        try {
            runUnsafe();
        } catch (Exception e) {
            System.out.println("Caught exception in unsafe: " + e);
        }

        System.out.println("\n=== Safe version ===");
        runSafe();
    }
}

