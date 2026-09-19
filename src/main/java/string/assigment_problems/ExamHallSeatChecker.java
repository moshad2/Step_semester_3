package string.assigment_problems;

public class ExamHallSeatChecker {

    public static void checkDuplicateSeats(int[] seatNumbers) {
        boolean hasDuplicates = false;

        for (int i = 0; i < seatNumbers.length; i++) {
            for (int j = i + 1; j < seatNumbers.length; j++) {
                if (seatNumbers[i] == seatNumbers[j]) {
                    System.out.println("Duplicate Seat Number Found: " + seatNumbers[i]);
                    hasDuplicates = true;
                    break;
                }
            }
        }

        if (!hasDuplicates) {
            System.out.println("No Duplicate Seats Found");
        }
    }

    public static void main(String[] args) {
        int[] test1 = {101, 102, 103, 102, 105};
        int[] test2 = {101, 102, 103, 104, 105};

        checkDuplicateSeats(test1);
        checkDuplicateSeats(test2);
    }
}
