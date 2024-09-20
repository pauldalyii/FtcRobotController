package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name = "Sample")
@TeleOp(name = "Sample")
public abstract class BaseOpMode extends LinearOpMode {

    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() {

        initializeHardware();
        waitForStart();
        runtime.reset();
        
        int durationOfAutonomousPeriodInSeconds = 30;
        while (opModeIsActive() && runtime.seconds() < durationOfAutonomousPeriodInSeconds) {
            telemetry.addData("OpMode:", "LinearOp");
            executeLinearOp();
            telemetry.update();
        }

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        while (opModeIsActive()) {
            telemetry.addData("OpMode:", "TeleOp");
            executeTeleOp();
            telemetry.update();
        }
    }

    protected abstract void initializeHardware();

    protected abstract void executeLinearOp();  //Autonomous

    protected abstract void executeTeleOp();
}