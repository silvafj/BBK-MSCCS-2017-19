package bridgepattern;

public class GearLocking implements Product {
  private String gearLockingSystem;

  public GearLocking(String gear_locking_system) {
    this.gearLockingSystem = gear_locking_system;
  }

  @Override
  public String productName() {
    return gearLockingSystem;
  }

  @Override
  public void produce() {
    System.out.println("Producing " + gearLockingSystem);

  }
}
