
package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.teamcode.hardware.*;

public class MecanumDrive {
    // Declare your motors
    private DcMotor frontLeft, frontRight, backLeft, backRight;

    public MecanumDrive(DcMotor frontLeft, DcMotor frontRight, DcMotor backLeft, DcMotor backRight) {
        this.frontLeft = frontLeft;
        this.frontRight = frontRight;
        this.backLeft = backLeft;
        this.backRight = backRight;
    }

    public void drive(double strafe, double forward, double rotation) {
        // Calculate wheel speeds
        double fl = forward + strafe + rotation;
        double fr = forward - strafe - rotation;
        double bl = forward - strafe + rotation;
        double br = forward + strafe - rotation;

        // Normalize speeds to make sure they are within [-1, 1]
        double max = Math.max(1.0, Math.max(Math.abs(fl), Math.max(Math.abs(fr), Math.max(Math.abs(bl), Math.abs(br)))));
        fl /= max;
        fr /= max;
        bl /= max;
        br /= max;

        // Set the motor powers
        frontLeft.setPower(fl);
        frontRight.setPower(fr);
        backLeft.setPower(bl);
        backRight.setPower(br);
    }
}
