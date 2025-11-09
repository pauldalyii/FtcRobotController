/* Copyright (c) 2024 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

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
