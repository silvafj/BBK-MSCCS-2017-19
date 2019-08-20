package observer;

import java.util.List;

public class CommentaryObject implements Subject, Commentary {
    private final List<Observer> observers;
    private final String s;
    private String desc;

    public CommentaryObject(List<Observer> observers, String s) {
        this.observers = observers;
        this.s = s;
    }

    @Override
    public void setDesc(String desc) {
        this.desc = desc;
        notifyObservers();
    }

    @Override
    public void subscribeObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void unSubscribeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(desc);
        }
    }

    @Override
    public String subjectDetails() {
        return this.s;
    }
}
