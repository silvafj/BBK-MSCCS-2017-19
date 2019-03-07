package mediator;

public class Heater implements Colleague {
    private MachineMediator mediator;

    @Override
    public void setMediator(MachineMediator mediator) {
        this.mediator = mediator;
    }

    public void on(int i) {
        System.out.println("Heater is on...");
        if (mediator.checkTemperature(i)) {
            mediator.off();
        }
    }

    public void off() {
        System.out.println("Heater is off...");
        mediator.wash();
    }
}
