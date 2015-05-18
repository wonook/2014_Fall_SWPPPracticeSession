public class DataStore {
  private Integer data;
  private SignTracker signTracker;
  private ParityTracker parityTracker;
  private ValueTracker valueTracker;

  public DataStore(Integer data,
                   SignTracker signTracker,
                   ParityTracker parityTracker,
                   ValueTracker valueTracker) {
    this.data = data;
    this.signTracker = signTracker;
    this.parityTracker = parityTracker;
    this.valueTracker = valueTracker;
  }

  public Integer getData() {
    return data;
  }

  public void setData(Integer data) {
    if (valueChanged(this.data, data)) {
      valueTracker.valueHasChanged();
    }

    if (parityChanged(this.data, data)) {
      parityTracker.parityHasChanged();
    }

    if (signChanged(this.data, data)) {
      signTracker.signHasChanged();
    }

    this.data = data;
  }

  private static boolean valueChanged(Integer i1, Integer i2) {
    return i1 != i2;
  }

  private static boolean parityChanged(Integer i1, Integer i2) {
    return Math.abs(i1) % 2 != Math.abs(i2) % 2;
  }

  private static boolean signChanged(Integer i1, Integer i2) {
    return !(i1 * i2 > 0 && i1 + i2 != 0
            || i1 == 0 && i2 == 0);
  }
}