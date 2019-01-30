package facadepattern;

interface ScheduleServer {
  public void startBooting();

  public void readSystemConfigFile();

  public void init();

  public void initializeContext();

  public void initializeListeners();

  public void createSystemObjects();

  public void releaseProcesses();

  public void destory();

  public void destroySystemObjects();

  public void destoryListeners();

  public void destoryContext();

  public void shutdown();
}
