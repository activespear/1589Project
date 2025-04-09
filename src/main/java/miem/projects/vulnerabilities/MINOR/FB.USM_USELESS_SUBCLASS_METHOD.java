package miem.projects.vulnerabilities.MINOR.FB;

public class FB_USM_USELESS_SUBCLASS_METHOD {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        class BaseOperation {
            public void execute() {
                System.out.println("Base operation executed");
            }
        }
        
        // Некорректно: бесполезное переопределение
        class SpecificOperation extends BaseOperation {
            @Override
            public void execute() {
                // Пустая реализация (нарушает LSP)
            }
        }
        
        new SpecificOperation().execute();  // Ничего не делает
    }

    public static void correctTest() {
        abstract class Operation {
            public abstract void execute();
        }
        
        // Корректно: осмысленное переопределение
        class ConcreteOperation extends Operation {
            @Override
            public void execute() {
                System.out.println("Concrete operation executed");
            }
        }
        
        // Альтернатива: композиция вместо наследования
        class OperationDelegate {
            private final Runnable operation;
            
            public OperationDelegate(Runnable operation) {
                this.operation = operation;
            }
            
            public void execute() {
                operation.run();
            }
        }
        
        new ConcreteOperation().execute();
        new OperationDelegate(() -> System.out.println("Delegated operation")).execute();
    }
}