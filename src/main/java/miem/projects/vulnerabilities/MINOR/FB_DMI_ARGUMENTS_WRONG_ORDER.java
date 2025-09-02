package miem.projects.vulnerabilities.MINOR.FB;

import com.google.common.base.Preconditions;

public class FB_DMI_ARGUMENTS_WRONG_ORDER {

    static class ExampleUnsafe {
        public void example(String message) {
            Preconditions.checkNotNull("message", message); // Неверный порядок
        }
    }

    static class ExampleSafe {
        public void example(String message) {
            Preconditions.checkNotNull(message, "message");
        }
    }

    public static void main(String[] args) {
        ExampleSafe safe = new ExampleSafe();
        safe.example("Test message");
    }
}
