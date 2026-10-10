package inheritance_polymorphism.assigment_problems;

public class A5_RaceNightlySettlementEngine {

    private static int bibCounter = 0;

    public static synchronized int incrementAndGetBibCounter() {
        return ++bibCounter;
    }

    public static int getBibCounter() {
        return bibCounter;
    }

    public static boolean isValidDiscountCode(String code) {
        if (code == null || code.length() != 5) {
            return false;
        }
        if (code.charAt(0) != 'M') {
            return false;
        }
        if (!Character.isDigit(code.charAt(1)) || 
            !Character.isDigit(code.charAt(2)) || 
            !Character.isDigit(code.charAt(3))) {
            return false;
        }
        return Character.isUpperCase(code.charAt(4));
    }

    public static void payWithMode(RaceEntry entry, double amount, String mode) {
        if (entry != null) {
            System.out.println("Paying via " + mode);
            entry.pay(amount);
        }
    }

    public static String settleNight(RaceEntry[] entries) {
        int processed = 0;
        int nullSkipped = 0;
        int relayCount = 0;
        int individualCount = 0;

        if (entries != null) {
            for (RaceEntry e : entries) {
                if (e == null) {
                    nullSkipped++;
                    continue;
                }
                processed++;
                if (e instanceof RelayTeamEntry) {
                    relayCount++;
                } else {
                    individualCount++;
                }
            }
        }

        return processed + " processed | " + nullSkipped + " null skipped | " 
            + relayCount + " relay | " + individualCount + " individual";
    }

    public static void main(String[] args) {
        System.out.println(isValidDiscountCode("M123A"));
        System.out.println(isValidDiscountCode("M12A"));
        System.out.println(isValidDiscountCode("X123A"));

        RunnerEntry r = new RunnerEntry("BIB2001", 80, "Open 10K");
        payWithMode(r, 10, "UPI");

        EliteRunnerEntry eliteEntry = new EliteRunnerEntry("BIB3001", 150, "Elite Full Marathon", 500);
        RelayTeamEntry relayEntry = new RelayTeamEntry("BIB4001", 300, 4);

        // Increment counter for created entries
        incrementAndGetBibCounter();
        incrementAndGetBibCounter();
        incrementAndGetBibCounter();
        incrementAndGetBibCounter();

        RaceEntry[] batch = { eliteEntry, null, relayEntry };
        System.out.println(settleNight(batch));
        System.out.println(getBibCounter());
    }
}
