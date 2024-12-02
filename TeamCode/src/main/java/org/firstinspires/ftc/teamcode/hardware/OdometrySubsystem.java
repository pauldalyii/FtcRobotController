package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.hardware.HardwareMap;
import java.util.Locale;
import org.firstinspires.ftc.robotcore.external.navigation.*;

public class OdometrySubsystem extends Subsystem implements Loop {
    private RobotHardware robot;
    GoBildaPinpointDriver odometry;

    double oldTime = 0;

    public OdometrySubsystem(RobotHardware robot) {
        this.robot = robot;
    }
    
    public void init() {
        odometry = robot.hardwareMap.get(GoBildaPinpointDriver.class,"odo");
        odometry.setOffsets(-50.8, -101.6);
        odometry.setEncoderResolution(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD);
        odometry.setEncoderDirections(GoBildaPinpointDriver.EncoderDirection.FORWARD, GoBildaPinpointDriver.EncoderDirection.FORWARD);
        odometry.recalibrateIMU();
        odometry.resetPosAndIMU();
    }

    @Override
    public void loop() {
            /*
            Request an update from the Pinpoint odometry computer. This checks almost all outputs
            from the device in a single I2C read.
            */
            odometry.update();

            /*
            Optionally, you can update only the heading of the device. This takes less time to read, but will not
            pull any other data. Only the heading (which you can pull with getHeading() or in getPosition().
            */
            //odo.update(GoBildaPinpointDriver.readData.ONLY_UPDATE_HEADING);


            if (robot.driverGamepad.a){
                odometry.resetPosAndIMU(); //resets the position to 0 and recalibrates the IMU
            }

            if (robot.driverGamepad.b){
                odometry.recalibrateIMU(); //recalibrates the IMU without resetting position
            }

            /*
            This code prints the loop frequency of the REV Control Hub. This frequency is effected
            by I²C reads/writes. So it's good to keep an eye on. This code calculates the amount
            of time each cycle takes and finds the frequency (number of updates per second) from
            that cycle time.
            */
            double newTime = robot.opMode.getRuntime();
            double loopTime = newTime-oldTime;
            double frequency = 1/loopTime;
            oldTime = newTime;


            /*
            gets the current Position (x & y in mm, and heading in degrees) of the robot, and prints it.
            */
            Pose2D pos = odometry.getPosition();
            String data = String.format(Locale.US, "{X: %.3f, Y: %.3f, H: %.3f}", pos.getX(DistanceUnit.MM), pos.getY(DistanceUnit.MM), pos.getHeading(AngleUnit.DEGREES));
            this.robot.telemetryObserver.addTelemetry("Position", data);

            /*
            gets the current Velocity (x & y in mm/sec and heading in degrees/sec) and prints it.
            */
            Pose2D vel = odometry.getVelocity();
            String velocity = String.format(Locale.US,"{XVel: %.3f, YVel: %.3f, HVel: %.3f}", vel.getX(DistanceUnit.MM), vel.getY(DistanceUnit.MM), vel.getHeading(AngleUnit.DEGREES));
            this.robot.telemetryObserver.addTelemetry("Velocity", velocity);



            /*
            Gets the Pinpoint device status. Pinpoint can reflect a few states. But we'll primarily see
            READY: the device is working as normal
            CALIBRATING: the device is calibrating and outputs are put on hold
            NOT_READY: the device is resetting from scratch. This should only happen after a power-cycle
            FAULT_NO_PODS_DETECTED - the device does not detect any pods plugged in
            FAULT_X_POD_NOT_DETECTED - The device does not detect an X pod plugged in
            FAULT_Y_POD_NOT_DETECTED - The device does not detect a Y pod plugged in
            */
            cccccbgtjurbfnrrgjjeftghbfvfiitbdjrjkrdkciit
            .addData("Status", odometry.getDeviceStatus());

            telemetry.addData("Pinpoint Frequency", odometry.getFrequency()); //prints/gets the current refresh rate of the Pinpoint

            telemetry.addData("REV Hub Frequency: ", frequency); //prints the control system refresh rate
    }

    public void addTelemetry() {

    }
}
