package miem.projects.vulnerabilities.MINOR.FB;

import java.util.Stack;

public class FB_UC_USELESS_OBJECT_STACK {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        // Некорректно: использование стека для одного элемента
        Stack<String> stack = new Stack<>();
        stack.push("single item");
        
        String item = stack.pop();
        System.out.println("Popped from unnecessary stack: " + item);
    }

    public static void correctTest() {
        // Корректно: прямое использование объекта
        String item = "direct item";
        System.out.println("Used directly: " + item);
        
        // Пример правильного использования стека
        Stack<String> properStack = new Stack<>();
        properStack.push("first");
        properStack.push("second");
        properStack.push("third");
        
        while (!properStack.isEmpty()) {
            System.out.println("Proper stack pop: " + properStack.pop());
        }
    }
}