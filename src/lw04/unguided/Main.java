
import java.util.Scanner;
import java.io.InputStream;

public class Main {

    public static void displayArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);

            if (i < arr.length - 1) {
                System.out.print(" ");
            }
        }
        System.out.println();
    }

    public static void bubbleSortPass(int[] arr, int pass) {
        for (int j = 0; j < arr.length - 1 - pass; j++) {

            if (arr[j] < arr[j + 1]) {
                int temp = arr[j];
                arr[j] = arr[j + 1];
                arr[j + 1] = temp;
            }
        }
    }

    public static int[] binarySearch(int[] arr, int target) {
        int low = 0;
        int high = arr.length - 1;
        int steps = 0;

        while (low <= high) {
            int mid = (low + high) / 2;
            steps++;

            if (arr[mid] == target) {
                return new int[]{mid, steps};
            } 
            else if (target < arr[mid]) {
                low = mid + 1;
            } 
            else {
                high = mid - 1;
            }
        }

        return new int[]{-1, steps};
    }

    public static void main(String[] args) {

        InputStream input = Main.class.getResourceAsStream("parcels.txt");

        if (input == null) {
            System.out.println("File parcels.txt tidak ditemukan.");
            return;
        }

        try (Scanner sc = new Scanner(input)) {

            int N = sc.nextInt();

            int[] parcels = new int[N];

            for (int i = 0; i < N; i++) {
                parcels[i] = sc.nextInt();
            }
            int M = sc.nextInt();

            int[] targets = new int[M];

            for (int i = 0; i < M; i++) {
                targets[i] = sc.nextInt();
            }
            int[] afterTwoPasses = new int[N];

            for (int i = 0; i < N - 1; i++) {

                bubbleSortPass(parcels, i);

                if (i == 1) {
                    for (int j = 0; j < N; j++) {
                        afterTwoPasses[j] = parcels[j];
                    }
                }
            }

            System.out.println("==== After 2 Passes ====");
            displayArray(afterTwoPasses);

            System.out.println();
            System.out.println("==== Fully Sorted ====");
            displayArray(parcels);

            System.out.println();
            System.out.println("==== Search Results ====");

            for (int i = 0; i < M; i++) {

                int[] result = binarySearch(parcels, targets[i]);

                int index = result[0];
                int steps = result[1];

                if (index != -1) {
                    System.out.println(
                        targets[i] + ": FOUND at index "
                        + index + " (steps: " + steps + ")"
                    );
                } 
                else {
                    System.out.println(
                        targets[i] + ": NOT FOUND (steps: "
                        + steps + ")"
                    );
                }
            }

        } 
        catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
