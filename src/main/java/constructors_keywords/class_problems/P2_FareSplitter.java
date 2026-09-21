package constructors_keywords.class_problems;

import java.util.Arrays;

public class P2_FareSplitter {
    private String tripId;
    private double totalFare;
    private int passengerCount;

    // Primary constructor with full validation
    public P2_FareSplitter(String tripId, double totalFare, int passengerCount) {
        if (totalFare < 0) {
            throw new IllegalArgumentException("Fare cannot be negative.");
        }
        if (passengerCount <= 0) {
            throw new IllegalArgumentException("Passenger count must be positive.");
        }
        this.tripId = tripId;
        this.totalFare = totalFare;
        this.passengerCount = passengerCount;
    }

    // Chained constructor 1: defaults passenger count to 2
    public P2_FareSplitter(String tripId, double totalFare) {
        this(tripId, totalFare, 2);
    }

    // Chained constructor 2: defaults fare to 0.0 and passenger count to 2 (Provisional split)
    public P2_FareSplitter(String tripId) {
        this(tripId, 0.0, 2);
    }

    public double[] fareBreakdown() {
        double[] breakdown = new double[passengerCount];
        if (totalFare == 0.0) {
            Arrays.fill(breakdown, 0.0);
            return breakdown;
        }

        long totalPaisa = Math.round(totalFare * 100);
        long baseSharePaisa = totalPaisa / passengerCount;
        long remainderPaisa = totalPaisa % passengerCount;

        for (int i = 0; i < passengerCount; i++) {
            // Remainder paisa distributed to the last passenger to avoid losing rounding money
            long sharePaisa = baseSharePaisa + (i == passengerCount - 1 ? remainderPaisa : 0);
            breakdown[i] = sharePaisa / 100.0;
        }

        return breakdown;
    }

    public boolean isConfirmationOverdue(int confirmed, int expected) {
        return confirmed < expected;
    }

    public static void main(String[] args) {
        P2_FareSplitter split1 = new P2_FareSplitter("TRIP001", 100000, 3);
        System.out.println(Arrays.toString(split1.fareBreakdown()));

        P2_FareSplitter split2 = new P2_FareSplitter("TRIP003");
        System.out.println(Arrays.toString(split2.fareBreakdown()));
    }
}
