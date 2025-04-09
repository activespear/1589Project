package miem.projects.vulnerabilities.MINOR.FB;

import java.util.Arrays;

public class EI_EXPOSE_REP2 {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    // Некорректный возврат ссылки на массив
    public static void incorrectTest() {
        SecureDataHolder holder = new SecureDataHolder(new int[]{1, 2, 3});
        int[] data = holder.getData(); // ОШИБКА: возврат ссылки на внутренний массив
        data[0] = 999; // Изменение внутреннего состояния объекта
        System.out.println("Modified data (INCORRECT): " + Arrays.toString(holder.getData()));
    }

    // Корректный способ защиты внутреннего массива
    public static void correctTest() {
        SecureDataHolderFixed holder = new SecureDataHolderFixed(new int[]{1, 2, 3});
        int[] data = holder.getData(); // Возвращает копию массива
        data[0] = 999; // Не влияет на внутренний массив
        System.out.println("Original data preserved (CORRECT): " + Arrays.toString(holder.getData()));
    }
}

// Некорректный класс (уязвимый)
class SecureDataHolder {
    private int[] data;

    public SecureDataHolder(int[] data) {
        this.data = data; // ОШИБКА: отсутствие защитной копии
    }

    public int[] getData() {
        return data; // ОШИБКА: возврат ссылки на массив
    }
}

// Исправленный класс (защищенный)
class SecureDataHolderFixed {
    private int[] data;

    public SecureDataHolderFixed(int[] data) {
        this.data = Arrays.copyOf(data, data.length); // Защитная копия при создании
    }

    public int[] getData() {
        return Arrays.copyOf(data, data.length); // Защитная копия при возврате
    }
}