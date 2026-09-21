package constructors_keywords.assigment_problems;

import java.util.Arrays;

public class A3_CanteenRankingEngine {

    public static class Canteen implements Comparable<Canteen> {
        private String canteenCode;
        private String canteenName;
        private int trustScore;

        // Primary constructor with 'this' field/parameter clash resolution
        public Canteen(String canteenCode, String canteenName, int trustScore) {
            this.canteenCode = canteenCode;
            this.canteenName = canteenName;
            this.trustScore = trustScore;
        }

        // Chained constructor with default trust score = 3
        public Canteen(String canteenCode, String canteenName) {
            this(canteenCode, canteenName, 3);
        }

        public String getCanteenCode() {
            return canteenCode;
        }

        @Override
        public int compareTo(Canteen other) {
            // Rank Rule 1: Higher trust score ranks first (descending)
            if (this.trustScore != other.trustScore) {
                return Integer.compare(other.trustScore, this.trustScore);
            }
            // Tie-break 1: Compare canteen codes case-insensitively (ascending)
            int codeCompare = this.canteenCode.compareToIgnoreCase(other.canteenCode);
            if (codeCompare != 0) {
                return codeCompare;
            }
            // Tie-break 2: Compare canteen names by length (ascending)
            return Integer.compare(this.canteenName.length(), other.canteenName.length());
        }
    }

    public static Canteen[] rankCanteens(Canteen[] canteens) {
        if (canteens == null || canteens.length <= 1) return canteens;

        Canteen[] sorted = canteens.clone();
        // Stable insertion sort implementation (O(n^2))
        for (int i = 1; i < sorted.length; i++) {
            Canteen key = sorted[i];
            int j = i - 1;
            while (j >= 0 && sorted[j].compareTo(key) > 0) {
                sorted[j + 1] = sorted[j];
                j--;
            }
            sorted[j + 1] = key;
        }
        return sorted;
    }

    public static void main(String[] args) {
        Canteen[] canteens = {
            new Canteen("HB3-C", "Spice Junction", 3),
            new Canteen("hb1-c", "Grand Mess", 5),
            new Canteen("HB2-C", "Southern Treats")
        };

        Canteen[] ranked = rankCanteens(canteens);
        String[] codes = new String[ranked.length];
        for (int i = 0; i < ranked.length; i++) {
            codes[i] = ranked[i].getCanteenCode();
        }
        System.out.println(Arrays.toString(codes));
    }
}
