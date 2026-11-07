package lw04.prelab;

import java.util.Scanner;

public class Main {

    static void insertionSortPasses(int[] a, int passes) {
        for (int i = 1; i <= passes && i < a.length; i++) {
            int key = a[i];
            int j = i - 1;
            while (j >= 0 && a[j] < key) {
                a[j + 1] = a[j];
                j--;
            }
            a[j + 1] = key;
        }
    }

    static int linearSearch(int[] a, int target) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] == target) return i;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] branches;
        int[] targets;

        try (Scanner sc = new Scanner(Main.class.getResourceAsStream("branches.txt"))) {
            int n = sc.nextInt();
            branches = new int[n];
            for (int i = 0; i < n; i++) branches[i] = sc.nextInt();

            int m = sc.nextInt();
            targets = new int[m];
            for (int i = 0; i < m; i++) targets[i] = sc.nextInt();
        }

        insertionSortPasses(branches, 3);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < branches.length; i++) {
            if (i > 0) sb.append(" ");
            sb.append(branches[i]);
        }
        System.out.println(sb);

        for (int t : targets) {
            int idx = linearSearch(branches, t);
            if (idx >= 0) System.out.println(t + ": FOUND at index " + idx);
            else System.out.println(t + ": NOT FOUND");
        }
    }
}