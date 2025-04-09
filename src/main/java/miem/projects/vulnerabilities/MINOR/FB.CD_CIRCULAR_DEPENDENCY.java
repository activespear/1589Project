package miem.projects.vulnerabilities.MINOR.FB;

public class FB_CD_CIRCULAR_DEPENDENCY {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    // Некорректно: циклическая зависимость
    static class ClassA {
        private ClassB b;
        
        public ClassA() {
            this.b = new ClassB(this);
        }
    }

    static class ClassB {
        private ClassA a;
        
        public ClassB(ClassA a) {
            this.a = a;
        }
    }

    public static void incorrectTest() {
        ClassA a = new ClassA();
        System.out.println("Created circular dependency (A->B->A)");
    }

    // Корректно: устранение циклической зависимости через интерфейс
    interface ICommonFunctionality {
        void doSomething();
    }

    static class ProperClassA implements ICommonFunctionality {
        private ProperClassB b;
        
        public ProperClassA() {
            this.b = new ProperClassB();
        }
        
        @Override
        public void doSomething() {
            System.out.println("ClassA functionality");
        }
    }

    static class ProperClassB {
        private ICommonFunctionality a;
        
        public ProperClassB() {
            // Зависимость через интерфейс
        }
        
        public void setDependency(ICommonFunctionality a) {
            this.a = a;
        }
    }

    public static void correctTest() {
        ProperClassA a = new ProperClassA();
        ProperClassB b = new ProperClassB();
        b.setDependency(a);
        
        System.out.println("Created proper dependency structure");
    }
}