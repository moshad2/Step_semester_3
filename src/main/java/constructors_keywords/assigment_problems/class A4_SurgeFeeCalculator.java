package constructors_keywords.assigment_problems;

// Class locked against modification using 'final'
public final class A4_SurgeFeeCalculator {

    // Field locked using 'final'
    private final double minimumSurgePercent;

    public A4_SurgeFeeCalculator(double minimumSurgePercent) {
        this.minimumSurgePercent = minimumSurgePercent;
    }

    // Method locked against overriding using 'final'
    public final double calculateSurgeFee(double orderValue, int delayMinutes) {
        if (orderValue < 0 || delayMinutes < 0) {
            throw new IllegalArgumentException("Order value and delay minutes must be non-negative.");
        }

        if (delayMinutes == 0) {
            return 0.0;
        }

        // Bracket 1: Minutes 1-5 @ 0.5% per minute
        int bracket1Mins = Math.min(delayMinutes, 5);
        double bracket1Cost = bracket1Mins * 0.005 * orderValue;

        // Bracket 2: Minutes 6-15 @ 1.0% per minute
        int bracket2Mins = 0;
        if (delayMinutes > 5) {
            bracket2Mins = Math.min(delayMinutes - 5, 10);
        }
        double bracket2Cost = bracket2Mins * 0.010 * orderValue;

        // Bracket 3: Minutes 16+ @ 2.0% per minute
        int bracket3Mins = 0;
        if (delayMinutes > 15) {
            bracket3Mins = delayMinutes - 15;
        }
        double bracket3Cost = bracket3Mins * 0.020 * orderValue;

        double tieredSurgeFee = bracket1Cost + bracket2Cost + bracket3Cost;
        double minimumFloorFee = (minimumSurgePercent / 100.0) * orderValue;

        return Math.max(tieredSurgeFee, minimumFloorFee);
    }

    public static void main(String[] args) {
        A4_SurgeFeeCalculator calc = new A4_SurgeFeeCalculator(1.0); // 1.0% floor

        System.out.println("orderValue = 500, delayMinutes = 0 -> Rs " + calc.calculateSurgeFee(500, 0));
        System.out.println("orderValue = 500, delayMinutes = 1 -> Rs " + calc.calculateSurgeFee(500, 1));
        System.out.println("orderValue = 500, delayMinutes = 16 -> Rs " + calc.calculateSurgeFee(500, 16));
    }
}
