package miem.projects.vulnerabilities.MAJOR.FB;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

public class STCAL_STATIC_SIMPLE_DATE_FORMAT_INSTANCE {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        class Test {
            private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            // ...
        }

        new Test();
    }

    public static void correctTest() {
        class Test {
            private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
             // ...
        }
        new Test();
    }
}
