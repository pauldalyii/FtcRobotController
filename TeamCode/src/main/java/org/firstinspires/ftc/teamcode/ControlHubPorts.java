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
public enum ControlHubPorts {
    
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
    I2C_3("ch_i2c_3");
    
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
}
