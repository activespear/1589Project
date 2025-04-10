package miem.projects.vulnerabilities.MAJOR.FB;

public class DB_DUPLICATE_BRANCHES {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        class MyClass {
            String checkValue(int value) {
                if (value > 10) {
                    return "Yes";
                } else {
                    return "Yes"; // Дублирующийся код
                }
            }
        }

        MyClass myClass = new MyClass();
        System.out.println(myClass.checkValue(15));
        System.out.println(myClass.checkValue(5));
    }

    public static void correctTest() {
        class MyClass {
            String checkValue(int value) {
                // Убрана ненужная ветка
                return "Yes";
            }
        }

        MyClass myClass = new MyClass();
        System.out.println(myClass.checkValue(15));
        System.out.println(myClass.checkValue(5));
    }
}