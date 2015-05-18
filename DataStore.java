import java.util.LinkedList;
import java.util.List;

public class DataStore {
  private Integer data;
  private List<SignTracker> signTrackerList;
  private List<ParityTracker> parityTrackerList;
  private List<ValueTracker> valueTrackerList;

  public DataStore(Integer data) {
    this.data = data;
    this.signTrackerList = new LinkedList<>();
    this.parityTrackerList = new LinkedList<>();
    this.valueTrackerList = new LinkedList<>();
  }

  public Integer getData() {
    return data;
  }

  public void setData(Integer data) {
    if (signChanged(this.data, data)) {
      for (SignTracker signTracker : signTrackerList) {
        signTracker.signHasChanged();
      }
    }

    if (parityChanged(this.data, data)) {
      for (ParityTracker parityTracker : parityTrackerList) {
        parityTracker.parityHasChanged();
      }
    }

    if (valueChanged(this.data, data)) {
      for (ValueTracker valueTracker : valueTrackerList) {
        valueTracker.valueHasChanged();
      }
    }

    this.data = data;
  }

  public void addSignTracker(SignTracker signTracker) {
    signTrackerList.add(signTracker);
  }

  public void addParityTracker(ParityTracker parityTracker) {
    parityTrackerList.add(parityTracker);
  }

  public void addValueTracker(ValueTracker valueTracker) {
    valueTrackerList.add(valueTracker);
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