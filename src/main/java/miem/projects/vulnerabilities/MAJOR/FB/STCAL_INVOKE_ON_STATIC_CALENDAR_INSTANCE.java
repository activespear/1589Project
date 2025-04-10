package miem.projects.vulnerabilities.MAJOR.FB;

import java.time.LocalDate;
import java.util.Calendar;

public class STCAL_INVOKE_ON_STATIC_CALENDAR_INSTANCE {
    private static final Calendar calendar = Calendar.getInstance();

    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        // Потенциальная проблема
        int year = calendar.get(Calendar.YEAR);
        System.out.println(year);
    }

    public static void correctTest() {
        int year = LocalDate.now().getYear();
        System.out.println(year);
    }
}
