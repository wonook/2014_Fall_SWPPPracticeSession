package edu.snu.reef.swppreef;

import org.apache.reef.client.DriverConfiguration;
import org.apache.reef.client.DriverLauncher;
import org.apache.reef.client.LauncherStatus;
import org.apache.reef.runtime.local.client.LocalRuntimeConfiguration;
import org.apache.reef.tang.Configuration;
import org.apache.reef.tang.exceptions.InjectionException;

public final class PrimeREEF {

  private static final int JOB_TIMEOUT = 100000;

  public static void main(final String[] args) {
    final Configuration driverConfiguration = DriverConfiguration.CONF
        .set(DriverConfiguration.DRIVER_IDENTIFIER, "PrimeREEF")
        .set(DriverConfiguration.ON_DRIVER_STARTED, PrimeDriver.StartHandler.class)
        .set(DriverConfiguration.ON_EVALUATOR_ALLOCATED, PrimeDriver.AllocatedEvaluatorHandler.class)
        .set(DriverConfiguration.ON_CONTEXT_ACTIVE, PrimeDriver.ActiveContextHandler.class)
        .build();

    final Configuration localRuntimeConfiguration = LocalRuntimeConfiguration.CONF.build();

    try {
      final LauncherStatus status =
          DriverLauncher.getLauncher(localRuntimeConfiguration)
                        .run(driverConfiguration, JOB_TIMEOUT);
      System.out.println("REEF job completed: " + status.toString());
    } catch (final InjectionException e) {
      System.out.println("Injection Exception.");
    }
  }
}
