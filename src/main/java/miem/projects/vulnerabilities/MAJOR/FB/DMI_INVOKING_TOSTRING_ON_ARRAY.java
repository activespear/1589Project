package miem.projects.vulnerabilities.MAJOR.FB;

import java.util.Arrays;

public class DMI_INVOKING_TOSTRING_ON_ARRAY {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        int[] arr = new int[10];
        String s = arr.toString();
    }

    public static void correctTest() {
        int[] arr = new int[10];
        String s = Arrays.toString(arr);
    }
}