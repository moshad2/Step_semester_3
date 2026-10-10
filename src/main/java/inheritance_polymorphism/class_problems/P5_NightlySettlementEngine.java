package inheritance_polymorphism.class_problems;

class GroupTicket extends EventTicket {
    private int groupSize;

    public GroupTicket(double basePrice, int groupSize) {
        super("GRP-" + System.currentTimeMillis(), basePrice * groupSize);
        this.groupSize = groupSize;
    }

    public int getGroupSize() {
        return groupSize;
    }
}

public class P5_NightlySettlementEngine {

    private static int ticketCounter = 1000;

    public static synchronized String generateTicketId() {
        ticketCounter++;
        return "TCK-" + ticketCounter;
    }

    public static boolean isValidPromoCode(String code) {
        if (code == null || code.length() != 5) {
            return false;
        }
        if (code.charAt(0) != 'F') {
            return false;
        }
        if (!Character.isDigit(code.charAt(1)) || 
            !Character.isDigit(code.charAt(2)) || 
            !Character.isDigit(code.charAt(3))) {
            return false;
        }
        return Character.isUpperCase(code.charAt(4));
    }

    public static void payWithMode(EventTicket ticket, double amount, String mode) {
        if (ticket != null) {
            System.out.println("Paid Rs " + amount + " via " + mode);
            ticket.pay(amount);
        }
    }

    public static String processNightlySettlement(EventTicket[] tickets) {
        int processed = 0;
        int nullSkipped = 0;
        int groupCount = 0;
        int individualCount = 0;

        if (tickets != null) {
            for (EventTicket t : tickets) {
                if (t == null) {
                    nullSkipped++;
                    continue;
                }
                processed++;
                if (t instanceof GroupTicket) {
                    groupCount++;
                } else {
                    individualCount++;
                }
            }
        }

        return processed + " processed | " + nullSkipped + " null skipped | " 
            + groupCount + " group | " + individualCount + " individual";
    }

    public static void main(String[] args) {
        System.out.println(isValidPromoCode("F123A"));
        System.out.println(isValidPromoCode("F12A"));
        System.out.println(isValidPromoCode("X123A"));

        EventTicket t1 = new EventTicket("STU1", 500);
        t1.pay(200);
        payWithMode(t1, 200, "UPI");
        System.out.println(t1.getBalanceDue());

        EventTicket[] batch = {
            new GroupTicket(2000, 5),
            null,
            new EventTicket("STU2", 500)
        };
        System.out.println(processNightlySettlement(batch));
    }
}
