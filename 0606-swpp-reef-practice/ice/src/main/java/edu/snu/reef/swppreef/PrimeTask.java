package edu.snu.reef.swppreef;

import org.apache.reef.task.Task;

import javax.inject.Inject;

public final class PrimeTask implements Task {

  private int n;

  @Inject
  public PrimeTask() {
    this.n = 450619297;
  }

  @Override
  public final byte[] call(final byte[] memento) {
    System.out.println(n + " is prime: " + isPrime(n));
    return null;
  }

  private final boolean isPrime(int n) {
    if (n == 1) {
      return false;
    }

    for (int i = 2; i < Math.sqrt(n); i++) {
      if (n % i == 0) {
        return false;
      }
    }

    return true;
  }

}