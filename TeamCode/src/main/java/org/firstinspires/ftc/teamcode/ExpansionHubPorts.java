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
 * ExpansionHubPorts provides a generic way to map hardware ports specifically
 * for the Expansion Hub.
 * 
 * This enum uses generic port names (e.g., MOTOR_0, SERVO_0) instead of
 * season-specific hardware configurations, making it reusable across
 * different seasons and robot designs.
 * 
 * Usage Example:
 * <pre>
 * // Using helper methods (no .toString() needed)
 * DcMotor rightDrive = ExpansionHubPorts.MOTOR_0.getDcMotor(hardwareMap);
 * Servo gripper = ExpansionHubPorts.SERVO_0.getServo(hardwareMap);
 * 
 * // Or with generic method for any hardware type
 * CRServo crServo = ExpansionHubPorts.SERVO_1.get(hardwareMap, CRServo.class);
 * </pre>
 */
public enum ExpansionHubPorts {
    
    // Motor ports (0-3 on Expansion Hub)
    /**
     * Expansion Hub Motor port 0
     */
    MOTOR_0("eh_motor_0"),
    
    /**
     * Expansion Hub Motor port 1
     */
    MOTOR_1("eh_motor_1"),
    
    /**
     * Expansion Hub Motor port 2
     */
    MOTOR_2("eh_motor_2"),
    
    /**
     * Expansion Hub Motor port 3
     */
    MOTOR_3("eh_motor_3"),
    
    // Servo ports (0-5 on Expansion Hub)
    /**
     * Expansion Hub Servo port 0
     */
    SERVO_0("eh_servo_0"),
    
    /**
     * Expansion Hub Servo port 1
     */
    SERVO_1("eh_servo_1"),
    
    /**
     * Expansion Hub Servo port 2
     */
    SERVO_2("eh_servo_2"),
    
    /**
     * Expansion Hub Servo port 3
     */
    SERVO_3("eh_servo_3"),
    
    /**
     * Expansion Hub Servo port 4
     */
    SERVO_4("eh_servo_4"),
    
    /**
     * Expansion Hub Servo port 5
     */
    SERVO_5("eh_servo_5"),
    
    // Digital ports (0-7 on Expansion Hub)
    /**
     * Expansion Hub Digital port 0
     */
    DIGITAL_0("eh_digital_0"),
    
    /**
     * Expansion Hub Digital port 1
     */
    DIGITAL_1("eh_digital_1"),
    
    /**
     * Expansion Hub Digital port 2
     */
    DIGITAL_2("eh_digital_2"),
    
    /**
     * Expansion Hub Digital port 3
     */
    DIGITAL_3("eh_digital_3"),
    
    /**
     * Expansion Hub Digital port 4
     */
    DIGITAL_4("eh_digital_4"),
    
    /**
     * Expansion Hub Digital port 5
     */
    DIGITAL_5("eh_digital_5"),
    
    /**
     * Expansion Hub Digital port 6
     */
    DIGITAL_6("eh_digital_6"),
    
    /**
     * Expansion Hub Digital port 7
     */
    DIGITAL_7("eh_digital_7"),
    
    // Analog ports (0-3 on Expansion Hub)
    /**
     * Expansion Hub Analog port 0
     */
    ANALOG_0("eh_analog_0"),
    
    /**
     * Expansion Hub Analog port 1
     */
    ANALOG_1("eh_analog_1"),
    
    /**
     * Expansion Hub Analog port 2
     */
    ANALOG_2("eh_analog_2"),
    
    /**
     * Expansion Hub Analog port 3
     */
    ANALOG_3("eh_analog_3"),
    
    // I2C Bus ports (0-3 on Expansion Hub)
    /**
     * Expansion Hub I2C Bus port 0
     */
    I2C_0("eh_i2c_0"),
    
    /**
     * Expansion Hub I2C Bus port 1
     */
    I2C_1("eh_i2c_1"),
    
    /**
     * Expansion Hub I2C Bus port 2
     */
    I2C_2("eh_i2c_2"),
    
    /**
     * Expansion Hub I2C Bus port 3
     */
    I2C_3("eh_i2c_3");
    
    private final String configName;
    
    /**
     * Constructor for ExpansionHubPorts enum
     * @param configName The configuration name to use in hardwareMap.get() calls
     */
    ExpansionHubPorts(String configName) {
        this.configName = configName;
    }
    
    /**
     * Returns the configuration name for this Expansion Hub port mapping.
     * This name should be used when configuring hardware in the Robot Configuration
     * and when retrieving hardware from the hardwareMap.
     * 
     * @return The configuration name string with "eh_" prefix indicating Expansion Hub
     */
    @Override
    public String toString() {
        return configName;
    }
    
    /**
     * Generic method to retrieve any hardware device from the hardware map.
     * This method provides type-safe hardware retrieval without needing to call toString().
     * 
     * @param hardwareMap The hardware map from the OpMode
     * @param deviceClass The class of the hardware device to retrieve
     * @param <T> The type of hardware device
     * @return The hardware device instance
     * @throws IllegalArgumentException if the device is not found in the configuration
     */
    public <T> T get(HardwareMap hardwareMap, Class<? extends T> deviceClass) {
        return hardwareMap.get(deviceClass, configName);
    }
    
