package mediator;

public class SoilRemoval implements Colleague {
    private MachineMediator mediator;

    @Override
    public void setMediator(MachineMediator mediator) {
        this.mediator = mediator;
    }

    public void low() {
        System.out.println("Setting Soil Removal to low");
    }

    public void medium() {
        System.out.println("Setting Soil Removal to medium");
    }
}
