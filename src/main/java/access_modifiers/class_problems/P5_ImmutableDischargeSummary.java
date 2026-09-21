package access_modifiers.class_problems;

import java.util.Arrays;

public class P5_ImmutableDischargeSummary {

    public static final class DischargeSummary {
        private final String patientId;
        private final String[] medicationCodes;

        public DischargeSummary(String patientId, String[] medicationCodes) {
            if (patientId == null || medicationCodes == null) {
                throw new IllegalArgumentException("Inputs cannot be null");
            }

            for (String code : medicationCodes) {
                if (code == null || !code.matches("^MED-[A-Z]$")) {
                    throw new IllegalArgumentException("Invalid medication code format: " + code);
                }
            }

            this.patientId = patientId;
            // Defensive copy on intake
            this.medicationCodes = medicationCodes.clone();
        }

        public String getPatientId() {
            return patientId;
        }

        public String[] getMedicationCodes() {
            // Defensive copy on output
            return medicationCodes.clone();
        }

        public DischargeSummary withCorrectedMedication(int index, String newCode) {
            if (index < 0 || index >= medicationCodes.length) {
                throw new IndexOutOfBoundsException("Invalid index");
            }
            String[] newCodes = medicationCodes.clone();
            newCodes[index] = newCode;
            return new DischargeSummary(this.patientId, newCodes);
        }
    }

    public static class CriticalCareDischargeSummary extends DischargeSummary {
        private final int icuDays;

        public CriticalCareDischargeSummary(String patientId, String[] medicationCodes, int icuDays) {
            super(patientId, medicationCodes);
            this.icuDays = icuDays;
        }

        public int getIcuDays() {
            return icuDays;
        }
    }

    public static String processNightlyBatch(DischargeSummary[] summaries) {
        int processed = 0;
        int nullSkipped = 0;
        int criticalCare = 0;
        int routine = 0;

        if (summaries != null) {
            for (DischargeSummary s : summaries) {
                if (s == null) {
                    nullSkipped++;
                    continue;
                }
                processed++;
                if (s instanceof CriticalCareDischargeSummary) {
                    criticalCare++;
                } else {
                    routine++;
                }
            }
        }

        return processed + " processed | " + nullSkipped + " null skipped | " 
            + criticalCare + " critical-care | " + routine + " routine";
    }

    public static void main(String[] args) {
        try {
            new DischargeSummary("MT2026-0142", new String[]{"MED-A", "bad"});
        } catch (IllegalArgumentException e) {
            System.out.println("construction rejected");
        }

        DischargeSummary d = new DischargeSummary("MT2026-0142", new String[]{"MED-A", "MED-B"});
        String[] codes = d.getMedicationCodes();
        codes[0] = "TAMPERED";
        System.out.println(d.getMedicationCodes()[0]);

        DischargeSummary[] batch = {
            new CriticalCareDischargeSummary("MT001", new String[]{"MED-X"}, 4),
            null,
            new DischargeSummary("MT002", new String[]{"MED-Y"})
        };
        System.out.println(processNightlyBatch(batch));
    }
}
