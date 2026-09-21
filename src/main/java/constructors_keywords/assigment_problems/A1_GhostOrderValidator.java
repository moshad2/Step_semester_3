package constructors_keywords.assigment_problems;

public class A1_GhostOrderValidator {

    public static class FoodOrder {
        private String studentName;
        private String dishName;
        private boolean isDelivered;

        // Parameterized constructor acting as a validation gate
        public FoodOrder(String studentName, String dishName) {
            if (studentName == null || studentName.trim().isEmpty()) {
                throw new IllegalArgumentException("Student name cannot be blank or null.");
            }
            if (dishName == null || dishName.trim().isEmpty()) {
                throw new IllegalArgumentException("Dish name cannot be blank or null.");
            }
            this.studentName = studentName.trim();
            this.dishName = dishName.trim();
            this.isDelivered = false;
        }

        public void markDelivered() {
            if (this.isDelivered) {
                System.out.println("Warning: Order for " + studentName + " (" + dishName + ") was ALREADY marked delivered!");
            } else {
                this.isDelivered = true;
                System.out.println("Order for " + studentName + " (" + dishName + ") successfully marked delivered.");
            }
        }

        public String getStudentName() {
            return studentName;
        }

        public String getDishName() {
            return dishName;
        }
    }

    public static void processBatch(String[][] rawOrders) {
        int validCount = 0;
        int rejectedCount = 0;

        if (rawOrders != null) {
            for (String[] rawOrder : rawOrders) {
                if (rawOrder == null || rawOrder.length < 2) {
                    rejectedCount++;
                    continue;
                }

                try {
                    new FoodOrder(rawOrder[0], rawOrder[1]);
                    validCount++;
                } catch (IllegalArgumentException e) {
                    rejectedCount++;
                }
            }
        }

        System.out.println("Valid: " + validCount + " | Rejected: " + rejectedCount);
    }

    public static void main(String[] args) {
        String[][] rawOrders = {
            {"Ravi", "Paneer Butter Masala"},
            {"", "Chole Bhature"},
            {"Meera", " "},
            {"Divya", "Veg Biryani"}
        };

        processBatch(rawOrders);
    }
}
