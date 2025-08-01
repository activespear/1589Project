package miem.projects.vulnerabilities.MAJOR.FB;

import java.util.List;
import java.util.NoSuchElementException;

public class IT_NO_SUCH_ELEMENT {

    static class UnsafeIterator {
        private final List<String> list;
        private int index = 0;

        public UnsafeIterator(List<String> list) {
            this.list = list;
        }

        // Небезопасная версия next(): не проверяет hasNext()
        public String next() {
            return list.get(index++);  // Может выбросить IndexOutOfBoundsException
        }
    }

    static class SafeIterator {
        private final List<String> list;
        private int index = 0;

        public SafeIterator(List<String> list) {
            this.list = list;
        }

        public boolean hasNext() {
            return index < list.size();
        }

        // Безопасная версия next(): проверяет наличие следующего элемента
        public String next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return list.get(index++);
        }
    }

    public static void runUnsafe() {
        System.out.println("Unsafe Iterator:");
        List<String> data = List.of("a", "b");
        UnsafeIterator it = new UnsafeIterator(data);

        // Попытка взять 3 элемента, хотя в списке только 2
        try {
            System.out.println(it.next());
            System.out.println(it.next());
            System.out.println(it.next());  // Ошибка здесь
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Exception caught: " + e);
        }
    }

    public static void runSafe() {
        System.out.println("Safe Iterator:");
        List<String> data = List.of("a", "b");
        SafeIterator it = new SafeIterator(data);

        try {
            while (it.hasNext()) {
                System.out.println(it.next());
            }
            // Попытка вызвать next(), когда элементов нет — выброс NoSuchElementException
            System.out.println(it.next());
        } catch (NoSuchElementException e) {
            System.out.println("Exception caught: " + e);
        }
    }

    public static void main(String[] args) {
        runUnsafe();
        System.out.println();
        runSafe();
    }
}

