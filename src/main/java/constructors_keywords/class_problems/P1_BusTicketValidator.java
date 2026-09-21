package constructors_keywords.class_problems;

import java.util.HashSet;
import java.util.Set;

public class P1_BusTicketValidator {

    public static class BusTicket {
        private String passengerName;
        private String destination;
        private boolean isCheckedIn;

        // Requirement: No default/no-argument constructor
        public BusTicket(String passengerName, String destination) {
            // Validation at construction time
            if (passengerName == null || passengerName.trim().isEmpty() || !isValidName(passengerName)) {
                throw new IllegalArgumentException("Invalid passenger name: " + passengerName);
            }
            if (destination == null || destination.trim().isEmpty()) {
                throw new IllegalArgumentException("Invalid destination: " + destination);
            }
            this.passengerName = passengerName.trim();
            this.destination = destination.trim();
            this.isCheckedIn = false;
        }

        private static boolean isValidName(String name) {
            // Valid names contain letters and spaces only
            return name.trim().matches("^[a-zA-Z\\s]+$");
        }

        public void markCheckedIn() {
            if (this.isCheckedIn) {
                System.out.println("Ticket already checked in.");
                return;
            }
            this.isCheckedIn = true;
        }

        public String getPassengerName() {
            return passengerName;
        }

        public String getDestination() {
            return destination;
        }
    }

    public static void processBatch(String[][] rawBookings) {
        int validCount = 0;
        int rejectedCount = 0;
        int duplicateCount = 0;

        Set<String> acceptedPairs = new HashSet<>();

        if (rawBookings != null) {
            for (String[] booking : rawBookings) {
                if (booking == null || booking.length < 2) {
                    rejectedCount++;
                    continue;
                }

                String name = booking[0];
                String dest = booking[1];

                try {
                    BusTicket ticket = new BusTicket(name, dest);
                    String key = ticket.getPassengerName().toLowerCase() + "|" + ticket.getDestination().toLowerCase();

                    if (acceptedPairs.contains(key)) {
                        duplicateCount++;
                    } else {
                        acceptedPairs.add(key);
                        validCount++;
                    }
                } catch (IllegalArgumentException e) {
                    rejectedCount++;
                }
            }
        }

        System.out.println("Valid: " + validCount + " | Rejected: " + rejectedCount + " | Duplicates skipped: " + duplicateCount);
    }

    public static void main(String[] args) {
        String[][] rawBookings = {
            {"Divya", "Chennai"},
            {"", "Bangalore"},
            {"Ravi123", "Pune"},
            {"Divya", "Chennai"},
            {" ", " "}
        };

        processBatch(rawBookings);
    }
}
