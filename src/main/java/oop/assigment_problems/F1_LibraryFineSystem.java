package oop.assigment_problems;

class BookIssue {
    String title;
    String borrowerName;
    int daysOverdue;

    public BookIssue(String title, String borrowerName, int daysOverdue) {
        this.title = title;
        this.borrowerName = borrowerName;
        this.daysOverdue = daysOverdue;
    }

    public double fineAmount() {
        return daysOverdue > 0 ? daysOverdue * 5.0 : 0.0;
    }

    public boolean isSeverelyOverdue() {
        return daysOverdue > 14;
    }

    // totalFineCollected is static because it computes an aggregate total across an array of all BookIssue objects,
    // whereas fineAmount is an instance method calculating the fine for a specific individual book issue.
    public static double totalFineCollected(BookIssue[] issues) {
        if (issues == null) return 0.0;
        double sum = 0.0;
        for (BookIssue issue : issues) {
            if (issue != null) {
                sum += issue.fineAmount();
            }
        }
        return sum;
    }
}

public class F1_LibraryFineSystem {
    public static void main(String[] args) {
        BookIssue[] issues = {
            new BookIssue("Clean Code", "Alice", 18),
            new BookIssue("Effective Java", "Bob", 5),
            new BookIssue("Refactoring", "Charlie", 0),
            new BookIssue("DSA Handbook", "David", 21),
            new BookIssue("Design Patterns", "Eve", 9)
        };

        for (BookIssue issue : issues) {
            String status = issue.isSeverelyOverdue() ? "Severely overdue" : (issue.daysOverdue > 0 ? issue.daysOverdue + " days OK" : "days OK");
            System.out.println(issue.title + " " + (issue.daysOverdue > 0 ? issue.daysOverdue + " days " : "") + status);
        }

        System.out.println("Total fine collected: Rs " + BookIssue.totalFineCollected(issues));
    }
}
