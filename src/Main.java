import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Set<Student> students = new HashSet<>();
        students.add(new Student("Алексей", "ИНФ-1", 1, 3.5));
        students.add(new Student("Марина", "ИНФ-1", 2, 2.5));
        students.add(new Student("Павел", "ИНФ-2", 3, 4.0));

        System.out.println("Список студентов: ");
        printStudents(students);
        removeLowMark(students);
        studentsNextCourse(students);

        System.out.println("\nНовый список студентов:");
        printStudents(students);

        System.out.println("\nСтуденты на втором курсе:");
        printStudents(students, 2);

        PhoneBook phoneBook = new PhoneBook();
        phoneBook.add("Иванов", "89001234567");
        phoneBook.add("Иванов", "89001234568"); // Ещё один номер для Ивана
        phoneBook.add("Иванова", "89009876543");
        phoneBook.add("Петров", "89001112233");

        System.out.println("\nВесь телефонный справочник:");
        phoneBook.showDirectory();

        System.out.println("\nНомера по запросу \"Иванов\":");
        System.out.println(phoneBook.get("Иванов"));
    }

    public static void printStudents(Set<Student> students, int course) {
        boolean hasStudentsOnCourse = false;
        for (Student student : students) {
            if (student.getCourse() == course) {
                System.out.println("Студент: " + student.getName() + ", группа: " + student.getGroup());
                hasStudentsOnCourse = true;
            }
        }
        if (!hasStudentsOnCourse) {
            System.out.println("На курсе " + course + " нет студентов.");
        }
    }

    public static void removeLowMark(Set<Student> students) {
        students.removeIf(student -> student.getAverageMark() < 3);
    }

    public static void  studentsNextCourse(Set<Student> students) {
        for (Student student : students) {
            if (student.getAverageMark() >= 3) {
                student.nextCourse();
            }
        }
    }

    public static void printStudents(Set<Student> students) {
        for (Student student : students) {
            System.out.println(student);
        }
    }
}