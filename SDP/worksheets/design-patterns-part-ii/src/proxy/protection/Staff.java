package proxy.protection;

public interface Staff {
  public boolean isOwner();

  public void setReportGenerator(ReportGeneratorProxy reportGenerator);
}
