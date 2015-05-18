import java.lang.Override;

public class ValueMonitorWrapper extends ValueTracker {
  private ValueMonitor valueMonitor;

  public ValueMonitorWrapper() {
    this.valueMonitor = new ValueMonitor();
  }

  @Override
  public void valueHasChanged() {
    valueMonitor.printValueChange();
  }
}