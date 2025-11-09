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
 * DcMotor leftDrive = hardwareMap.get(DcMotor.class, ControlHubPorts.MOTOR_0.getConfigName());
 * Servo claw = hardwareMap.get(Servo.class, ControlHubPorts.SERVO_0.getConfigName());
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
     * Gets the configuration name for this Control Hub port mapping.
     * This name should be used when configuring hardware in the Robot Configuration
     * and when retrieving hardware from the hardwareMap.
     * 
     * @return The configuration name string with "ch_" prefix indicating Control Hub
     */
    public String getConfigName() {
        return configName;
    }
    
    /**
     * Returns a string representation of this Control Hub port mapping
     * @return The enum constant name
     */
    @Override
    public String toString() {
        return name();
    }
}
