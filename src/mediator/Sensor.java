package mediator;

public class Sensor implements Colleague {
    private MachineMediator mediator;

    @Override
    public void setMediator(MachineMediator mediator) {
        this.mediator = mediator;
    }

    public boolean checkTemperature(int temp) {
        System.out.println("Temperature reached " + temp + "C");
        System.out.println("Temperature is set to " + temp);
        return true;
    }
}
