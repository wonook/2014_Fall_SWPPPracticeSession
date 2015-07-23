package edu.snu.reef.swppreef;

import javax.inject.Inject;
import java.util.Random;

public final class RandomGenerator {

  private final Random random;

  @Inject
  public RandomGenerator() {
    this.random = new Random();
  }

  public int getRandom() {
    return random.nextInt(1000000000);
  }
}
