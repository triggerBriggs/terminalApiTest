package com.ineda.terminalApi;

import org.junit.experimental.ParallelComputer;
import org.junit.jupiter.api.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class ConcurrentByodEnquireTest {

    @Test
    public void test() {
        Class[] cls = {
                TerminalApiClientByodEnquireTest.class,
                TerminalApiClientByodEnquireTest.class,
                TerminalApiClientByodEnquireTest.class,
                TerminalApiClientByodEnquireTest.class,
                TerminalApiClientByodEnquireTest.class,
                TerminalApiClientByodEnquireTest.class,
                TerminalApiClientByodEnquireTest.class,
                TerminalApiClientByodEnquireTest.class,
                TerminalApiClientByodEnquireTest.class,
                TerminalApiClientByodEnquireTest.class,
                TerminalApiClientByodEnquireTest.class,
                TerminalApiClientByodEnquireTest.class,
                TerminalApiClientByodEnquireTest.class,
                TerminalApiClientByodEnquireTest.class,
                TerminalApiClientByodEnquireTest.class,
                TerminalApiClientByodEnquireTest.class,
                TerminalApiClientByodEnquireTest.class,
                TerminalApiClientByodEnquireTest.class,
                TerminalApiClientByodEnquireTest.class,
                TerminalApiClientByodEnquireTest.class,
                TerminalApiClientByodEnquireTest.class,
                TerminalApiClientByodEnquireTest.class,
                TerminalApiClientByodEnquireTest.class,
                TerminalApiClientByodEnquireTest.class,
                TerminalApiClientByodEnquireTest.class,
                TerminalApiClientByodEnquireTest.class
        };

        // Parallel all methods in all classes
        Result result = JUnitCore.runClasses(new ParallelComputer(true, true), cls);
        assertThat("One of the concurrent tests has failed.", result.getFailureCount(), equalTo(0));
    }
}