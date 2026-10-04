package lw03.prelab;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Main {

    private static Scanner openFile(String name) {
        InputStream is = Main.class.getResourceAsStream(name);
        if (is == null) {
            throw new RuntimeException("File tidak ditemukan: " + name);
        }
        return new Scanner(is);
    }

    private static void problem1() {
        List<String> playlist = new ArrayList<>();
        Scanner sc = openFile("playlist.txt");

        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            if (line.isEmpty()) continue;

            String[] parts = line.split(" ", 3);
            String type = parts[0];

            if (type.equals("ADD")) {
                String song = line.substring(4).trim();
                playlist.add(song);
            } else if (type.equals("INSERT")) {
                int index = Integer.parseInt(parts[1]);
                String song = parts[2];
                playlist.add(index, song);
            } else if (type.equals("REMOVE")) {
                String song = line.substring(7).trim();
                playlist.remove(song);
            }
        }
        sc.close();

        System.out.println("===== Problem 1 =====");
        System.out.println("Total songs: " + playlist.size());
        for (int i = 0; i < playlist.size(); i++) {
            System.out.println((i + 1) + ": " + playlist.get(i));
        }
    }

    private static void problem2() {
        Set<String> participants = new LinkedHashSet<>();
        int duplicates = 0;
        Scanner sc = openFile("participants.txt");

        while (sc.hasNextLine()) {
            String name = sc.nextLine().trim();
            if (name.isEmpty()) continue;

            if (!participants.add(name)) {
                duplicates++;
            }
        }
        sc.close();

        System.out.println("===== Problem 2 =====");
        System.out.println("Unique participants: " + participants.size());
        int no = 1;
        for (String name : participants) {
            System.out.println(no + ". " + name);
            no++;
        }
        System.out.println("Duplicate registrations: " + duplicates);
    }

    private static void problem3() {
        Map<String, Integer> stock = new LinkedHashMap<>();
        int failedSales = 0;
        Scanner sc = openFile("inventory.txt");

        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            if (line.isEmpty()) continue;

            String[] parts = line.split(" ");
            String type = parts[0];
            String product = parts[1];
            int qty = Integer.parseInt(parts[2]);

            if (type.equals("ADD")) {
                if (stock.containsKey(product)) {
                    stock.put(product, stock.get(product) + qty);
                } else {
                    stock.put(product, qty);
                }
            } else if (type.equals("SELL")) {
                if (stock.containsKey(product) && stock.get(product) >= qty) {
                    stock.put(product, stock.get(product) - qty);
                } else {
                    failedSales++;
                }
            }
        }
        sc.close();

        System.out.println("===== Problem 3 =====");
        for (Map.Entry<String, Integer> e : stock.entrySet()) {
            System.out.println(e.getKey() + ": " + e.getValue());
        }
        System.out.println("Failed sales: " + failedSales);
    }

    public static void main(String[] args) {
        problem1();
        problem2();
        problem3();
    }
}