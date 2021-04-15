package com.highspot.mixtape.takehome.executor;

import com.highspot.mixtape.takehome.data.MixTape;
import com.highspot.mixtape.takehome.operations.IOperation;

/**
 * process operations on MixTape file.
 */
public interface IOperationExecutor {
  /**
   * execute operations with MixTape file.
   * @param mixTape the content of MixTape.json
   * @param operations action to be execute on MixTape file.
   * @throws Exception exceptions
   */
  void execute(
    final MixTape mixTape,
    final IOperation[] operations) throws Exception;
}
