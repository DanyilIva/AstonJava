import java.util.Arrays;//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        System.out.println("Задание 1");
        Lesson2.printThreeWords();

        System.out.println("\nЗадание 2");
        Lesson2.checkSumSign();

        System.out.println("\nЗадание 3");
        Lesson2.printColor();

        System.out.println("\nЗадание 4");
        Lesson2.compareNumbers();

        System.out.println("\nЗадание 5");
        System.out.println(Lesson2.range(9, 1));
        System.out.println(Lesson2.range(8, 1));

        System.out.println("\nЗадание 6");
        Lesson2.positiveNegative(15);
        Lesson2.positiveNegative(-15);

        System.out.println("\nЗадание 7");
        System.out.println(Lesson2.boolPositiveNegative(3));
        System.out.println(Lesson2.boolPositiveNegative(-3));

        System.out.println("\nЗадание 8");
        Lesson2.strCount("Java", 5);

        System.out.println("\nЗадание 9");
        System.out.println(Lesson2.visYear(2024));
        System.out.println(Lesson2.visYear(2025));

        System.out.println("\nЗадание 10");
        int [] mas = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0};
        Lesson2.zeroOneArray(mas);
        System.out.println(Arrays.toString(mas));

        System.out.println("\nЗадание 11");
        int [] mass = new int [100];
        Lesson2.hundArray(mass);
        System.out.println(Arrays.toString(mass));

        System.out.println("\nЗадание 12");
        int [] massive = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        Lesson2.sixArray(massive);
        System.out.println(Arrays.toString(massive));

        System.out.println("\nЗадание 13");
        int size = 8;
        int [][] matrix = new int [size][size];
        Lesson2.matrDiag(matrix);
        for (int [] row : matrix) {
            System.out.println(Arrays.toString(row));
        }

        System.out.println("\nЗадание 14");
        int [] myArray = Lesson2.oneArray(5, 10);
        System.out.println(Arrays.toString(myArray));

    }
}