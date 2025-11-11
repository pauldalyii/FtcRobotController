package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.I2cDevice;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.LED;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;

/**
 * Interface for port mapping enums that provides common hardware retrieval methods.
 * This interface eliminates code duplication between ControlHubPorts and ExpansionHubPorts.
 */
public interface HardwarePort {
    
    /**
     * Gets the configuration name for this port.
     * 
     * @return The configuration name string
     */
    String toString();
    
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
    default <T> T get(HardwareMap hardwareMap, Class<? extends T> deviceClass) {
        return hardwareMap.get(deviceClass, toString());
    }
    
    /**
     * Convenience method to retrieve a DcMotor from the hardware map.
     * 
     * @param hardwareMap The hardware map from the OpMode
     * @return The DcMotor instance
     * @throws IllegalArgumentException if the motor is not found in the configuration
     */
    default DcMotor getDcMotor(HardwareMap hardwareMap) {
        return hardwareMap.get(DcMotor.class, toString());
    }
    
    /**
     * Convenience method to retrieve a DcMotorEx from the hardware map.
     * DcMotorEx provides extended motor functionality with velocity control and additional features.
     * 
     * @param hardwareMap The hardware map from the OpMode
     * @return The DcMotorEx instance
     * @throws IllegalArgumentException if the motor is not found in the configuration
     */
    default DcMotorEx getDcMotorEx(HardwareMap hardwareMap) {
        return hardwareMap.get(DcMotorEx.class, toString());
    }
    
    /**
     * Convenience method to retrieve a Servo from the hardware map.
     * 
     * @param hardwareMap The hardware map from the OpMode
     * @return The Servo instance
     * @throws IllegalArgumentException if the servo is not found in the configuration
     */
    default Servo getServo(HardwareMap hardwareMap) {
        return hardwareMap.get(Servo.class, toString());
    }
    
    /**
     * Convenience method to retrieve a CRServo (Continuous Rotation Servo) from the hardware map.
     * 
     * @param hardwareMap The hardware map from the OpMode
     * @return The CRServo instance
     * @throws IllegalArgumentException if the CR servo is not found in the configuration
     */
    default CRServo getCRServo(HardwareMap hardwareMap) {
        return hardwareMap.get(CRServo.class, toString());
    }
    
    /**
     * Convenience method to retrieve a DigitalChannel from the hardware map.
     * Used for digital input/output devices.
     * 
     * @param hardwareMap The hardware map from the OpMode
     * @return The DigitalChannel instance
     * @throws IllegalArgumentException if the device is not found in the configuration
     */
    default DigitalChannel getDigitalChannel(HardwareMap hardwareMap) {
        return hardwareMap.get(DigitalChannel.class, toString());
    }
    
    /**
     * Convenience method to retrieve an AnalogInput from the hardware map.
     * Used for analog sensors and inputs.
     * 
     * @param hardwareMap The hardware map from the OpMode
     * @return The AnalogInput instance
     * @throws IllegalArgumentException if the device is not found in the configuration
     */
    default AnalogInput getAnalogInput(HardwareMap hardwareMap) {
        return hardwareMap.get(AnalogInput.class, toString());
    }
    
    /**
     * Convenience method to retrieve a TouchSensor from the hardware map.
     * 
     * @param hardwareMap The hardware map from the OpMode
     * @return The TouchSensor instance
     * @throws IllegalArgumentException if the sensor is not found in the configuration
     */
    default TouchSensor getTouchSensor(HardwareMap hardwareMap) {
        return hardwareMap.get(TouchSensor.class, toString());
    }
    
    /**
     * Convenience method to retrieve a ColorSensor from the hardware map.
     * 
     * @param hardwareMap The hardware map from the OpMode
     * @return The ColorSensor instance
     * @throws IllegalArgumentException if the sensor is not found in the configuration
     */
    default ColorSensor getColorSensor(HardwareMap hardwareMap) {
        return hardwareMap.get(ColorSensor.class, toString());
    }
    
    /**
     * Convenience method to retrieve a DistanceSensor from the hardware map.
     * 
     * @param hardwareMap The hardware map from the OpMode
     * @return The DistanceSensor instance
     * @throws IllegalArgumentException if the sensor is not found in the configuration
     */
    default DistanceSensor getDistanceSensor(HardwareMap hardwareMap) {
        return hardwareMap.get(DistanceSensor.class, toString());
    }
    
    /**
     * Convenience method to retrieve an IMU (Inertial Measurement Unit) from the hardware map.
     * 
     * @param hardwareMap The hardware map from the OpMode
     * @return The IMU instance
     * @throws IllegalArgumentException if the IMU is not found in the configuration
     */
    default IMU getIMU(HardwareMap hardwareMap) {
        return hardwareMap.get(IMU.class, toString());
    }
    
    /**
     * Convenience method to retrieve an LED from the hardware map.
     * 
     * @param hardwareMap The hardware map from the OpMode
     * @return The LED instance
     * @throws IllegalArgumentException if the LED is not found in the configuration
     */
    default LED getLED(HardwareMap hardwareMap) {
        return hardwareMap.get(LED.class, toString());
    }
    
    /**
     * Convenience method to retrieve an I2cDevice from the hardware map.
     * Used for custom I2C devices.
     * 
     * @param hardwareMap The hardware map from the OpMode
     * @return The I2cDevice instance
     * @throws IllegalArgumentException if the device is not found in the configuration
     */
    default I2cDevice getI2cDevice(HardwareMap hardwareMap) {
        return hardwareMap.get(I2cDevice.class, toString());
    }
    
    /**
     * Convenience method to retrieve a WebcamName from the hardware map.
     * Used for accessing USB webcams.
     * 
     * @param hardwareMap The hardware map from the OpMode
     * @return The WebcamName instance
     * @throws IllegalArgumentException if the webcam is not found in the configuration
     */
    default WebcamName getWebcamName(HardwareMap hardwareMap) {
        return hardwareMap.get(WebcamName.class, toString());
    }
}
