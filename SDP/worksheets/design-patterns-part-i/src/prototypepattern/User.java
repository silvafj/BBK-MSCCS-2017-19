package prototypepattern;

public class User {
    private String userName;
    private String userLevel;
    private AccessControl userAccessControl;


    public User(String user_a, String user_level, AccessControl userAccessControl) {
        this.userName = user_a;
        this.userLevel = user_level;
        this.userAccessControl = userAccessControl;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLevel() {
        return userLevel;
    }

    public void setLevel(String level) {
        this.userLevel = userLevel;
    }

    public AccessControl getAccessControl() {
        return userAccessControl;
    }

    public void setAccessControl(AccessControl accessControl) {
        this.userAccessControl = accessControl;

    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(getUserName());
        builder.append(", Level: " + getLevel());
        builder.append(", Access Control Level: " + getAccessControl().getControlLevel());
        builder.append(", Access: " + getAccessControl().getAccess());
        return builder.toString();
    }
}