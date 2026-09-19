package oop.class_problems;

// Broken Version demonstrating static field pollution
class BrokenSrmStudent {
    static String name;
    static String regNo;
    static int attendance;

    public BrokenSrmStudent(String name, String regNo, int attendance) {
        BrokenSrmStudent.name = name;
        BrokenSrmStudent.regNo = regNo;
        BrokenSrmStudent.attendance = attendance;
    }
}

// Correct Version
class CorrectSrmStudent {
    // Instance fields: unique per student
    String name;
    String regNo;
    int attendance;

    // Static fields: shared across university/class level
    static String university = "SRM";
    static int admissionCount = 0;

    public CorrectSrmStudent(String name, int attendance) {
        this.name = name;
        this.attendance = attendance;
        admissionCount++;
        this.regNo = "RA23110030101" + admissionCount;
    }

    public void printIdCard() {
        System.out.println(name + " | " + regNo);
    }

    public static void printTotalAdmissions() {
        System.out.println("Students admitted so far: " + admissionCount);
    }
}

public class F4_StaticBoundaryFix {
    public static void main(String[] args) {
        System.out.println("--- Broken Version ---");
        BrokenSrmStudent s1 = new BrokenSrmStudent("Ravi", "RA01", 80);
        BrokenSrmStudent s2 = new BrokenSrmStudent("Meera", "RA02", 90);
        System.out.println("s1 name: " + BrokenSrmStudent.name);
        System.out.println("s2 name: " + BrokenSrmStudent.name);
        System.out.println("(Ravi's data was overwritten; both show " + BrokenSrmStudent.name + ")\n");

        System.out.println("--- Fixed Version ---");
        CorrectSrmStudent fixed1 = new CorrectSrmStudent("Ravi", 80);
        CorrectSrmStudent fixed2 = new CorrectSrmStudent("Meera", 90);
        fixed1.printIdCard();
        fixed2.printIdCard();
        CorrectSrmStudent.printTotalAdmissions();
    }
}
