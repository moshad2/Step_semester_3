package oop.class_problems;

class SrmStudent {
    String name;
    String regNo;
    int attendance;

    public SrmStudent(String name, String regNo, int attendance) {
        this.name = name;
        this.regNo = regNo;
        this.attendance = attendance;
    }

    public void addAttendanceUpdate(int newAttendance) {
        this.attendance = newAttendance;
    }

    public boolean isEligible() {
        return this.attendance >= 75;
    }

    // classAverage is static because it operates across an array of all students as a utility calculation,
    // whereas isEligible is an instance method evaluating state for a single specific student object.
    public static double classAverage(SrmStudent[] students) {
        if (students == null || students.length == 0) return 0.0;
        double sum = 0;
        for (SrmStudent s : students) {
            sum += s.attendance;
        }
        return sum / students.length;
    }
}

public class F1_AttendanceSystem {
    public static void main(String[] args) {
        SrmStudent[] students = {
            new SrmStudent("Ravi", "REG01", 82),
            new SrmStudent("Anitha", "REG02", 68),
            new SrmStudent("Karthik", "REG03", 91),
            new SrmStudent("Meera", "REG04", 74),
            new SrmStudent("Suresh", "REG05", 60)
        };

        for (SrmStudent s : students) {
            String status = s.isEligible() ? "Eligible" : "Detained";
            System.out.println(s.name + " " + s.attendance + "% " + status);
        }

        System.out.printf("Class average: %.1f%%%n", SrmStudent.classAverage(students));
    }
}
