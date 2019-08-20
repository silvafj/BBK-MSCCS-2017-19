package bridgepattern;

public class CentralLocking implements Product {
  private String centralLockingSystem;
  public CentralLocking(String central_locking_system) {
    this.centralLockingSystem = central_locking_system;
  }

  @Override
  public String productName() {
    return centralLockingSystem;
  }

  @Override
  public void produce() {
    System.out.println("Producing " + centralLockingSystem);
  }
}
