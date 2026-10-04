package lw2.prelab;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        LinkedList<String[]> orders = new LinkedList<>();
        LinkedList<String[]> foods = new LinkedList<>();
        LinkedList<String[]> drinks = new LinkedList<>();
        LinkedList<String[]> successfulOrders = new LinkedList<>();

        Queue<String[]> queue = new LinkedList<>();
        Stack<String[]> failedOrders = new Stack<>();

        Scanner scanner = new Scanner(
            Main.class.getResourceAsStream("orders.txt")
        );

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();

            if (line.isEmpty()) {
                continue;
            }

            String[] order = line.split("\\s+");

            if (order.length == 4) {
                orders.add(order);
            }
        }

        scanner.close();

        foods.add(new String[]{"Bakso", "2"});
        foods.add(new String[]{"Sate", "1"});
        foods.add(new String[]{"Soto", "2"});

        drinks.add(new String[]{"EsTeh", "4"});
        drinks.add(new String[]{"EsJeruk", "2"});

        queue.addAll(orders);

        while (!queue.isEmpty()) {

            String[] order = queue.poll();

            String foodName = order[1];
            String drinkName = order[2];

            String[] food = null;
            String[] drink = null;

            if (!foodName.equals("-")) {
                for (String[] data : foods) {
                    if (data[0].equals(foodName)) {
                        food = data;
                        break;
                    }
                }
            }

            if (!drinkName.equals("-")) {
                for (String[] data : drinks) {
                    if (data[0].equals(drinkName)) {
                        drink = data;
                        break;
                    }
                }
            }

            boolean foodAvailable = foodName.equals("-")
                    || (food != null
                    && Integer.parseInt(food[1]) > 0);

            boolean drinkAvailable = drinkName.equals("-")
                    || (drink != null
                    && Integer.parseInt(drink[1]) > 0);

            if (foodAvailable && drinkAvailable) {

                if (!foodName.equals("-")) {
                    int stock = Integer.parseInt(food[1]);
                    food[1] = String.valueOf(stock - 1);
                }

                if (!drinkName.equals("-")) {
                    int stock = Integer.parseInt(drink[1]);
                    drink[1] = String.valueOf(stock - 1);
                }

                successfulOrders.add(order);

            } else {
                failedOrders.push(order);
            }
        }

        System.out.println("=== Sucessfully Processed Ordered ===");

        for (String[] order : successfulOrders) {
            System.out.println(
                    order[0] + " " +
                    order[1] + " " +
                    order[2] + " " +
                    order[3]
            );
        }

        System.out.println("=== Remaining Food Stock ===")

        for (String[] food : foods) {
            System.out.println(food[0] + " : " + food[1]);
        }

        System.out.println("=== Remaining Drink Stock ===")

        for (String[] drink : drinks) {
            System.out.println(drink[0] + " : " + drink[1]);
        }

        System.out.println("=== Failed Orders ===");

        while (!failedOrders.isEmpty()) {
            String[] order = failedOrders.pop();

            System.out.println(
                    order[0] + " " +
                    order[1] + " " +
                    order[2] + " " +
                    order[3]
            );
        }
    }
}