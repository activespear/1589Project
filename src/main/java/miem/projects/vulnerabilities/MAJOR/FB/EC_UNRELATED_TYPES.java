package miem.projects.vulnerabilities.MAJOR.FB;

public class EC_UNRELATED_TYPES {

    // Небезопасная конструкция: equals всегда true в A и всегда false в B
    static class AUnsafe {
        @Override
        public boolean equals(Object obj) {
            return true;
        }
    }

    static class BUnsafe {
        @Override
        public boolean equals(Object obj) {
            return false;
        }
    }

    public static void unsafeEquals() {
        AUnsafe a = new AUnsafe();
        BUnsafe b = new BUnsafe();

        boolean result = a.equals(b);
        boolean reverse = b.equals(a);

        System.out.println("Unsafe: a.equals(b) = " + result);
        System.out.println("Unsafe: b.equals(a) = " + reverse);
    }

    // Безопасная конструкция: equals проверяет класс объекта
    static class ASafe {
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            return true;
        }
    }

    static class BSafe {
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            return true;
        }
    }

    public static void safeEquals() {
        ASafe a = new ASafe();
        BSafe b = new BSafe();

        boolean result = a.equals(b);
        boolean reverse = b.equals(a);

        System.out.println("Safe: a.equals(b) = " + result);
        System.out.println("Safe: b.equals(a) = " + reverse);
    }

    public static void main(String[] args) {
        unsafeEquals();
        safeEquals();
    }
}

