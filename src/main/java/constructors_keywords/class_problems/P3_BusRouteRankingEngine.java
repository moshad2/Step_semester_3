package constructors_keywords.class_problems;

import java.util.Arrays;

public class P3_BusRouteRankingEngine {

    public static class BusRoute implements Comparable<BusRoute> {
        private String routeCode;
        private String routeName;
        private int priority;

        // Primary constructor using 'this' to resolve naming collisions
        public BusRoute(String routeCode, String routeName, int priority) {
            this.routeCode = routeCode;
            this.routeName = routeName;
            this.priority = priority;
        }

        // Chained constructor with default priority = 5
        public BusRoute(String routeCode, String routeName) {
            this(routeCode, routeName, 5);
        }

        public String getRouteCode() {
            return routeCode;
        }

        @Override
        public int compareTo(BusRoute other) {
            // Priority ordering: lower numeric value = higher priority dispatch
            if (this.priority != other.priority) {
                return Integer.compare(this.priority, other.priority);
            }
            // Tie-break 1: Compare route codes case-insensitively
            int codeCompare = this.routeCode.compareToIgnoreCase(other.routeCode);
            if (codeCompare != 0) {
                return codeCompare;
            }
            // Tie-break 2: Compare route names case-insensitively
            return this.routeName.compareToIgnoreCase(other.routeName);
        }
    }

    public static BusRoute[] rankRoutes(BusRoute[] routes) {
        if (routes == null || routes.length <= 1) return routes;

        BusRoute[] sorted = routes.clone();
        // Stable insertion sort implementation (O(n^2))
        for (int i = 1; i < sorted.length; i++) {
            BusRoute key = sorted[i];
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
        BusRoute[] routes = {
            new BusRoute("RT205L", "Airport Express", 3),
            new BusRoute("rt201j", "City Central", 4),
            new BusRoute("RT299T", "Night Service")
        };

        BusRoute[] ranked = rankRoutes(routes);
        String[] codes = new String[ranked.length];
        for (int i = 0; i < ranked.length; i++) {
            codes[i] = ranked[i].getRouteCode();
        }
        System.out.println(Arrays.toString(codes));
    }
}
