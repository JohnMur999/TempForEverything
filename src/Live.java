import java.util.Arrays;
import java.util.Optional;
import java.util.Random;

public class Live {
    public static int[] generateSortedArray(int size) {
        Random random = new Random();
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(100);
        }
        Arrays.sort(array);
        return array;
    }

    public static Optional<Integer> getIndexWithoutUsingBinarySearchMethod(int[] array, int number) {
        System.out.println("Using getIndexWithoutUsingBinarySearchMethod:");

        int left = 0;
        int right = array.length - 1;

        // Условие - массив отсортирован
        while (left <= right) {
            int mid = (left + right) / 2;
            if (number == array[mid]) {
                return Optional.of(mid);
            } else if (number < array[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return Optional.empty();
    }

    public static Optional<Integer> getIndexWithoutUsingBinarySearchMethodWithRecursive(int[] array, int number, int left, int right) {

        if (left > right) {
            return Optional.empty();
        }

        int mid = (left + right) / 2;

        if (number == array[mid]) {
            return Optional.of(mid);
        } else if (number < array[mid]) {
            return getIndexWithoutUsingBinarySearchMethodWithRecursive(array, number, left, mid - 1);
        } else {
            return getIndexWithoutUsingBinarySearchMethodWithRecursive(array, number, mid + 1, right);
        }
    }

    public static Optional<Integer> getIndexWithBinarySearch(int[] array, int number) {
        System.out.println("Using getIndexWithBinarySearch:");

        int index = Arrays.binarySearch(array, number);

        if (index >= 0) {
            return Optional.of(array[index]);
        } else {
            return Optional.empty();
        }
    }

    public static void main(String[] args) {
        int num = 15;
        int arraySize = 100; // 0<=x<=99

        Optional<Integer> foundNumber = getIndexWithoutUsingBinarySearchMethod(generateSortedArray(arraySize), num);
        if (foundNumber.isPresent()) {
            System.out.println("Index of " + num + " = " + foundNumber.get());
        } else {
            System.out.println("Index of " + num + " " + "not found");
        }

        int num2 = 81;

        Optional<Integer> foundNumber2 = getIndexWithBinarySearch(generateSortedArray(arraySize), num2);
        if (foundNumber2.isPresent()) {
            System.out.println("Index of " + num2 + " = " + foundNumber2.get());
        } else {
            System.out.println("Index of " + num2 + " " + "not found");
        }

        int num3 = 32;

        Optional<Integer> foundNumber3 = getIndexWithoutUsingBinarySearchMethodWithRecursive(
                generateSortedArray(arraySize),
                num3,
                0,
                arraySize - 1);

        if (foundNumber3.isPresent()) {
            System.out.println("Index of " + num3 + " = " + foundNumber3.get());
        } else {
            System.out.println("Index of " + num3 + " " + "not found");
        }
    }
}