    /**
     * Convenience method to retrieve a DcMotor from the hardware map.
     * 
     * @param hardwareMap The hardware map from the OpMode
     * @return The DcMotor instance
     * @throws IllegalArgumentException if the motor is not found in the configuration
     */
    public DcMotor getDcMotor(HardwareMap hardwareMap) {
        return hardwareMap.get(DcMotor.class, configName);
    }
    
    /**
     * Convenience method to retrieve a DcMotorEx from the hardware map.
     * DcMotorEx provides extended motor functionality with velocity control and additional features.
     * 
     * @param hardwareMap The hardware map from the OpMode
     * @return The DcMotorEx instance
     * @throws IllegalArgumentException if the motor is not found in the configuration
     */
    public DcMotorEx getDcMotorEx(HardwareMap hardwareMap) {
        return hardwareMap.get(DcMotorEx.class, configName);
    }
    
    /**
     * Convenience method to retrieve a Servo from the hardware map.
     * 
     * @param hardwareMap The hardware map from the OpMode
     * @return The Servo instance
     * @throws IllegalArgumentException if the servo is not found in the configuration
     */
    public Servo getServo(HardwareMap hardwareMap) {
        return hardwareMap.get(Servo.class, configName);
    }
    
    /**
     * Convenience method to retrieve a CRServo (Continuous Rotation Servo) from the hardware map.
     * 
     * @param hardwareMap The hardware map from the OpMode
     * @return The CRServo instance
     * @throws IllegalArgumentException if the CR servo is not found in the configuration
     */
    public CRServo getCRServo(HardwareMap hardwareMap) {
        return hardwareMap.get(CRServo.class, configName);
    }
    
    /**
     * Convenience method to retrieve a DigitalChannel from the hardware map.
     * Used for digital input/output devices.
     * 
     * @param hardwareMap The hardware map from the OpMode
     * @return The DigitalChannel instance
     * @throws IllegalArgumentException if the device is not found in the configuration
     */
    public DigitalChannel getDigitalChannel(HardwareMap hardwareMap) {
        return hardwareMap.get(DigitalChannel.class, configName);
    }
    
    /**
     * Convenience method to retrieve an AnalogInput from the hardware map.
     * Used for analog sensors and inputs.
     * 
     * @param hardwareMap The hardware map from the OpMode
     * @return The AnalogInput instance
     * @throws IllegalArgumentException if the device is not found in the configuration
     */
    public AnalogInput getAnalogInput(HardwareMap hardwareMap) {
        return hardwareMap.get(AnalogInput.class, configName);
    }
    
    /**
     * Convenience method to retrieve a TouchSensor from the hardware map.
     * 
     * @param hardwareMap The hardware map from the OpMode
     * @return The TouchSensor instance
     * @throws IllegalArgumentException if the sensor is not found in the configuration
     */
    public TouchSensor getTouchSensor(HardwareMap hardwareMap) {
        return hardwareMap.get(TouchSensor.class, configName);
    }
    
    /**
     * Convenience method to retrieve a ColorSensor from the hardware map.
     * 
     * @param hardwareMap The hardware map from the OpMode
     * @return The ColorSensor instance
     * @throws IllegalArgumentException if the sensor is not found in the configuration
     */
    public ColorSensor getColorSensor(HardwareMap hardwareMap) {
        return hardwareMap.get(ColorSensor.class, configName);
    }
    
    /**
     * Convenience method to retrieve a DistanceSensor from the hardware map.
     * 
     * @param hardwareMap The hardware map from the OpMode
     * @return The DistanceSensor instance
     * @throws IllegalArgumentException if the sensor is not found in the configuration
     */
    public DistanceSensor getDistanceSensor(HardwareMap hardwareMap) {
        return hardwareMap.get(DistanceSensor.class, configName);
    }
    
    /**
     * Convenience method to retrieve an IMU (Inertial Measurement Unit) from the hardware map.
     * 
     * @param hardwareMap The hardware map from the OpMode
     * @return The IMU instance
     * @throws IllegalArgumentException if the IMU is not found in the configuration
     */
    public IMU getIMU(HardwareMap hardwareMap) {
        return hardwareMap.get(IMU.class, configName);
    }
    
    /**
     * Convenience method to retrieve an LED from the hardware map.
     * 
     * @param hardwareMap The hardware map from the OpMode
     * @return The LED instance
     * @throws IllegalArgumentException if the LED is not found in the configuration
     */
    public LED getLED(HardwareMap hardwareMap) {
        return hardwareMap.get(LED.class, configName);
    }
    
    /**
     * Convenience method to retrieve an I2cDevice from the hardware map.
     * Used for custom I2C devices.
     * 
     * @param hardwareMap The hardware map from the OpMode
     * @return The I2cDevice instance
     * @throws IllegalArgumentException if the device is not found in the configuration
     */
    public I2cDevice getI2cDevice(HardwareMap hardwareMap) {
        return hardwareMap.get(I2cDevice.class, configName);
    }
    
    /**
     * Convenience method to retrieve a WebcamName from the hardware map.
     * Used for accessing USB webcams.
     * 
     * @param hardwareMap The hardware map from the OpMode
     * @return The WebcamName instance
     * @throws IllegalArgumentException if the webcam is not found in the configuration
     */
    public WebcamName getWebcamName(HardwareMap hardwareMap) {
        return hardwareMap.get(WebcamName.class, configName);
    }
}
