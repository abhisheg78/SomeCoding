package com.highspot.mixtape.takehome.executor;

import com.highspot.mixtape.takehome.data.MixTape;
import com.highspot.mixtape.takehome.operations.IOperation;

/**
 * An array of operations are executed in sequence.
 * If one fails execution fails.
 */
public class ChangeOperationsExecutor implements IOperationExecutor {

  public void execute(final MixTape mixTape,
                      final IOperation[] operationList) throws Exception {
    for (IOperation operation : operationList) {
      operation.execute(mixTape);
    }
  }
}
