package com.ineda.terminalApi;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TerminalApiClientNoAuthPingTest.class, TerminalApiClientPingTest.class })
public class ConsecutiveTest { }
