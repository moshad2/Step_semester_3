package oop.class_problems;

class FeeAccount {
    private String regNo;
    private double totalFee;
    private double amountPaid;

    public FeeAccount(String regNo, double totalFee, double amountPaid) {
        this.regNo = regNo;
        this.totalFee = totalFee;
        this.amountPaid = amountPaid;
    }

    public void pay(double amount) {
        if (amount <= 0) {
            System.out.println("Rejected: Payment amount must be positive.");
            return;
        }
        this.amountPaid += amount;
    }

    public double getDue() {
        return totalFee - amountPaid;
    }
}

class HostelFeeAccount extends FeeAccount {
    public HostelFeeAccount(String regNo, double totalFee, double amountPaid) {
        super(regNo, totalFee, amountPaid);
    }

    public void payInTwoInstallments(double amount) {
        pay(amount / 2);
        pay(amount / 2);
    }
}

class ScholarshipFeeAccount extends FeeAccount {
    private double scholarshipPercent;

    public ScholarshipFeeAccount(String regNo, double totalFee, double amountPaid, double scholarshipPercent) {
        super(regNo, totalFee, amountPaid);
        this.scholarshipPercent = scholarshipPercent;
    }

    public double effectiveDue() {
        double currentDue = getDue();
        return currentDue - (currentDue * (scholarshipPercent / 100.0));
    }
}

public class F2_FeeAccountSystem {
    public static void main(String[] args) {
        FeeAccount plain = new FeeAccount("REG01", 150000, 150000);
        HostelFeeAccount hostel = new HostelFeeAccount("REG02", 200000, 60000);
        ScholarshipFeeAccount scholarship = new ScholarshipFeeAccount("REG03", 180000, 0, 20);

        FeeAccount[] accounts = {plain, hostel, scholarship};

        for (FeeAccount acc : accounts) {
            if (acc instanceof ScholarshipFeeAccount) {
                ScholarshipFeeAccount sAcc = (ScholarshipFeeAccount) acc;
                System.out.println("Scholarship account effective due: Rs " + sAcc.effectiveDue());
            } else if (acc instanceof HostelFeeAccount) {
                System.out.println("Hostel account due: Rs " + acc.getDue());
            } else {
                System.out.println("Plain account due: Rs " + acc.getDue());
            }
        }
    }
}
