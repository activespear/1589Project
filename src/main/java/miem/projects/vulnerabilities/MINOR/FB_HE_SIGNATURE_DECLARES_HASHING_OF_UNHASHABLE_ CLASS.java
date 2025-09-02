package miem.projects.vulnerabilities.MINOR.FB;

import java.util.Objects;

public class FB_HE_SIGNATURE_DECLARES_HASHING_OF_UNHASHABLE_CLASS {

    static class MyClassUnsafe {
        private int id;

        public MyClassUnsafe(int id) {
            this.id = id;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            MyClassUnsafe myClass = (MyClassUnsafe) obj;
            return id == myClass.id;
        }

        // hashCode отсутствует -> нарушает контракт equals/hashCode
    }

    static class MyClassSafe {
        private int id;

        public MyClassSafe(int id) {
            this.id = id;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            MyClassSafe myClass = (MyClassSafe) obj;
            return id == myClass.id;
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }
    }

    public static void main(String[] args) {
        MyClassUnsafe u1 = new MyClassUnsafe(1);
        MyClassUnsafe u2 = new MyClassUnsafe(1);

        MyClassSafe s1 = new MyClassSafe(1);
        MyClassSafe s2 = new MyClassSafe(1);

        System.out.println("Unsafe equals: " + u1.equals(u2));
        System.out.println("Unsafe hashCodes: " + u1.hashCode() + " vs " + u2.hashCode()); // может различаться

        System.out.println("Safe equals: " + s1.equals(s2));
        System.out.println("Safe hashCodes: " + s1.hashCode() + " vs " + s2.hashCode());   // одинаковые
    }
}
