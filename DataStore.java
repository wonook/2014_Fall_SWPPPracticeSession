import java.util.LinkedList;
import java.util.List;

public class DataStore {
  private Integer data;
  private List<Tracker> signTrackerList;
  private List<Tracker> parityTrackerList;
  private List<Tracker> valueTrackerList;

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
      for (Tracker signTracker : signTrackerList) {
        signTracker.inform();
      }
    }

    if (parityChanged(this.data, data)) {
      for (Tracker parityTracker : parityTrackerList) {
        parityTracker.inform();
      }
    }

    if (valueChanged(this.data, data)) {
      for (Tracker valueTracker : valueTrackerList) {
        valueTracker.inform();
      }
    }

    this.data = data;
  }

  public void addSignTracker(Tracker signTracker) {
    signTrackerList.add(signTracker);
  }

  public void addParityTracker(Tracker parityTracker) {
    parityTrackerList.add(parityTracker);
  }

  public void addValueTracker(Tracker valueTracker) {
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