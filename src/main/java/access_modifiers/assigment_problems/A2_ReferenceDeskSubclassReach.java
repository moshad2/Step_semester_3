package access_modifiers.assigment_problems;

public class A2_ReferenceDeskSubclassReach {

    public static String classifyAccess(String fieldModifier, String accessorContext) {
        if (fieldModifier == null || accessorContext == null) return "DENIED";

        if ("public".equals(fieldModifier)) return "ALLOWED";
        if ("private".equals(fieldModifier)) {
            return "SAME_CLASS".equals(accessorContext) ? "ALLOWED" : "DENIED";
        }

        if ("default".equals(fieldModifier)) {
            return ("SAME_CLASS".equals(accessorContext) || "SAME_PACKAGE".equals(accessorContext)) ? "ALLOWED" : "DENIED";
        }

        if ("protected".equals(fieldModifier)) {
            switch (accessorContext) {
                case "SAME_CLASS":
                case "SAME_PACKAGE":
                case "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE":
                    return "ALLOWED";
                case "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE":
                case "DIFFERENT_PACKAGE":
                default:
                    return "DENIED";
            }
        }

        return "DENIED";
    }

    public static String describeContext(String accessorContext) {
        if (accessorContext == null || accessorContext.isEmpty()) return "";

        String[] words = accessorContext.split("_");
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < words.length; i++) {
            String w = words[i].toLowerCase();
            if (!w.isEmpty()) {
                sb.append(Character.toUpperCase(w.charAt(0))).append(w.substring(1));
                if (i < words.length - 1) {
                    sb.append(" ");
                }
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(classifyAccess("protected", "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"));
        System.out.println(classifyAccess("protected", "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"));
        System.out.println(describeContext("SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"));
    }
}
