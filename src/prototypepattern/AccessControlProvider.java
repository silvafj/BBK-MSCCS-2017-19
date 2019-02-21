package prototypepattern;

import java.util.HashMap;

public class AccessControlProvider {

    private static HashMap<String, AccessControl> accessControlMap = new HashMap<>();

    static {
        System.out.println("Fetching data from external resources and creating access control objects...");
        accessControlMap.put("ADMIN", new AccessControl("ADMIN"));

        AccessControl managerUserAccess = new AccessControl("MANAGER");
        managerUserAccess.setAccess("GENERATE/READ REPORTS");
        accessControlMap.put("MANAGER", managerUserAccess);

        AccessControl userAccessControl = new AccessControl("USER");
        userAccessControl.setAccess("DO_WORK");
        accessControlMap.put("USER", userAccessControl);
    }

    public static AccessControl getAccessControlObject(String controlLevel) {
        try {
            return accessControlMap.get(controlLevel).clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }
}
