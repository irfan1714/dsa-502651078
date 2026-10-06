package lw03.unguided;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Set<String> registered = new HashSet<>();
        Set<String> checkedIn = new HashSet<>();
        int rejected = 0;

        Scanner sc = new Scanner(Main.class.getResourceAsStream("registrations.txt"));
        while (sc.hasNextLine()) {
            String id = sc.nextLine().trim();
            if (!id.equals("")) {
                registered.add(id);
            }
        }
        sc.close();

        System.out.println("===== Event Check-In Results =====");
        sc = new Scanner(Main.class.getResourceAsStream("checkins.txt"));
        while (sc.hasNextLine()) {
            String id = sc.nextLine().trim();
            if (id.equals("")) {
                continue;
            }

            if (!registered.contains(id)) {
                System.out.println(id + ": Rejected (not registered)");
                rejected++;
            } else if (checkedIn.contains(id)) {
                System.out.println(id + ": Rejected (already checked in)");
                rejected++;
            } else {
                checkedIn.add(id);
                System.out.println(id + ": Checked in");
            }
        }
        sc.close();

        System.out.println("===== Final Event Summary =====");
        System.out.println("Registered students: " + registered.size());
        System.out.println("Successful check-ins: " + checkedIn.size());
        System.out.println("Absent students: " + (registered.size() - checkedIn.size()));
        System.out.println("Rejected attempts: " + rejected);
    }
}