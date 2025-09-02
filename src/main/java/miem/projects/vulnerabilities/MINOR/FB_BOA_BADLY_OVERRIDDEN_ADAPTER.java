package miem.projects.vulnerabilities.MINOR.FB;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FB_BOA_BADLY_OVERRIDDEN_ADAPTER {

    // Потенциально небезопасное переопределение методов адаптера
    static class ExampleUnsafe extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            System.out.println("Mouse clicked (unsafe)");
        }

        // Другие методы MouseAdapter не переопределены или реализованы некорректно
    }

    // Корректная конструкция
    static class ExampleSafe extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            System.out.println("Mouse clicked (safe)");
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            System.out.println("Mouse entered");
        }
    }

    public static void main(String[] args) {
        ExampleSafe listener = new ExampleSafe();
        listener.mouseClicked(new MouseEvent(new java.awt.Button(), 0, 0, 0, 0, 0, 1, false));
        listener.mouseEntered(new MouseEvent(new java.awt.Button(), 0, 0, 0, 0, 0, 1, false));
    }
}
