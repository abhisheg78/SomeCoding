package com.highspot.mixtape.takehome;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.highspot.mixtape.takehome.data.MixTape;
import com.highspot.mixtape.takehome.executor.ChangeOperationsExecutor;
import com.highspot.mixtape.takehome.executor.IOperationExecutor;
import com.highspot.mixtape.takehome.operations.IOperation;

import picocli.CommandLine;

import java.io.File;
import java.util.concurrent.Callable;

/**
 * the executor of file ingest, it ingests mixtape.json with change file,
 * and stores the data to the output file.
 */
@CommandLine.Command(
  name = "ingest",
  mixinStandardHelpOptions = true,
  version = "1.0",
  description = "ingest is a commandline tool which is used to"
                  + " ingest a mixtage.json with a change file",
  subcommands = CommandLine.HelpCommand.class
)
public class ChangeManager  implements Callable<Integer>{
  @CommandLine.Option(names = {"-i", "--input"},
    description = "Input file path for mixtape.json")
  private File inputFile;

  @CommandLine.Option(names = {"-c", "--change"},
    description = "File path for the change file ")
  private File changeFile;

  @CommandLine.Option(names = {"-o", "--output"},
    description = "Output file path")

  private File outputFile;

  private final ObjectMapper objectMapper;
  private final IOperationExecutor operationExecutor;

  public ChangeManager(final ObjectMapper objectMapper, final ChangeOperationsExecutor changeOperationsExecutor) {
    this.objectMapper = objectMapper;
    this.operationExecutor = changeOperationsExecutor;
  }

  /**
   * read the data from mixtape.json and change file,
   * execute file ingestion and write data to the output file.
   * @return integer
   *  0 : success
   *  1 : error
   */
  @Override
  public Integer call() {
    try {
      final MixTape mixTape = loadMixTapeFile(inputFile);
      final IOperation[] operationList = loadActionFile(changeFile);

      operationExecutor.execute(mixTape, operationList);
      saveResult(outputFile, mixTape);
      System.out.println(String.format("succeeded ingesting %s with %s",
                             inputFile.getName(), changeFile.getName()));
      return 0;
    } catch (Exception e) {
      System.out.println(String.format("Failed to ingest %s with %s, reason: %s",
                              inputFile.getName(), changeFile.getName(), e.getMessage()));
      return 1;
    }
  }

  private MixTape loadMixTapeFile(final File input) throws Exception {
    try {
      return objectMapper.readValue(input, MixTape.class);
    } catch (Exception e) {
      final String errorMessage = String.format("loading mixtape file failed, error:%s",
                                                e.getMessage());
      throw new Exception(errorMessage, e);
    }
  }

  private IOperation[] loadActionFile(final File changes) throws Exception {
    try {
      return objectMapper.readValue(changes, IOperation[].class);
    } catch (Exception e) {
      final String errorMessage = String.format("loading changes file failed, error:%s",
                                                e.getMessage());
      throw new Exception(errorMessage, e);
    }
  }

  private void saveResult(final File output, final MixTape mixTape) throws Exception {
    try {
      objectMapper.writerWithDefaultPrettyPrinter()
                  .writeValue(output, mixTape);
    } catch (Exception e) {
      final String errorMessage = String.format("write output file failed, error:%s",
                                                e.getMessage());
      throw new Exception(errorMessage, e);
    }
  }
}
