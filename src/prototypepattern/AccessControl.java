package prototypepattern;

public class AccessControl implements Prototype {
  @Override
  public AccessControl clone() throws CloneNotSupportedException {
    return null;
  }

  public void setAccess(String access) {
  }
}
