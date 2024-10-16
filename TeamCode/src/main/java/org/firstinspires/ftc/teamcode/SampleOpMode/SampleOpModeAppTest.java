package org.firstinspires.ftc.teamcode;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

public class SampleOpModeAppTest {

    private TelemetryStrategy telemetryStrategy;
    private SampleOpModeApp sampleOpModeApp;

    @Before
    public void setUp() {
        telemetryStrategy = mock(TelemetryStrategy.class);
        sampleOpModeApp = new SampleOpModeApp("1234", telemetryStrategy);
    }

    @Test
    public void testInitLogsBuildNumber() {
        sampleOpModeApp.init();
        // Verify that the log method was called passing in the build number
        verify(telemetryStrategy).log("Build Number", "1234");
    }
}