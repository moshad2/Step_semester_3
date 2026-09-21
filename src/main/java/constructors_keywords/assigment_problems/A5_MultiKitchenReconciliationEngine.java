package constructors_keywords.assigment_problems;

class DeliveryAccount {
    private String studentId;
    private double orderValue;
    private static A4_SurgeFeeCalculator surgeCalculator;

    // Static setup block for one-time initialization
    static {
        surgeCalculator = new A4_SurgeFeeCalculator(1.0);
    }

    // Full constructor
    public DeliveryAccount(String studentId, double orderValue) {
        this.studentId = studentId;
        this.orderValue = orderValue;
    }

    // Provisional constructor chaining
    public DeliveryAccount(String studentId) {
        this(studentId, 0.0);
    }

    public String getStudentId() {
        return studentId;
    }

    public double getOrderValue() {
        return orderValue;
    }

    public final double calculateSurgeFee(int delayMinutes) {
        return surgeCalculator.calculateSurgeFee(orderValue, delayMinutes);
    }
}

class PremiumDeliveryAccount extends DeliveryAccount {
    public PremiumDeliveryAccount(String studentId, double orderValue) {
        super(studentId, orderValue);
    }
}

public class A5_MultiKitchenReconciliationEngine {

    public static void processBatch(DeliveryAccount[] accounts, double[] amounts, int[] delayMinutesArray) {
        if (accounts == null || amounts == null || delayMinutesArray == null) {
            System.out.println("Invalid input arrays.");
            return;
        }

        // Validate matching lengths across parallel arrays
        if (accounts.length != amounts.length || accounts.length != delayMinutesArray.length) {
            System.out.println("Input array length mismatch. Batch execution aborted to prevent quiet data misalignment.");
            return;
        }

        int processedCount = 0;
        int nullSkippedCount = 0;
        int premiumCount = 0;
        int regularCount = 0;
        double grandTotalSurgeFees = 0.0;

        for (int i = 0; i < accounts.length; i++) {
            DeliveryAccount account = accounts[i];

            // Null-safe check preventing uncaught NullPointerExceptions
            if (account == null) {
                nullSkippedCount++;
                continue;
            }

            processedCount++;
            if (account instanceof PremiumDeliveryAccount) {
                premiumCount++;
            } else {
                regularCount++;
            }

            double surgeFee = account.calculateSurgeFee(delayMinutesArray[i]);
            grandTotalSurgeFees += surgeFee;
        }

        System.out.println(processedCount + " processed | " + nullSkippedCount + " null skipped | " 
            + premiumCount + " premium | " + regularCount + " regular | grand total surge fees = Rs " 
            + grandTotalSurgeFees);
    }

    public static void main(String[] args) {
        DeliveryAccount[] accounts = {
            new PremiumDeliveryAccount("STU001", 500),
            null,
            new DeliveryAccount("STU002", 300)
        };
        double[] amounts = {500, 400, 300};
        int[] delayMinutesArray = {10, 5, 0};

        processBatch(accounts, amounts, delayMinutesArray);
    }
}
