package oop.assigment_problems;

class CompanyEmployeeRecord {
    String name;
    String empId;
    Employee employee;
    ParkingSlot slot;

    static int totalRecords = 0;

    public CompanyEmployeeRecord(String name, String empId, Employee employee, ParkingSlot slot) {
        this.name = name;
        this.empId = empId;
        this.employee = employee;
        this.slot = slot;
        totalRecords++;
    }

    public String fullProfile() {
        String slotStr = (slot != null) ? slot.slotNo : "no parking assigned";
        double pay = 0.0;

        if (employee instanceof ManagerEmployee) {
            pay = ((ManagerEmployee) employee).effectiveSalary();
        } else if (employee instanceof InternEmployee) {
            pay = ((InternEmployee) employee).effectiveSalary();
        } else if (employee != null) {
            pay = employee.getSalary();
        }

        return name + " | Pay: Rs " + pay + " | Slot: " + slotStr;
    }
}

public class F5_HrParkingMiniSystem {
    public static void main(String[] args) {
        ParkingSlot slot1 = new ParkingSlot("A1", 4, 3);
        ParkingSlot slot2 = new ParkingSlot("A2", 5, 4);

        ManagerEmployee m1 = new ManagerEmployee("E02", "Divya", 70000, 8000);
        Employee e1 = new Employee("E01", "Karan", 40000);
        InternEmployee i1 = new InternEmployee("E03", "Meera", 12000, 10000);

        CompanyEmployeeRecord r1 = new CompanyEmployeeRecord("Divya", "E02", m1, slot1);
        CompanyEmployeeRecord r2 = new CompanyEmployeeRecord("Karan", "E01", e1, slot2);
        CompanyEmployeeRecord r3 = new CompanyEmployeeRecord("Meera", "E03", i1, null);

        System.out.println(r1.fullProfile());
        System.out.println(r2.fullProfile());
        System.out.println(r3.fullProfile());
        System.out.println("Total records: " + CompanyEmployeeRecord.totalRecords);
    }
}
