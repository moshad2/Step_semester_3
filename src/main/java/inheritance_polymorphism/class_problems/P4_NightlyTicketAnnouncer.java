package inheritance_polymorphism.class_problems;

public class P4_NightlyTicketAnnouncer {

    public static String batchPrint(EventTicket[] tickets) {
        StringBuilder sb = new StringBuilder();
        if (tickets != null) {
            for (int i = 0; i < tickets.length; i++) {
                EventTicket t = tickets[i];
                if (t instanceof WorkshopTicket) {
                    WorkshopTicket wt = (WorkshopTicket) t;
                    sb.append("Workshop | Track: ").append(wt.getTrack())
                      .append(" | Balance: ").append(wt.getBalanceDue())
                      .append(" [Track via downcast: ").append(wt.getTrack()).append("]");
                } else {
                    sb.append("Standard Balance: ").append(t.getBalanceDue());
                }

                if (i < tickets.length - 1) {
                    sb.append(" | ");
                }
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        EventTicket[] tickets = {
            new EventTicket("STU1", 500),
            new WorkshopTicket("STU2", 1200, "AI/ML")
        };

        System.out.println(batchPrint(tickets));
    }
}
