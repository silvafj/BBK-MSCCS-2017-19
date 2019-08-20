package proxy.protection;

public class Owner implements Staff {
  private boolean isOwner;
  private ReportGeneratorProxy reportGenerator;

  {
    isOwner = true;
  }

  @Override
  public void setReportGenerator(ReportGeneratorProxy reportGenerator) {
    this.reportGenerator = reportGenerator;
  }

  @Override
  public boolean isOwner() {
    return isOwner;
  }

  public String generateDailyReport() {
    try {
      return reportGenerator.generateDailyReport();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "";
  }
}