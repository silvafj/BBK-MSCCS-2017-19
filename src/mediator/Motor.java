package mediator;

public class Motor implements Colleague {
    private MachineMediator mediator;

    @Override
    public void setMediator(MachineMediator mediator) {
        this.mediator = mediator;
    }

    public void startMotor() {
        System.out.println("Start motor...");
    }

    public void rotateDrum(int i) {
        System.out.println("Rotating drum at " + i + " rpm");
    }
}
