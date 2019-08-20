package observer;

public class SMSUsers implements Observer {
    private final Subject subject;
    private final String s;

    public SMSUsers(Subject subject, String s) {
        this.subject = subject;
        this.s = s;
    }

    @Override
    public void update(String desc) {
        System.out.println(s + ": " + desc);
    }

    @Override
    public void subscribe() {
        System.out.println("Subscribing " + s + " to " + subject.subjectDetails());
        subject.subscribeObserver(this);
        System.out.println("Subscribed successfully.");
    }

    @Override
    public void unSubscribe() {
        System.out.println("Unsubscribing " + s + " to " + subject.subjectDetails());
        subject.unSubscribeObserver(this);
        System.out.println("Unsubscribed successfully.");
    }
}
