import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.Scanner;
import java.io.InputStream;

public class Main {

    public static void main(String[] args) {

        // 1. Menyimpan semua transaksi
        LinkedList<String[]> transactions = new LinkedList<>();

        // 2. Menyimpan data nasabah
        LinkedList<String[]> customers = new LinkedList<>();

        // 3. Menyimpan transaksi yang gagal
        Stack<String[]> failedTransactions = new Stack<>();

        // 4. Membaca file transactions.txt
        InputStream inputStream =
                Main.class.getResourceAsStream("/transactions.txt");

        if (inputStream == null) {
            System.out.println("File transactions.txt tidak ditemukan.");
            return;
        }

        Scanner scanner = new Scanner(inputStream);

        while (scanner.hasNextLine()) {

            String line = scanner.nextLine().trim();

            if (line.isEmpty()) {
                continue;
            }

            String[] data = line.split("\\s+");

            String name = data[0];
            String type = data[1];
            String amount = data[2];

            // Menyimpan transaksi
            transactions.add(new String[]{name, type, amount});

            // Menambahkan nasabah jika belum ada
            boolean found = false;

            for (String[] customer : customers) {
                if (customer[0].equals(name)) {
                    found = true;
                    break;
                }
            }

            if (!found) {
                customers.add(new String[]{name, "0"});
            }
        }

        scanner.close();

        // 5. Memindahkan transaksi ke Queue
        Queue<String[]> queue = new LinkedList<>();
        queue.addAll(transactions);

        // 6. Memproses transaksi berdasarkan FIFO
        while (!queue.isEmpty()) {

            String[] transaction = queue.poll();

            String name = transaction[0];
            String type = transaction[1];
            int amount = Integer.parseInt(transaction[2]);

            // Mencari nasabah
            for (String[] customer : customers) {

                if (customer[0].equals(name)) {

                    int balance = Integer.parseInt(customer[1]);

                    if (type.equals("DEPOSIT")) {

                        // Menambah saldo
                        balance += amount;
                        customer[1] = String.valueOf(balance);

                    } else if (type.equals("WITHDRAW")) {

                        if (amount > balance) {

                            // Penarikan gagal
                            failedTransactions.push(transaction);

                        } else {

                            // Mengurangi saldo
                            balance -= amount;
                            customer[1] = String.valueOf(balance);
                        }
                    }

                    break;
                }
            }
        }

        // 7. Menampilkan saldo akhir
        System.out.println("=== Final Balances ===");

        for (String[] customer : customers) {
            System.out.println(customer[0] + " : " + customer[1]);
        }

        // 8. Menampilkan transaksi gagal dalam urutan LIFO
        System.out.println("=== Failed Transactions ===");

        while (!failedTransactions.isEmpty()) {

            String[] failed = failedTransactions.pop();

            System.out.println(
                    failed[0] + " " +
                    failed[1] + " " +
                    failed[2]
            );
        }
    }
}