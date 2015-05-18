public class Main {
  public static void main(String[] args) {
    SignTracker signTracker = new SignTracker();
    ParityTracker parityTracker = new ParityTracker();
    ValueTracker valueTracker = new ValueTracker();
    DataStore dataStore =
        new DataStore(1, signTracker, parityTracker, valueTracker);

    dataStore.setData(2);
    System.out.println();

    dataStore.setData(-2);
    System.out.println();

    dataStore.setData(1);
    System.out.println();
  }
}