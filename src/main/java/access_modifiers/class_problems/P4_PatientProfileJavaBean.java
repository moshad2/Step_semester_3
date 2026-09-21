package access_modifiers.class_problems;

public class P4_PatientProfileJavaBean {

    public static class PatientProfile {
        private String patientId;
        private String name;
        private boolean discharged;
        private String lockerPinHash;

        // Constructor 1: JavaBean requirement
        public PatientProfile() {
            this(null, null);
        }

        // Constructor 2: Name-only
        public PatientProfile(String name) {
            this(null, name);
        }

        // Constructor 3: Full id + name
        public PatientProfile(String patientId, String name) {
            this.patientId = patientId;
            this.name = name;
            this.discharged = false;
            this.lockerPinHash = null;
        }

        public String getPatientId() {
            return patientId;
        }

        // Write-once setter
        public void setPatientId(String id) {
            if (this.patientId == null) {
                this.patientId = id;
            }
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean isDischarged() {
            return discharged;
        }

        public void setDischarged(boolean discharged) {
            this.discharged = discharged;
        }

        // Write-only property: No getter exists anywhere
        public void setLockerPin(String pin) {
            if (pin != null && pin.matches("\\d{4,6}")) {
                this.lockerPinHash = "HASH_" + pin.hashCode();
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(new PatientProfile("Arjun Iyer").getPatientId());
        System.out.println(new PatientProfile("MT2026-0142", "Arjun Iyer").getPatientId());

        PatientProfile p = new PatientProfile();
        p.setPatientId("MT2026-0142");
        p.setPatientId("HACKED-0000");
        System.out.println(p.getPatientId());
    }
}
