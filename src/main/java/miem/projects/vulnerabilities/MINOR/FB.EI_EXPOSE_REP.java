package miem.projects.vulnerabilities.MINOR.FB;

import java.util.Date;

public class EI_EXPOSE_REP {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    // Некорректный возврат изменяемого объекта
    public static void incorrectTest() {
        EventScheduler scheduler = new EventScheduler(new Date());
        Date scheduleDate = scheduler.getScheduleDate(); // ОШИБКА: возврат ссылки на изменяемый объект
        scheduleDate.setTime(0); // Нежелательное изменение состояния объекта
        System.out.println("Modified date: " + scheduler.getScheduleDate());
    }

    // Корректный способ защиты внутреннего состояния
    public static void correctTest() {
        EventSchedulerFixed scheduler = new EventSchedulerFixed(new Date());
        Date scheduleDate = scheduler.getScheduleDate(); // Возвращает копию
        scheduleDate.setTime(0); // Не влияет на внутреннее состояние
        System.out.println("Original date preserved: " + scheduler.getScheduleDate());
    }
}

// Некорректный класс (уязвимый)
class EventScheduler {
    private Date scheduleDate;

    public EventScheduler(Date date) {
        this.scheduleDate = date;
    }

    public Date getScheduleDate() {
        return scheduleDate; // ОШИБКА: прямое возвращение изменяемого объекта
    }
}

// Исправленный класс (защищенный)
class EventSchedulerFixed {
    private Date scheduleDate;

    public EventSchedulerFixed(Date date) {
        this.scheduleDate = new Date(date.getTime()); // Защитная копия при создании
    }

    public Date getScheduleDate() {
        return new Date(scheduleDate.getTime()); // Защитная копия при возврате
    }
}