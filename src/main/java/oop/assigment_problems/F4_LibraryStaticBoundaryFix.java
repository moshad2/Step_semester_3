package oop.assigment_problems;

// Broken Version
class BrokenLibraryMember {
    // Marking these static means every member shares the same memory location,
    // so creating a new member overwrites the values for all previously created members.
    static String name;
    static String memberId;
    static int booksIssued;

    public BrokenLibraryMember(String name, String memberId, int booksIssued) {
        BrokenLibraryMember.name = name;
        BrokenLibraryMember.memberId = memberId;
        BrokenLibraryMember.booksIssued = booksIssued;
    }
}

// Fixed Version
class CorrectLibraryMember {
    // Instance fields: unique per library member
    String name;
    String memberId;
    int booksIssued;

    // Static fields: shared across the library system
    static String libraryName = "SRM Central Library";
    static int memberCount = 1000;

    public CorrectLibraryMember(String name, int booksIssued) {
        this.name = name;
        this.booksIssued = booksIssued;
        memberCount++;
        this.memberId = "LM-" + memberCount;
    }

    public void printMemberCard() {
        System.out.println(name + " " + memberId);
    }

    public static void printTotalMembers() {
        System.out.println("Total members: " + (memberCount - 1000));
    }
}

public class F4_LibraryStaticBoundaryFix {
    public static void main(String[] args) {
        System.out.println("--- Broken Version ---");
        BrokenLibraryMember b1 = new BrokenLibraryMember("Aditi", "LM-1001", 2);
        BrokenLibraryMember b2 = new BrokenLibraryMember("Rohan", "LM-1002", 1);
        System.out.println("b1 name: " + BrokenLibraryMember.name);
        System.out.println("b2 name: " + BrokenLibraryMember.name);
        System.out.println("(Aditi's data was overwritten; both members now show " + BrokenLibraryMember.name + ")\n");

        System.out.println("--- Fixed Version ---");
        CorrectLibraryMember c1 = new CorrectLibraryMember("Aditi", 2);
        CorrectLibraryMember c2 = new CorrectLibraryMember("Rohan", 1);
        c1.printMemberCard();
        c2.printMemberCard();
        CorrectLibraryMember.printTotalMembers();
    }
}
