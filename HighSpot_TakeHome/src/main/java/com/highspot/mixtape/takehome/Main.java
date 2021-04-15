package com.highspot.mixtape.takehome;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.highspot.mixtape.takehome.executor.ChangeOperationsExecutor;

import picocli.CommandLine;

public class Main {

    public static void main(String[] args) {
	// write your code here
        final ChangeManager changeManager = new ChangeManager(new ObjectMapper(),
                new ChangeOperationsExecutor());

        // basic argument check
        if(args.length == 0) {
           System.out.println("Process expects certain arguments \n. Please use -h or --help to get yourself familiar with the usage.");
           return;
        }

        final CommandLine commandLine = new CommandLine(changeManager);
        System.exit(commandLine.execute(args));
    }
}
