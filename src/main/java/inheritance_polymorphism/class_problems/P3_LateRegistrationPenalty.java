package inheritance_polymorphism.class_problems;

import java.util.Arrays;

class PenaltyTrackedEventTicket extends EventTicket {
    private double[] lateFeeHistory;
    private int feeCount;

    public PenaltyTrackedEventTicket(String attendeeId, double basePrice) {
        super(attendeeId, basePrice);
        this.lateFeeHistory = new double[10];
        this.feeCount = 0;
    }

    protected void applyLateFee(double amount) {
        if (amount > 0) {
            if (feeCount < lateFeeHistory.length) {
                lateFeeHistory[feeCount++] = amount;
            }
            pay(-amount); // Increases balance due by subtracting negative payment
        }
    }

    public double[] getLateFeeHistory() {
        return Arrays.copyOf(lateFeeHistory, feeCount);
    }
}

class PenaltyTrackedWorkshopTicket extends PenaltyTrackedEventTicket {

    public PenaltyTrackedWorkshopTicket(String attendeeId, double basePrice) {
        super(attendeeId, basePrice);
    }

    @Override
    protected void applyLateFee(double amount) {
        super.applyLateFee(amount * 2);
    }
}

public class P3_LateRegistrationPenalty {
    public static void main(String[] args) {
        PenaltyTrackedWorkshopTicket w = new PenaltyTrackedWorkshopTicket("STU2", 1200);
        w.pay(1200);
        w.applyLateFee(100);
        System.out.println(w.getBalanceDue());

        double[] history = w.getLateFeeHistory();
        System.out.println(Arrays.toString(history));

        history[0] = 999;
        System.out.println(Arrays.toString(w.getLateFeeHistory()));
    }
}
