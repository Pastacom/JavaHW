import java.util.Arrays;
import java.util.Scanner;

public class Main {

    private static void Task2(int n) {
        if (n < 1) {
            throw new IllegalArgumentException("Invalid value for array size");
        }
        double[] numbers = new double[n];
        System.out.print("Generated array:\n");
        for (int i = 0; i < n; ++i) {
            numbers[i] = Math.random();
            System.out.printf("%.4f  ", numbers[i]);
        }
        System.out.printf("\nMaximum: %.4f\n", Arrays.stream(numbers).max().getAsDouble());
        System.out.printf("Average: %.4f\n", Arrays.stream(numbers).average().getAsDouble());
        System.out.printf("Minimum: %.4f\n", Arrays.stream(numbers).min().getAsDouble());
    }

    private static void Task3(int a, int b) {
        if (a < 2 || a > b) {
            throw new IllegalArgumentException("Invalid value for operation range");
        }
        System.out.printf("Prime numbers in range (%d, %d): ", a, b);
        if (a == 2) {
            System.out.printf("%d ", a);
        }
        if (a % 2 == 0) {
            ++a;
        }
        for (int i = a; i <= b; i += 2) {
            boolean flag = true;
            for (int j = 3; j < Math.sqrt(i) + 1; j += 2) {
                if (i % j == 0) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                System.out.printf("%d ", i);
            }
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the size of the array: ");
        Task2(scan.nextInt());
        System.out.print("Enter operation range: ");
        Task3(scan.nextInt(), scan.nextInt());
    }
}