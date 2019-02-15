package memento;

public class Originator {
  private double x;
  private double y;

  private String lastUndoSavepoint;
  CareTaker careTaker;

  public Originator(double x, double y, CareTaker careTaker) {
    // TODO
    createSavepoint("INITIAL");
  }

  public double getX() {
    return x;
  }

  public double getY() {
    return y;
  }

  public void setX(double x) {
  }

  public void setY(double y) {
  }

  public void createSavepoint(String savepointName) {
  }

  public void undo() {
  }

  public void undo(String savepointName) {
  }

  public void undoAll() {
  }

  private void setOriginatorState(String savepointName) {
  }

  @Override
  public String toString() {
    return "X: " + x + ", Y: " + y;
  }
}