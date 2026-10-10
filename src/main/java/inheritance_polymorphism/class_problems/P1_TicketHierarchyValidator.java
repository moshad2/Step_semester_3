package inheritance_polymorphism.class_problems;

class EventTicket {
    private String attendeeId;
    private double basePrice;
    private double amountPaid;

    public EventTicket(String attendeeId, double basePrice) {
        if (attendeeId == null || attendeeId.trim().length() < 4) {
            throw new IllegalArgumentException("Attendee ID must be at least 4 characters long.");
        }
        this.attendeeId = attendeeId.trim();
        this.basePrice = basePrice;
        this.amountPaid = 0.0;
    }

    public void pay(double amount) {
        if (amount > 0) {
            this.amountPaid += amount;
        }
    }

    public double getBalanceDue() {
        return basePrice - amountPaid;
    }

    public String getAttendeeId() {
        return attendeeId;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void printTicket() {
        System.out.println("Standard Event Ticket | Balance Due: " + getBalanceDue());
    }
}

class WorkshopTicket extends EventTicket {
    private String track;

    public WorkshopTicket(String attendeeId, double basePrice, String track) {
        super(attendeeId, basePrice);
        this.track = track;
    }

    public String getTrack() {
        return track;
    }

    @Override
    public void printTicket() {
        System.out.println("Workshop Ticket | Track: " + track + " | Balance Due: " + getBalanceDue());
    }
}

public class P1_TicketHierarchyValidator {

    public static String registerBatch(String[] attendeeIds, double basePrice) {
        int registered = 0;
        int rejected = 0;

        if (attendeeIds != null) {
            for (String id : attendeeIds) {
                try {
                    new EventTicket(id, basePrice);
                    registered++;
                } catch (IllegalArgumentException e) {
                    rejected++;
                }
            }
        }

        return "Registered: " + registered + " Rejected: " + rejected;
    }

    public static void main(String[] args) {
        try {
            new EventTicket("ST1", 500);
        } catch (IllegalArgumentException e) {
            System.out.println("construction rejected");
        }

        WorkshopTicket w = new WorkshopTicket("STU2", 1200, "AI/ML");
        w.pay(500);
        System.out.println(w.getBalanceDue());

        String[] batch = {"STU1", "ST1", "STU2", "", "STU3"};
        System.out.println(registerBatch(batch, 500));
    }
}
