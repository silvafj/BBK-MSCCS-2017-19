package prototypepattern;

public class AccessControl implements Prototype {
    private String controlLevel;
    private String access;

    public AccessControl(String controlLevel) {
        this.controlLevel = controlLevel;
    }

    @Override
    public AccessControl clone() throws CloneNotSupportedException {
        AccessControl cloned = new AccessControl(controlLevel);
        cloned.setAccess(access);
        return cloned;
    }

    public String getAccess() {
        return access;

    }

    public void setAccess(String access) {
        this.access = access;

    }

    public String getControlLevel() {
        return controlLevel;
    }
}
