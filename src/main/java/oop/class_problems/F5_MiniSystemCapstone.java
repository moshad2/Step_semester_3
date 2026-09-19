package oop.class_problems;

class CapstoneStudent {
    String name;
    String regNo;
    HostelFeeAccount feeAccount;
    HostelRoom room;

    static int totalStudents = 0;

    public CapstoneStudent(String name, String regNo, HostelFeeAccount feeAccount, HostelRoom room) {
        this.name = name;
        this.regNo = regNo;
        this.feeAccount = feeAccount;
        this.room = room;
        totalStudents++;
    }

    public String fullStatus() {
        String roomStr = (room != null) ? room.roomNo : "unallotted";
        double due = (feeAccount != null) ? feeAccount.getDue() : 0.0;
        return name + " | Due: Rs " + due + " | Room: " + roomStr;
    }
}

public class F5_MiniSystemCapstone {
    public static void main(String[] args) {
        HostelRoom room1 = new HostelRoom("C-214", 3, 2);
        HostelRoom room2 = new HostelRoom("C-507", 2, 1);

        HostelFeeAccount fee1 = new HostelFeeAccount("REG01", 200000, 60000);
        HostelFeeAccount fee2 = new HostelFeeAccount("REG02", 200000, 20000);
        HostelFeeAccount fee3 = new HostelFeeAccount("REG03", 200000, 0);

        // Attempt invalid payment
        fee3.pay(-5000);

        CapstoneStudent s1 = new CapstoneStudent("Ravi", "REG01", fee1, room1);
        CapstoneStudent s2 = new CapstoneStudent("Anitha", "REG02", fee2, room2);
        CapstoneStudent s3 = new CapstoneStudent("Karthik", "REG03", fee3, null);

        System.out.println(s1.fullStatus());
        System.out.println(s2.fullStatus());
        System.out.println(s3.fullStatus());
        System.out.println("Total students: " + CapstoneStudent.totalStudents);
    }
}
