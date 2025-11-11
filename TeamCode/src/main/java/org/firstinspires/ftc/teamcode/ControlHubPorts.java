package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareDevice;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.I2cDevice;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.LED;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;

/**
 * ControlHubPorts provides a generic way to map hardware ports specifically
 * for the Control Hub.
 * 
 * This enum uses generic port names (e.g., MOTOR_0, SERVO_0) instead of
 * season-specific hardware configurations, making it reusable across
 * different seasons and robot designs.
 * 
 * Usage Example:
 * <pre>
 * // Using helper methods (no .toString() needed)
 * DcMotor leftDrive = ControlHubPorts.MOTOR_0.getDcMotor(hardwareMap);
 * Servo claw = ControlHubPorts.SERVO_0.getServo(hardwareMap);
 * 
 * // Or with generic method for any hardware type
 * CRServo crServo = ControlHubPorts.SERVO_1.get(hardwareMap, CRServo.class);
 * </pre>
 */
public enum ControlHubPorts implements HardwarePort {
    
    // Motor ports (0-3 on Control Hub)
    /**
     * Control Hub Motor port 0
     */
    MOTOR_0("ch_motor_0"),
    
    /**
     * Control Hub Motor port 1
     */
    MOTOR_1("ch_motor_1"),
    
    /**
     * Control Hub Motor port 2
     */
    MOTOR_2("ch_motor_2"),
    
    /**
     * Control Hub Motor port 3
     */
    MOTOR_3("ch_motor_3"),
    
    // Servo ports (0-5 on Control Hub)
    /**
     * Control Hub Servo port 0
     */
    SERVO_0("ch_servo_0"),
    
    /**
     * Control Hub Servo port 1
     */
    SERVO_1("ch_servo_1"),
    
    /**
     * Control Hub Servo port 2
     */
    SERVO_2("ch_servo_2"),
    
    /**
     * Control Hub Servo port 3
     */
    SERVO_3("ch_servo_3"),
    
    /**
     * Control Hub Servo port 4
     */
    SERVO_4("ch_servo_4"),
    
    /**
     * Control Hub Servo port 5
     */
    SERVO_5("ch_servo_5"),
    
    // Digital ports (0-7 on Control Hub)
    /**
     * Control Hub Digital port 0
     */
    DIGITAL_0("ch_digital_0"),
    
    /**
     * Control Hub Digital port 1
     */
    DIGITAL_1("ch_digital_1"),
    
    /**
     * Control Hub Digital port 2
     */
    DIGITAL_2("ch_digital_2"),
    
    /**
     * Control Hub Digital port 3
     */
    DIGITAL_3("ch_digital_3"),
    
    /**
     * Control Hub Digital port 4
     */
    DIGITAL_4("ch_digital_4"),
    
    /**
     * Control Hub Digital port 5
     */
    DIGITAL_5("ch_digital_5"),
    
    /**
     * Control Hub Digital port 6
     */
    DIGITAL_6("ch_digital_6"),
    
    /**
     * Control Hub Digital port 7
     */
    DIGITAL_7("ch_digital_7"),
    
    // Analog ports (0-3 on Control Hub)
    /**
     * Control Hub Analog port 0
     */
    ANALOG_0("ch_analog_0"),
    
    /**
     * Control Hub Analog port 1
     */
    ANALOG_1("ch_analog_1"),
    
    /**
     * Control Hub Analog port 2
     */
    ANALOG_2("ch_analog_2"),
    
    /**
     * Control Hub Analog port 3
     */
    ANALOG_3("ch_analog_3"),
    
    // I2C Bus ports (0-3 on Control Hub)
    /**
     * Control Hub I2C Bus port 0
     */
    I2C_0("ch_i2c_0"),
    
    /**
     * Control Hub I2C Bus port 1
     */
    I2C_1("ch_i2c_1"),
    
    /**
     * Control Hub I2C Bus port 2
     */
    I2C_2("ch_i2c_2"),
    
    /**
     * Control Hub I2C Bus port 3
     */
    I2C_3("ch_i2c_3"),
    
    // USB Webcam port on Control Hub
    /**
     * Control Hub USB Webcam (typically "Webcam 1")
     */
    WEBCAM("Webcam 1");
    
    private final String configName;
    
    /**
     * Constructor for ControlHubPorts enum
     * @param configName The configuration name to use in hardwareMap.get() calls
     */
    ControlHubPorts(String configName) {
        this.configName = configName;
    }
    
    /**
     * Returns the configuration name for this Control Hub port mapping.
     * This name should be used when configuring hardware in the Robot Configuration
     * and when retrieving hardware from the hardwareMap.
     * 
     * @return The configuration name string with "ch_" prefix indicating Control Hub
     */
    @Override
    public String toString() {
        return configName;
    }
    
    // All hardware retrieval methods are inherited from HardwarePort interface
}
