import java.lang.Override;

public class ValueMonitorWrapper implements Tracker {
  private ValueMonitor valueMonitor;

  public ValueMonitorWrapper() {
    this.valueMonitor = new ValueMonitor();
  }

  public void inform() {
    valueMonitor.printValueChange();
  }
}