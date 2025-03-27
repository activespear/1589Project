package miem.projects.vulnerabilities.MAJOR.FB;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class STCAL_INVOKE_ON_STATIC_DATE_FORMAT_INSTANCE {
    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        String date = dateFormat.format(new Date());
        System.out.println(date);
    }

    public static void correctTest() {
        String date = LocalDate.now().format(formatter);
        System.out.println(date);
    }
}
