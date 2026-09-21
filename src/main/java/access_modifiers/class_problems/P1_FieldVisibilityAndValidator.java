package access_modifiers.class_problems;

public class P1_FieldVisibilityAndValidator {

    public static class PatientRecord {
        // private: Restricted to class internal logic
        private String patientId;
        // default (package-private): Accessible within same package
        String wardCode;
        // protected: Accessible within same package and subclasses
        protected double vitalsScore;
        // public: Accessible everywhere
        public String facilityName;

        // Requirement: No usable no-argument constructor
        public PatientRecord(String patientId, String wardCode, double vitalsScore, String facilityName) {
            if (patientId == null || patientId.trim().length() < 4) {
                throw new IllegalArgumentException("Patient ID must be non-null, non-blank, and at least 4 characters.");
            }
            this.patientId = patientId.trim();
            this.wardCode = wardCode;
            this.vitalsScore = vitalsScore;
            this.facilityName = facilityName;
        }

        public String getPatientId() {
            return patientId;
        }
    }

    public static String classifyAccess(String fieldModifier, String accessorContext) {
        if (fieldModifier == null || accessorContext == null) return "DENIED";

        switch (fieldModifier) {
            case "public":
                return "ALLOWED";
            case "private":
                return "SAME_CLASS".equals(accessorContext) ? "ALLOWED" : "DENIED";
            case "default":
            case "protected":
                return ("SAME_CLASS".equals(accessorContext) || "SAME_PACKAGE".equals(accessorContext)) ? "ALLOWED" : "DENIED";
            default:
                return "DENIED";
        }
    }

    public static String summarizeBatch(String[][] attempts) {
        int allowed = 0;
        int denied = 0;

        if (attempts != null) {
            for (String[] attempt : attempts) {
                if (attempt != null && attempt.length >= 2) {
                    String res = classifyAccess(attempt[0], attempt[1]);
                    if ("ALLOWED".equals(res)) allowed++;
                    else denied++;
                }
            }
        }

        return "Allowed: " + allowed + " | Denied: " + denied;
    }

    public static void main(String[] args) {
        System.out.println(classifyAccess("private", "SAME_CLASS"));
        System.out.println(classifyAccess("default", "DIFFERENT_PACKAGE"));

        String[][] batch = {
            {"protected", "SAME_PACKAGE"},
            {"protected", "DIFFERENT_PACKAGE"},
            {"public", "DIFFERENT_PACKAGE"}
        };
        System.out.println(summarizeBatch(batch));

        try {
            new PatientRecord("MT9", "W3", 98.2, "MediTrack Central");
        } catch (IllegalArgumentException e) {
            System.out.println("construction rejected");
        }
    }
}
