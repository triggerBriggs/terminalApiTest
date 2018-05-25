package com.ineda.terminalApi;

import org.junit.experimental.ParallelComputer;
import org.junit.jupiter.api.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import static org.junit.Assert.assertThat;

import static org.hamcrest.CoreMatchers.equalTo;

public class ConcurrentTest1 {

    @Test
    public void test() {
        Class[] cls = {
                TerminalApiClientWagerTriTest.class
        };

        // Parallel among classes
        //JUnitCore.runClasses(ParallelComputer.classes(), cls);

        //System.out.println("----------------------------");

        // Parallel among methods in a class
        //JUnitCore.runClasses(ParallelComputer.methods(), cls);

        //System.out.println("----------------------------");

        // Parallel all methods in all classes
        Result result = JUnitCore.runClasses(new ParallelComputer(true, true), cls);
        assertThat("One of the concurrent tests has failed.", result.getFailureCount(), equalTo(0));
    }
}