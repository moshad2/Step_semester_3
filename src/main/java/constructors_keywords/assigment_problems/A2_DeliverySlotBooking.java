package constructors_keywords.assigment_problems;

import java.util.Arrays;

public class A2_DeliverySlotBooking {
    private String orderId;
    private String timeSlot;

    // Primary constructor
    public A2_DeliverySlotBooking(String orderId, String timeSlot) {
        this.orderId = orderId;
        this.timeSlot = timeSlot;
    }

    // Chained constructor defaulting timeSlot to "ASAP"
    public A2_DeliverySlotBooking(String orderId) {
        this(orderId, "ASAP");
    }

    public boolean isPeakHour() {
        if (timeSlot == null) return false;
        
        String[] peakSlots = {"12:00-13:00", "13:00-14:00", "19:00-20:00", "20:00-21:00"};
        return Arrays.asList(peakSlots).contains(timeSlot.trim());
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    public static void main(String[] args) {
        A2_DeliverySlotBooking slot1 = new A2_DeliverySlotBooking("ORD101", "13:00-14:00");
        System.out.println("ORD101 isPeakHour: " + slot1.isPeakHour());

        A2_DeliverySlotBooking slot2 = new A2_DeliverySlotBooking("ORD102");
        System.out.println("ORD102 (" + slot2.getTimeSlot() + ") isPeakHour: " + slot2.isPeakHour());
    }
}
