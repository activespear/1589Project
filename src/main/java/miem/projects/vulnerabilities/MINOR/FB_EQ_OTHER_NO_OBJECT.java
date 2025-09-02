package miem.projects.vulnerabilities.MINOR.FB;

import java.util.Objects;

public class FB_EQ_OTHER_NO_OBJECT {

    static class MyClassUnsafe {
        private int id;

        public MyClassUnsafe(int id) {
            this.id = id;
        }

        public boolean Equals(MyClassUnsafe other) {
            return this.id == other.id;
        }
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
        MyClassUnsafe a1 = new MyClassUnsafe(1);
        MyClassUnsafe a2 = new MyClassUnsafe(1);

        MyClassSafe b1 = new MyClassSafe(1);
        MyClassSafe b2 = new MyClassSafe(1);

        System.out.println("Unsafe Equals: " + a1.Equals(a2)); // работает, но не переопределяет Object.equals()
        System.out.println("Safe equals: " + b1.equals(b2));   // корректно
    }
}
