package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.teamcode.hardware.*;

public class OdometryRobot {
    private MecanumDrive mecanumDrive;
    private GoBildaPinpointDriver odometry; // This is a placeholder for your goBILDA odometry device class

    // Define the target position
    private double targetX, targetY;

    public OdometryRobot(MecanumDrive mecanumDrive, GoBildaPinpointDriver odometry) {
        this.mecanumDrive = mecanumDrive;
        this.odometry = odometry;
    }

    public void driveToTarget(double targetX, double targetY) {
        // Continue looping until the robot is close enough to the target position
        while (Math.abs(targetX - odometry.getPosX()) >= 10 || Math.abs(targetY - odometry.getPosY()) >= 10) {
            // Get the current position
            double currentX = odometry.getPosX();
            double currentY = odometry.getPosY();
    
            // Calculate the error
            double errorX = targetX - currentX;
            double errorY = targetY - currentY;
    
            // Proportional control for demonstration purposes
            double kP = 0.1; // Tuning parameter
            double strafe = kP * errorX;
            double forward = kP * errorY;
    
            // Drive the robot
            mecanumDrive.drive(strafe, forward, 0);
        }
    
        // Optionally, stop the motors once the target is reached
        mecanumDrive.drive(0, 0, 0);
    }
}