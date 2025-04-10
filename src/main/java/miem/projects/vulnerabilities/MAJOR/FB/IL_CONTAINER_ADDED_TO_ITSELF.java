package miem.projects.vulnerabilities.MAJOR.FB;

import java.util.HashSet;
import java.util.Set;

public class IL_CONTAINER_ADDED_TO_ITSELF {

    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        Set<Set<?>> set = new HashSet<>();
        set.add(set);
    }

    public static void correctTest() {
        Set<Set<?>> set1 = new HashSet<>();
        Set<Set<?>> set2 = new HashSet<>();
        set2.addAll(set1);
    }
}