package miem.projects.vulnerabilities.MAJOR.FB;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class HE_EQUALS_USE_HASHCODE {

    // Небезопасная реализация: equals без hashCode
    static class BookUnsafe {
        private String isbn;

        public BookUnsafe(String isbn) {
            this.isbn = isbn;
        }

        @Override
        public boolean equals(Object o) {
            if (o instanceof BookUnsafe b) {
                return isbn.equals(b.isbn);
            }
            return false;
        }

        // hashCode отсутствует!
    }

    // Безопасная реализация: equals и hashCode
    static class BookSafe {
        private String isbn;

        public BookSafe(String isbn) {
            this.isbn = isbn;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof BookSafe b)) return false;
            return Objects.equals(isbn, b.isbn);
        }

        @Override
        public int hashCode() {
            return Objects.hash(isbn);
        }
    }

    public static void runUnsafe() {
        Set<BookUnsafe> set = new HashSet<>();
        BookUnsafe b1 = new BookUnsafe("978-1234567890");
        BookUnsafe b2 = new BookUnsafe("978-1234567890");

        set.add(b1);
        System.out.println("Unsafe contains b2? " + set.contains(b2)); // false: hashCode не определен
    }

    public static void runSafe() {
        Set<BookSafe> set = new HashSet<>();
        BookSafe b1 = new BookSafe("978-1234567890");
        BookSafe b2 = new BookSafe("978-1234567890");

        set.add(b1);
        System.out.println("Safe contains b2? " + set.contains(b2)); // true: hashCode определен
    }

    public static void main(String[] args) {
        System.out.println("Running unsafe version:");
        runUnsafe();

        System.out.println("\nRunning safe version:");
        runSafe();
    }
}

