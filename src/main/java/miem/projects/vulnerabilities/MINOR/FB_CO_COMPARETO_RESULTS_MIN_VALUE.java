package miem.projects.vulnerabilities.MINOR.FB;

public class FB_CO_COMPARETO_RESULTS_MIN_VALUE {

    static class MyClassUnsafe implements Comparable<MyClassUnsafe> {
        int value;

        MyClassUnsafe(int value) {
            this.value = value;
        }

        // Потенциально небезопасное сравнение
        @Override
        public int compareTo(MyClassUnsafe o) {
            if (this.value < o.value) {
                return Integer.MIN_VALUE;
            }
            return 1;
        }
    }

    static class MyClassSafe implements Comparable<MyClassSafe> {
        int value;

        MyClassSafe(int value) {
            this.value = value;
        }

        // Корректная реализация compareTo
        @Override
        public int compareTo(MyClassSafe o) {
            return Integer.compare(this.value, o.value);
        }
    }

    public static void main(String[] args) {
        MyClassSafe a = new MyClassSafe(5);
        MyClassSafe b = new MyClassSafe(10);

        System.out.println(a.compareTo(b)); // -1
        System.out.println(b.compareTo(a)); // 1
    }
}
