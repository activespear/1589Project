package miem.projects.vulnerabilities.MINOR.FB;

public class FB_DC_PARTIALLY_CONSTRUCTED {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        class ResourceHolder {
            private final Resource resource;
            
            // Некорректно: "побег" this из конструктора
            public ResourceHolder() {
                this.resource = new Resource();
                register(this);  // Опасная публикация
            }
            
            public Resource getResource() {
                return resource;
            }
        }
        
        class Resource {}
        
        // Может получить частично инициализированный объект
        new ResourceHolder();
    }

    public static void correctTest() {
        class SafeResourceHolder {
            private final Resource resource;
            
            // Корректно: полная инициализация перед публикацией
            public SafeResourceHolder() {
                this.resource = new Resource();
            }
            
            // Регистрация после полного создания объекта
            public SafeResourceHolder register() {
                Registry.register(this);
                return this;
            }
            
            public Resource getResource() {
                return resource;
            }
        }
        
        class Resource {}
        
        // Безопасное создание и регистрация
        SafeResourceHolder holder = new SafeResourceHolder().register();
    }
}

// Вспомогательный класс для демонстрации
class Registry {
    static void register(Object obj) {
        System.out.println("Object registered");
    }
}