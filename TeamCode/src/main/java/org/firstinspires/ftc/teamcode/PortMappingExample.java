package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * This OpMode demonstrates how to use the ControlHubPorts and ExpansionHubPorts
 * enums to configure robot hardware in a generic and reusable way.
 * 
 * The separate enums make it clear which device (Control Hub or Expansion Hub)
 * each port belongs to, providing clarity in hardware configuration.
 * 
 * To use this example:
 * 1. Configure your robot hardware in the Driver Station using the port names
 *    from ControlHubPorts and ExpansionHubPorts (e.g., "ch_motor_0", "eh_motor_0")
 * 2. Remove or comment out the @Disabled annotation below
 * 3. Select this OpMode from the Driver Station
 */
@TeleOp(name="Port Mapping Example", group="Examples")
@Disabled
public class PortMappingExample extends LinearOpMode {
    
    // Declare motors using descriptive names
    // Drive motors on Control Hub
    private DcMotor leftDrive = null;
    private DcMotor rightDrive = null;
    
    // Arm motor on Expansion Hub
    private DcMotor armMotor = null;
    
    // Declare servos using descriptive names
    // Claw servo on Control Hub
    private Servo clawServo = null;
    
    @Override
    public void runOpMode() {
        // Initialize hardware using ControlHubPorts and ExpansionHubPorts enums
        // Using the helper methods eliminates the need for .toString() calls
        
        // Control Hub devices
        leftDrive = ControlHubPorts.MOTOR_0.getDcMotor(hardwareMap);
        rightDrive = ControlHubPorts.MOTOR_1.getDcMotor(hardwareMap);
        clawServo = ControlHubPorts.SERVO_0.getServo(hardwareMap);
        
        // Expansion Hub devices
        armMotor = ExpansionHubPorts.MOTOR_0.getDcMotor(hardwareMap);
        
        // Configure motor directions
        leftDrive.setDirection(DcMotor.Direction.REVERSE);
        rightDrive.setDirection(DcMotor.Direction.FORWARD);
        armMotor.setDirection(DcMotor.Direction.FORWARD);
        
        // Set motors to brake when power is zero
        leftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        armMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        
        // Initialize servo position
        clawServo.setPosition(0.5);
        
        // Display hardware configuration info
        telemetry.addData("Status", "Hardware Initialized");
        telemetry.addData("=== Control Hub ===", "");
        telemetry.addData("Left Drive", "Port: %s", ControlHubPorts.MOTOR_0);
        telemetry.addData("Right Drive", "Port: %s", ControlHubPorts.MOTOR_1);
        telemetry.addData("Claw Servo", "Port: %s", ControlHubPorts.SERVO_0);
        telemetry.addData("=== Expansion Hub ===", "");
        telemetry.addData("Arm Motor", "Port: %s", ExpansionHubPorts.MOTOR_0);
        telemetry.addData(">", "Press START to begin");
        telemetry.update();
        
        // Wait for driver to press START
        waitForStart();
        
        // Run until the driver presses STOP
        while (opModeIsActive()) {
            // Tank drive with left and right sticks
            double leftPower = -gamepad1.left_stick_y;
            double rightPower = -gamepad1.right_stick_y;
            
            // Arm control with triggers
            double armPower = gamepad1.right_trigger - gamepad1.left_trigger;
            
            // Claw control with bumpers
            if (gamepad1.right_bumper) {
                clawServo.setPosition(1.0); // Open
            } else if (gamepad1.left_bumper) {
                clawServo.setPosition(0.0); // Close
            }
            
            // Set motor powers
            leftDrive.setPower(leftPower);
            rightDrive.setPower(rightPower);
            armMotor.setPower(armPower);
            
            // Display telemetry
            telemetry.addData("Left Drive", "Power: %.2f", leftPower);
            telemetry.addData("Right Drive", "Power: %.2f", rightPower);
            telemetry.addData("Arm", "Power: %.2f", armPower);
            telemetry.addData("Claw", "Position: %.2f", clawServo.getPosition());
            telemetry.update();
        }
    }
}
