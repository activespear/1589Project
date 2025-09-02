package miem.projects.vulnerabilities.MINOR.FB;

import javax.swing.*;

public class FB_SW_SWING_METHODS_INVOKED_IN_SWING_THREAD {

    // Потенциально небезопасное использование Swing без потока событий
    static class ExampleUnsafe {
        public void createUI() {
            JFrame frame = new JFrame("Unsafe Swing Example");
            frame.setSize(400, 300);
            frame.setVisible(true); //  Вызывается напрямую в основном потоке, что небезопасно
        }
    }

    // Корректная конструкция
    static class ExampleSafe {
        public void createUI() {
            JFrame frame = new JFrame("Safe Swing Example");
            frame.setSize(400, 300);

            //  Методы Swing вызываются через поток событий
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    frame.setVisible(true);
                }
            });
        }
    }

    public static void main(String[] args) {
        ExampleSafe example = new ExampleSafe();
        example.createUI();
        System.out.println("Swing UI created safely using Event Dispatch Thread");
    }
}
