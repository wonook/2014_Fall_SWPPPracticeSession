package edu.snu.reef.swppreef;

import org.apache.reef.driver.context.ActiveContext;
import org.apache.reef.driver.context.ContextConfiguration;
import org.apache.reef.driver.evaluator.AllocatedEvaluator;
import org.apache.reef.driver.evaluator.EvaluatorRequest;
import org.apache.reef.driver.evaluator.EvaluatorRequestor;
import org.apache.reef.driver.task.TaskConfiguration;
import org.apache.reef.tang.Configuration;
import org.apache.reef.tang.annotations.Unit;
import org.apache.reef.wake.EventHandler;
import org.apache.reef.wake.time.event.StartTime;

import javax.inject.Inject;
import java.util.concurrent.atomic.AtomicInteger;

@Unit
public final class PrimeDriver {

  private final EvaluatorRequestor requestor;
  private final AtomicInteger contextIds;

  @Inject
  public PrimeDriver(final EvaluatorRequestor requestor) {
    this.requestor = requestor;
    this.contextIds = new AtomicInteger(0);
  }

  final class StartHandler implements EventHandler<StartTime> {
    @Override
    public final void onNext(final StartTime startTime) {
      requestor.submit(EvaluatorRequest.newBuilder()
          .setNumber(3)
          .setMemory(64)
          .setNumberOfCores(1)
          .build());
    }
  }

  final class AllocatedEvaluatorHandler implements EventHandler<AllocatedEvaluator> {
    @Override
    public final void onNext(final AllocatedEvaluator allocatedEvaluator) {
      final String contextId = "PrimeContext-" + contextIds.getAndIncrement();
      final Configuration contextConfiguration = ContextConfiguration.CONF
          .set(ContextConfiguration.IDENTIFIER, contextId)
          .build();
      allocatedEvaluator.submitContext(contextConfiguration);
    }
  }

  final class ActiveContextHandler implements EventHandler<ActiveContext> {
    @Override
    public final void onNext(final ActiveContext activeContext) {
      final String contextId = activeContext.getId();
      final String taskIndex = contextId.substring("PrimeContext-".length());
      final String taskId = "PrimeTask-" + taskIndex;
      final Configuration taskConfiguration = TaskConfiguration.CONF
          .set(TaskConfiguration.IDENTIFIER, taskId)
          .build();
      activeContext.submitTask(taskConfiguration);
    }
  }
}
