package miem.projects.vulnerabilities.MINOR.FB;

import java.util.Arrays;

public class FB_EC_INCOMPATIBLE_ARRAY_COMPARE {

    static class ExampleUnsafe {
        public void test() {
            String[] arr1 = {"a", "b"};
            StringBuffer[] arr2 = {new StringBuffer("a"), new StringBuffer("b")};

            System.out.println("Unsafe compare: " + arr1.equals(arr2));
        }
    }

    static class ExampleSafe {
        public void test() {
            String[] arr1 = {"a", "b"};
            String[] arr2 = {"a", "b"};

            System.out.println("Safe compare: " + Arrays.equals(arr1, arr2));
        }
    }

    public static void main(String[] args) {
        new ExampleUnsafe().test();
        new ExampleSafe().test();
    }
}
