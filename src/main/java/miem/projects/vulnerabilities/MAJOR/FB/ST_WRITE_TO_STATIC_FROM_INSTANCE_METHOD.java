package miem.projects.vulnerabilities.MAJOR.FB;

public class ST_WRITE_TO_STATIC_FROM_INSTANCE_METHOD {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        MyClass myObject = new MyClass();
        myObject.modifyStaticField();
    }

    public static void correctTest() {
        MyClass.modifyStaticFieldStatically();
    }

    static class MyClass {
        private static int staticField = 0;

        public void modifyStaticField() {
            staticField = 10;
            System.out.println("Статическое поле изменено в экземпляре: " + staticField);
        }

        public static void modifyStaticFieldStatically() {
            staticField = 10;
            System.out.println("Статическое поле изменено через статический метод: " + staticField);
        }
    }
}