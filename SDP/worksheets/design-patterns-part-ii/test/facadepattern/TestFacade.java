package facadepattern;

public class TestFacade {
  public static void main(String[] args) {
    ScheduleServer scheduleServer = new ScheduleServerImpl();
    ScheduleServerFacade facadeServer = new ScheduleServerFacadeImpl(scheduleServer);
    facadeServer.startServer();

    System.out.println("Start working......");
    System.out.println("After work done.........");

    facadeServer.stopServer();
  }
}
