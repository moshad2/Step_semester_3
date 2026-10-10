package inheritance_polymorphism.assigment_problems;

import java.util.Arrays;

class PenaltyTrackedRaceEntry extends RaceEntry {
    private double[] lateFeeHistory;
    private int feeCount;

    public PenaltyTrackedRaceEntry(String bibNumber, double entryFee) {
        super(bibNumber, entryFee);
        this.lateFeeHistory = new double[10];
        this.feeCount = 0;
    }

    protected void applyLateFee(double amount) {
        if (amount > 0) {
            if (feeCount < lateFeeHistory.length) {
                lateFeeHistory[feeCount++] = amount;
            }
            pay(-amount); // Increases balance due
        }
    }

    public double[] getLateFeeHistory() {
        return Arrays.copyOf(lateFeeHistory, feeCount);
    }
}

class PenaltyTrackedRunnerEntry extends PenaltyTrackedRaceEntry {

    public PenaltyTrackedRunnerEntry(String bibNumber, double entryFee, String category) {
        super(bibNumber, entryFee);
    }

    @Override
    protected void applyLateFee(double amount) {
        super.applyLateFee(amount * 2);
    }
}

public class A3_LateWithdrawalPenalty {
    public static void main(String[] args) {
        PenaltyTrackedRunnerEntry r = new PenaltyTrackedRunnerEntry("BIB2001", 80, "Open 10K");
        r.pay(30);
        r.applyLateFee(20);
        System.out.println(r.getBalanceDue());

        double[] history = r.getLateFeeHistory();
        System.out.println(Arrays.toString(history));

        history[0] = 999;
        System.out.println(Arrays.toString(r.getLateFeeHistory()));
    }
}
