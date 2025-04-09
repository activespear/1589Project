package miem.projects.vulnerabilities.MINOR.FB;

import java.applet.Applet;

public class FB_BAC_BAD_APPLET_CONSTRUCTOR {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        // Некорректно: опасный конструктор апплета
        class DangerousApplet extends Applet {
            public DangerousApplet() {
                // Опасные операции в конструкторе
                System.setSecurityManager(null);  // Отключение security manager
            }
        }
        
        System.out.println("Created dangerous applet (INSECURE)");
    }

    public static void correctTest() {
        // Корректно: безопасная инициализация апплета
        class SafeApplet extends Applet {
            public SafeApplet() {
                // Безопасная инициализация
            }
            
            @Override
            public void init() {
                // Операции инициализации в методе init()
                System.out.println("Applet initialized safely");
            }
        }
        
        System.out.println("Created safe applet");
    }
}