package com.indea.microtote;

import org.junit.experimental.ParallelComputer;
import org.junit.jupiter.api.Test;
import org.junit.runner.JUnitCore;

public class ConcurrentPingTest {

    @Test
    public void test() {
        Class[] cls = { MicrototeClientPingTest.class,
                MicrototeClientPingTest.class,
                MicrototeClientPingTest.class,
                MicrototeClientPingTest.class,
                MicrototeClientPingTest.class,
                MicrototeClientPingTest.class,
                MicrototeClientPingTest.class,
                MicrototeClientPingTest.class,
                MicrototeClientPingTest.class,
                MicrototeClientPingTest.class,
                MicrototeClientPingTest.class,
                MicrototeClientPingTest.class,
                MicrototeClientPingTest.class,
                MicrototeClientPingTest.class,
                MicrototeClientPingTest.class,
                MicrototeClientPingTest.class,
                MicrototeClientPingTest.class,
                MicrototeClientPingTest.class,
                MicrototeClientPingTest.class,
                MicrototeClientPingTest.class,
                MicrototeClientPingTest.class,
                MicrototeClientPingTest.class,
                MicrototeClientPingTest.class,
                MicrototeClientPingTest.class,
                MicrototeClientPingTest.class,
                MicrototeClientPingTest.class,
                MicrototeClientPingTest.class,
                MicrototeClientPingTest.class,
                MicrototeClientPingTest.class,
                MicrototeClientPingTest.class,
                MicrototeClientPingTest.class,
                MicrototeClientPingTest.class,
                MicrototeClientPingTest.class,
                MicrototeClientPingTest.class,
                MicrototeClientPingTest.class,
                MicrototeClientPingTest.class,
                MicrototeClientPingTest.class,
                MicrototeClientPingTest.class,
                MicrototeClientPingTest.class,
                MicrototeClientPingTest.class,
                MicrototeClientPingTest.class,
                MicrototeClientPingTest.class,
                MicrototeClientPingTest.class,
                MicrototeClientPingTest.class,
                MicrototeClientPingTest.class,
                MicrototeClientPingTest.class,
                MicrototeClientPingTest.class,
                MicrototeClientPingTest.class,
                MicrototeClientPingTest.class,
                MicrototeClientPingTest.class,
                MicrototeClientPingTest.class,
                MicrototeClientPingTest.class,
                MicrototeClientPingTest.class,
                MicrototeClientPingTest.class,
                MicrototeClientPingTest.class,
                MicrototeClientPingTest.class,
                MicrototeClientPingTest.class,
                MicrototeClientPingTest.class,
                MicrototeClientPingTest.class,
                MicrototeClientPingTest.class,
                MicrototeClientPingTest.class,
                MicrototeClientPingTest.class,
                MicrototeClientPingTest.class,
                MicrototeClientPingTest.class,
                MicrototeClientPingTest.class
        };

        // Parallel among classes
        JUnitCore.runClasses(ParallelComputer.classes(), cls);

        System.out.println("----------------------------");

        // Parallel among methods in a class
        //JUnitCore.runClasses(ParallelComputer.methods(), cls);

        System.out.println("----------------------------");

        // Parallel all methods in all classes
        JUnitCore.runClasses(new ParallelComputer(true, true), cls);
    }
}