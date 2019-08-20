package facadepattern;

public class ScheduleServerFacadeImpl implements ScheduleServerFacade {
  private final ScheduleServer scheduleServer;

  public ScheduleServerFacadeImpl(ScheduleServer scheduleServer) {
    this.scheduleServer = scheduleServer;
  }

  @Override
  public void startServer() {
    scheduleServer.startBooting();
    scheduleServer.readSystemConfigFile();
    scheduleServer.init();
    scheduleServer.initializeContext();
    scheduleServer.initializeListeners();
    scheduleServer.createSystemObjects();
  }

  @Override
  public void stopServer() {
    scheduleServer.releaseProcesses();
    scheduleServer.destory();
    scheduleServer.destroySystemObjects();
    scheduleServer.destoryListeners();
    scheduleServer.destoryContext();
    scheduleServer.shutdown();
  }
}
