package memento;

public class Originator {
  private double x;
  private double y;

  private String lastUndoSavepoint;
  CareTaker careTaker;

  public Originator(double x, double y, CareTaker careTaker) {
    this.careTaker = new CareTaker();
    this.setX(x);
    this.setY(y);
    createSavepoint("INITIAL");
  }

  public double getX() {
    return x;
  }

  public double getY() {
    return y;
  }

  public void setX(double x) {
    this.x = x;
  }

  public void setY(double y) {
    this.y = y;
  }

  public void createSavepoint(String savepointName) {
    careTaker.saveMemento(new Memento(this.getX(), this.getY()), savepointName);
    this.lastUndoSavepoint = savepointName;
  }

  public void undo() {
    this.undo(this.lastUndoSavepoint);
  }

  public void undo(String savepointName) {
    this.setOriginatorState(savepointName);
  }

  public void undoAll() {
    this.undo("INITIAL");
    careTaker.clearSavepoints();
  }

  private void setOriginatorState(String savepointName) {
    Memento memento = careTaker.getMemento(savepointName);
    this.setX(memento.getX());
    this.setY(memento.getY());
  }

  @Override
  public String toString() {
    return "X: " + x + ", Y: " + y;
  }
}