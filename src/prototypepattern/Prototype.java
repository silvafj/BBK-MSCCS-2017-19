package prototypepattern;

public interface Prototype extends Cloneable {
  public AccessControl clone() throws CloneNotSupportedException;
}