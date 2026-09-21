package constructors_keywords.class_problems;

class BusTicketAccount {
    private String bookingId;
    private double ticketFare;
    private static P4_BoardingPenaltyCalculator penaltyCalculator;

    // Static block for class-level initialization
    static {
        penaltyCalculator = new P4_BoardingPenaltyCalculator(1.0);
    }

    // Full constructor
    public BusTicketAccount(String bookingId, double ticketFare) {
        this.bookingId = bookingId;
        this.ticketFare = ticketFare;
    }

    // Provisional constructor chaining
    public BusTicketAccount(String bookingId) {
        this(bookingId, 1000.0);
    }

    public String getBookingId() {
        return bookingId;
    }

    public double getTicketFare() {
        return ticketFare;
    }

    public final double calculatePenalty(int minutesLate) {
        return penaltyCalculator.calculatePenalty(ticketFare, minutesLate);
    }
}

class SleeperCoachAccount extends BusTicketAccount {
    public SleeperCoachAccount(String bookingId, double ticketFare) {
        super(bookingId, ticketFare);
    }
}

public class P5_FleetReconciliationEngine {

    public static void processBatch(BusTicketAccount[] accounts, double[] amounts, int[] minutesLateArray) {
        if (accounts == null || amounts == null || minutesLateArray == null) {
            System.out.println("Invalid batch input arrays.");
            return;
        }

        // Validate matching lengths for input safety
        if (accounts.length != amounts.length || accounts.length != minutesLateArray.length) {
            System.out.println("Input array length mismatch. Batch execution aborted.");
            return;
        }

        int processedCount = 0;
        int nullSkippedCount = 0;
        int sleeperCount = 0;
        int regularCount = 0;
        double grandTotalPenalties = 0.0;

        for (int i = 0; i < accounts.length; i++) {
            BusTicketAccount acc = accounts[i];

            // Null-safe check
            if (acc == null) {
                nullSkippedCount++;
                continue;
            }

            processedCount++;
            if (acc instanceof SleeperCoachAccount) {
                sleeperCount++;
            } else {
                regularCount++;
            }

            double penalty = acc.calculatePenalty(minutesLateArray[i]);
            grandTotalPenalties += penalty;
        }

        System.out.println(processedCount + " processed | " + nullSkippedCount + " null skipped | " 
            + sleeperCount + " sleeper | " + regularCount + " regular | grand total penalties = Rs " 
            + grandTotalPenalties);
    }

    public static void main(String[] args) {
        BusTicketAccount[] accounts = {
            new SleeperCoachAccount("BK001", 2000),
            null,
            new BusTicketAccount("BK002", 1200)
        };
        double[] amounts = {1200, 900, 700};
        int[] minutesLateArray = {10, 5, 0};

        processBatch(accounts, amounts, minutesLateArray);
    }
}
