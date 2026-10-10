package inheritance_polymorphism.assigment_problems;

public class A4_RaceDayAnnouncerBoard {

    public static String announceAll(RaceEntry[] entries) {
        StringBuilder sb = new StringBuilder();
        if (entries != null) {
            for (int i = 0; i < entries.length; i++) {
                RaceEntry entry = entries[i];
                if (entry instanceof RunnerEntry) {
                    RunnerEntry runner = (RunnerEntry) entry;
                    sb.append("Runner Entry | Bib: ").append(runner.getBibNumber())
                      .append(" | Category: ").append(runner.getCategory())
                      .append(" | Balance: ").append(runner.getBalanceDue());
                } else if (entry instanceof RelayTeamEntry) {
                    RelayTeamEntry relay = (RelayTeamEntry) entry;
                    sb.append("Relay Team | Bib: ").append(relay.getBibNumber())
                      .append(" | Team Size: ").append(relay.getTeamSize())
                      .append(" | Balance: ").append(relay.getBalanceDue())
                      .append(" [Team size via downcast: ").append(relay.getTeamSize()).append("]");
                } else {
                    sb.append("Race Entry | Bib: ").append(entry.getBibNumber())
                      .append(" | Balance: ").append(entry.getBalanceDue());
                }

                if (i < entries.length - 1) {
                    sb.append(" | ");
                }
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        RunnerEntry runnerEntry = new RunnerEntry("BIB2001", 80, "Open 10K");
        runnerEntry.pay(30);
        runnerEntry.pay(-40); // balance becomes 90

        RelayTeamEntry relayEntry = new RelayTeamEntry("BIB4001", 300, 4);

        RaceEntry[] fleet = { runnerEntry, relayEntry };
        System.out.println(announceAll(fleet));
    }
}
