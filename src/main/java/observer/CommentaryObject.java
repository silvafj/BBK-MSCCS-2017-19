package observer;

import java.util.List;

public class CommentaryObject implements Subject, Commentary {
  public CommentaryObject(List<Observer> observers, String s) {
  }

  @Override
  public void setDesc(String desc) {

  }

  @Override
  public void subscribeObserver(Observer observer) {

  }

  @Override
  public void unSubscribeObserver(Observer observer) {

  }

  @Override
  public void notifyObservers() {

  }

  @Override
  public String subjectDetails() {
    return null;
  }
}
