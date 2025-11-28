public class Main {

    static class MyArraySizeException extends RuntimeException {
        MyArraySizeException(String msg) {
            super(msg);
        }
    }

    static class MyArrayDataException extends RuntimeException {
        MyArrayDataException(int row, int column) {
            super("ошибка значения в строке: " + row + ", столбце: " + column);
        }
    }

    public static int sumElements (String [][] array) {
        if (array.length != 4 || array[0].length != 4) {
            throw new MyArraySizeException("размер массива должен быть 4х4");
        }

        int totalSum = 0;

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                try {
                    totalSum += Integer.parseInt(array[i][j]);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException(i, j);
                }
            }
        }

        return totalSum;
    }

    public static void generateArrayIndexOutOfBounds() {
        int [] numbs = {1, 2, 3, 4, 5};
        try {
            System.out.println("Элемент массива: " + numbs[5]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Исключение: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        String [][] correctArray = {
                {"1", "2", "3", "4"},
                {"5", "6", "7", "8"},
                {"9", "10", "11", "12"},
                {"13", "14", "15", "16"}
        };
        try {
            System.out.println("Сумма элеметов первого массива: " + sumElements(correctArray));
        } catch (RuntimeException e) {
            System.out.println("Исключение: " + e.getMessage());
        }

        String [][] incorrectArray = {
                {"1", "2", "3"},
                {"4", "5", "6"},
                {"7", "8", "9"},
        };
        try {
            System.out.println("Сумма элеметов второго массива: " + sumElements(incorrectArray));
        } catch (RuntimeException e) {
            System.out.println("Исключение: " + e.getMessage());
        }

        String [][] textArray = {
                {"1", "2", "3", "4"},
                {"5", "6", "?", "8"},
                {"9", "10", "11", "12"},
                {"13", "14", "15", "16"}
        };
        try {
            System.out.println("Сумма элеметов третьего массива: " + sumElements(textArray));
        } catch (RuntimeException e) {
            System.out.println("Исключение: " + e.getMessage());
        }

        generateArrayIndexOutOfBounds();
    }
}