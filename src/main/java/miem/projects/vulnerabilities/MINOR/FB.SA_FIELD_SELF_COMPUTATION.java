package miem.projects.vulnerabilities.MINOR.FB;

public class SA_FIELD_SELF_COMPUTATION {
    private int counter;
    private boolean status;
    
    public static void main(String[] args) {
        SA_FIELD_SELF_COMPUTATION example = new SA_FIELD_SELF_COMPUTATION();
        example.incorrectOperations();
        example.correctOperations();
    }
    
    // Некорректные операции
    public void incorrectOperations() {
        counter = counter + counter;  // FB.SA_FIELD_SELF_COMPUTATION
        status = status || status;    // FB.SA_FIELD_SELF_COMPUTATION
        int useless = counter & counter;  // FB.SA_FIELD_SELF_COMPUTATION
    }
    
    // Корректные альтернативы
    public void correctOperations() {
        // Удвоение значения
        counter *= 2;
        
        // Установка флага
        status = true;
        
        // Реальная битовая операция
        int mask = 0xFF;
        int result = counter & mask;
    }
}