package miem.projects.vulnerabilities.MINOR.FB;  

import java.util.Arrays;  

public class FB_MS_OOI_PKGPROTECT {  
    public static void main(String[] args) {  
        incorrectTest();  
        correctTest();  
    }  

    // Класс с уязвимым изменяемым полем  
    public static class DataHolderIncorrect {  
        public static String[] sensitiveArray = {"secret1", "secret2"};  
    }  

    // Исправленный класс  
    static class DataHolderCorrect {  
        private static final String[] sensitiveArray = {"secret1", "secret2"};  

        // Защищённый доступ через копирование  
        public static String[] getSensitiveArray() {  
            return Arrays.copyOf(sensitiveArray, sensitiveArray.length);  
        }  
    }  

    public static void incorrectTest() {  
        // Вредоносный код может изменить массив напрямую  
        DataHolderIncorrect.sensitiveArray[0] = "hacked!";  
    }  

    public static void correctTest() {  
        // Получаем защищённую копию, оригинал остаётся неизменным  
        String[] copy = DataHolderCorrect.getSensitiveArray();  
        copy[0] = "attempted_hack"; // Не влияет на исходный массив  
    }  
}  