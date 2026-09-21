package access_modifiers.assigment_problems;

public class A4_LibraryMemberJavaBean {

    public static class LibraryMember {
        private String membershipId;
        private String name;
        private boolean premiumMember;
        private String securityAnswerHash;

        // Constructor 1: JavaBean requirement
        public LibraryMember() {
            this(null, null);
        }

        // Constructor 2: Name-only
        public LibraryMember(String name) {
            this(null, name);
        }

        // Constructor 3: Full id + name
        public LibraryMember(String membershipId, String name) {
            this.membershipId = membershipId;
            this.name = name;
            this.premiumMember = false;
            this.securityAnswerHash = null;
        }

        public String getMembershipId() {
            return membershipId;
        }

        // Write-once setter
        public void setMembershipId(String id) {
            if (this.membershipId == null) {
                this.membershipId = id;
            }
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean isPremiumMember() {
            return premiumMember;
        }

        public void setPremiumMember(boolean premiumMember) {
            this.premiumMember = premiumMember;
        }

        // Write-only property: No getter exists anywhere
        public void setSecurityAnswer(String answer) {
            if (answer != null) {
                this.securityAnswerHash = "HASH_" + answer.hashCode();
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(new LibraryMember("Priya Nair").getMembershipId());
        System.out.println(new LibraryMember("LIB-8841", "Priya Nair").getMembershipId());

        LibraryMember m = new LibraryMember();
        m.setMembershipId("LIB-8841");
        m.setMembershipId("FAKE-0000");
        System.out.println(m.getMembershipId());
    }
}
