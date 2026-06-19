import java.util.*;

public class Main {

    static int[] BIT;
    static int n;

    static void update(int index, int value) {
        while (index <= n) {
            BIT[index] += value;
            index += index & (-index);
        }
    }

    static int prefixSum(int index) {
        int sum = 0;
        while (index > 0) {
            sum += BIT[index];
            index -= index & (-index);
        }
        return sum;
    }

    static int rangeSum(int left, int right) {
        return prefixSum(right) - prefixSum(left - 1);
    }

    public static void main(String[] args) {

        int[] registrations = {150, 200, 175, 250, 300};

        n = registrations.length;
        BIT = new int[n + 1];

        for (int i = 0; i < n; i++) {
            update(i + 1, registrations[i]);
        }

        System.out.println("Event Registrations:");
        for (int i = 0; i < n; i++) {
            System.out.println("Event " + (1001 + i) +
                    " -> " + registrations[i]);
        }

        System.out.println();

        int rangeResult = rangeSum(2, 4);

        System.out.println("Range Query:");
        System.out.println("Total Registrations from Event 1002 to Event 1004");
        System.out.println("Result = " + rangeResult);

        System.out.println();

        int prefixResult = prefixSum(4);

        System.out.println("Prefix Sum Query:");
        System.out.println("Registrations up to Event 1004");
        System.out.println("Result = " + prefixResult);
    }
}