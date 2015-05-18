public class Main {
  public static void main(String[] args) {
    SignTracker signTracker = new SignTracker();
    ParityTracker parityTracker = new ParityTracker();
    ValueTracker valueTracker1 = new ValueTracker();
    ValueTracker valueTracker2 = new ValueTracker();

    DataStore dataStore = new DataStore(1);
    dataStore.addSignTracker(signTracker);
    dataStore.addParityTracker(parityTracker);
    dataStore.addValueTracker(valueTracker1);
    dataStore.addValueTracker(valueTracker2);

    dataStore.setData(2);
    System.out.println();

    dataStore.setData(-2);
    System.out.println();

    dataStore.setData(1);
    System.out.println();
  }
}