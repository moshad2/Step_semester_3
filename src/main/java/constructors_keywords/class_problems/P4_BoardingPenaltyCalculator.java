package constructors_keywords.class_problems;

// Class locked against inheritance using 'final'
public final class P4_BoardingPenaltyCalculator {

    // Minimum floor percentage locked using 'final'
    private final double minimumPenaltyPercent;

    public P4_BoardingPenaltyCalculator(double minimumPenaltyPercent) {
        this.minimumPenaltyPercent = minimumPenaltyPercent;
    }

    // Method locked against overriding using 'final'
    public final double calculatePenalty(double ticketFare, int minutesLate) {
        if (ticketFare < 0 || minutesLate < 0) {
            throw new IllegalArgumentException("Ticket fare and minutes late must non-negative.");
        }

        if (minutesLate == 0) {
            return 0.0;
        }

        // Bracket 1: 1-5 mins @ 0.5% per min
        int bracket1Mins = Math.min(minutesLate, 5);
        double bracket1Cost = bracket1Mins * 0.005 * ticketFare;

        // Bracket 2: 6-15 mins @ 1.0% per min
        int bracket2Mins = 0;
        if (minutesLate > 5) {
            bracket2Mins = Math.min(minutesLate - 5, 10);
        }
        double bracket2Cost = bracket2Mins * 0.010 * ticketFare;

        // Bracket 3: 16+ mins @ 2.0% per min
        int bracket3Mins = 0;
        if (minutesLate > 15) {
            bracket3Mins = minutesLate - 15;
        }
        double bracket3Cost = bracket3Mins * 0.020 * ticketFare;

        double tieredPenalty = bracket1Cost + bracket2Cost + bracket3Cost;
        double minimumFloorPenalty = (minimumPenaltyPercent / 100.0) * ticketFare;

        return Math.max(tieredPenalty, minimumFloorPenalty);
    }

    public static void main(String[] args) {
        P4_BoardingPenaltyCalculator calc = new P4_BoardingPenaltyCalculator(1.0); // 1% minimum floor

        System.out.println("ticketFare = 1000, minutesLate = 0 -> Rs " + calc.calculatePenalty(1000, 0));
        System.out.println("ticketFare = 1000, minutesLate = 1 -> Rs " + calc.calculatePenalty(1000, 1));
        System.out.println("ticketFare = 1000, minutesLate = 16 -> Rs " + calc.calculatePenalty(1000, 16));
    }
}
