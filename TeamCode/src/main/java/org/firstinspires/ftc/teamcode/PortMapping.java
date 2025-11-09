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
 * PortMapping provides a generic way to map hardware ports when assigning
 * motors, servos, and other devices to Control Hubs and Expansion Hubs.
 * 
 * This enum uses generic port names (e.g., MOTOR_0, SERVO_0) instead of
 * season-specific hardware configurations, making it reusable across
 * different seasons and robot designs.
 * 
 * Port Numbering Convention:
 * - Ports 0-3/5/7 are typically used for Control Hub
 * - Ports 4-7/11/15 are typically used for Expansion Hub
 * 
 * Usage Example:
 * <pre>
 * DcMotor leftDrive = hardwareMap.get(DcMotor.class, PortMapping.MOTOR_0.getConfigName());
 * Servo claw = hardwareMap.get(Servo.class, PortMapping.SERVO_0.getConfigName());
 * </pre>
 */
public enum PortMapping {
    
    // Motor ports (0-3: Control Hub, 4-7: Expansion Hub)
    /**
     * Motor port 0 (typically Control Hub)
     */
    MOTOR_0("motor_0"),
    
    /**
     * Motor port 1 (typically Control Hub)
     */
    MOTOR_1("motor_1"),
    
    /**
     * Motor port 2 (typically Control Hub)
     */
    MOTOR_2("motor_2"),
    
    /**
     * Motor port 3 (typically Control Hub)
     */
    MOTOR_3("motor_3"),
    
    /**
     * Motor port 4 (typically Expansion Hub)
     */
    MOTOR_4("motor_4"),
    
    /**
     * Motor port 5 (typically Expansion Hub)
     */
    MOTOR_5("motor_5"),
    
    /**
     * Motor port 6 (typically Expansion Hub)
     */
    MOTOR_6("motor_6"),
    
    /**
     * Motor port 7 (typically Expansion Hub)
     */
    MOTOR_7("motor_7"),
    
    // Servo ports (0-5: Control Hub, 6-11: Expansion Hub)
    /**
     * Servo port 0 (typically Control Hub)
     */
    SERVO_0("servo_0"),
    
    /**
     * Servo port 1 (typically Control Hub)
     */
    SERVO_1("servo_1"),
    
    /**
     * Servo port 2 (typically Control Hub)
     */
    SERVO_2("servo_2"),
    
    /**
     * Servo port 3 (typically Control Hub)
     */
    SERVO_3("servo_3"),
    
    /**
     * Servo port 4 (typically Control Hub)
     */
    SERVO_4("servo_4"),
    
    /**
     * Servo port 5 (typically Control Hub)
     */
    SERVO_5("servo_5"),
    
    /**
     * Servo port 6 (typically Expansion Hub)
     */
    SERVO_6("servo_6"),
    
    /**
     * Servo port 7 (typically Expansion Hub)
     */
    SERVO_7("servo_7"),
    
    /**
     * Servo port 8 (typically Expansion Hub)
     */
    SERVO_8("servo_8"),
    
    /**
     * Servo port 9 (typically Expansion Hub)
     */
    SERVO_9("servo_9"),
    
    /**
     * Servo port 10 (typically Expansion Hub)
     */
    SERVO_10("servo_10"),
    
    /**
     * Servo port 11 (typically Expansion Hub)
     */
    SERVO_11("servo_11"),
    
    // Digital ports (0-7: Control Hub, 8-15: Expansion Hub)
    /**
     * Digital port 0 (typically Control Hub)
     */
    DIGITAL_0("digital_0"),
    
    /**
     * Digital port 1 (typically Control Hub)
     */
    DIGITAL_1("digital_1"),
    
    /**
     * Digital port 2 (typically Control Hub)
     */
    DIGITAL_2("digital_2"),
    
    /**
     * Digital port 3 (typically Control Hub)
     */
    DIGITAL_3("digital_3"),
    
    /**
     * Digital port 4 (typically Control Hub)
     */
    DIGITAL_4("digital_4"),
    
    /**
     * Digital port 5 (typically Control Hub)
     */
    DIGITAL_5("digital_5"),
    
    /**
     * Digital port 6 (typically Control Hub)
     */
    DIGITAL_6("digital_6"),
    
    /**
     * Digital port 7 (typically Control Hub)
     */
    DIGITAL_7("digital_7"),
    
    /**
     * Digital port 8 (typically Expansion Hub)
     */
    DIGITAL_8("digital_8"),
    
    /**
     * Digital port 9 (typically Expansion Hub)
     */
    DIGITAL_9("digital_9"),
    
    /**
     * Digital port 10 (typically Expansion Hub)
     */
    DIGITAL_10("digital_10"),
    
    /**
     * Digital port 11 (typically Expansion Hub)
     */
    DIGITAL_11("digital_11"),
    
    /**
     * Digital port 12 (typically Expansion Hub)
     */
    DIGITAL_12("digital_12"),
    
    /**
     * Digital port 13 (typically Expansion Hub)
     */
    DIGITAL_13("digital_13"),
    
    /**
     * Digital port 14 (typically Expansion Hub)
     */
    DIGITAL_14("digital_14"),
    
    /**
     * Digital port 15 (typically Expansion Hub)
     */
    DIGITAL_15("digital_15"),
    
    // Analog ports (0-3: Control Hub, 4-7: Expansion Hub)
    /**
     * Analog port 0 (typically Control Hub)
     */
    ANALOG_0("analog_0"),
    
    /**
     * Analog port 1 (typically Control Hub)
     */
    ANALOG_1("analog_1"),
    
    /**
     * Analog port 2 (typically Control Hub)
     */
    ANALOG_2("analog_2"),
    
    /**
     * Analog port 3 (typically Control Hub)
     */
    ANALOG_3("analog_3"),
    
    /**
     * Analog port 4 (typically Expansion Hub)
     */
    ANALOG_4("analog_4"),
    
    /**
     * Analog port 5 (typically Expansion Hub)
     */
    ANALOG_5("analog_5"),
    
    /**
     * Analog port 6 (typically Expansion Hub)
     */
    ANALOG_6("analog_6"),
    
    /**
     * Analog port 7 (typically Expansion Hub)
     */
    ANALOG_7("analog_7"),
    
    // I2C Bus ports (0-3: Control Hub, 4-7: Expansion Hub)
    /**
     * I2C Bus port 0 (typically Control Hub)
     */
    I2C_0("i2c_0"),
    
    /**
     * I2C Bus port 1 (typically Control Hub)
     */
    I2C_1("i2c_1"),
    
    /**
     * I2C Bus port 2 (typically Control Hub)
     */
    I2C_2("i2c_2"),
    
    /**
     * I2C Bus port 3 (typically Control Hub)
     */
    I2C_3("i2c_3"),
    
    /**
     * I2C Bus port 4 (typically Expansion Hub)
     */
    I2C_4("i2c_4"),
    
    /**
     * I2C Bus port 5 (typically Expansion Hub)
     */
    I2C_5("i2c_5"),
    
    /**
     * I2C Bus port 6 (typically Expansion Hub)
     */
    I2C_6("i2c_6"),
    
    /**
     * I2C Bus port 7 (typically Expansion Hub)
     */
    I2C_7("i2c_7");
    
    private final String configName;
    
    /**
     * Constructor for PortMapping enum
     * @param configName The configuration name to use in hardwareMap.get() calls
     */
    PortMapping(String configName) {
        this.configName = configName;
    }
    
    /**
     * Gets the configuration name for this port mapping.
     * This name should be used when configuring hardware in the Robot Configuration
     * and when retrieving hardware from the hardwareMap.
     * 
     * @return The configuration name string
     */
    public String getConfigName() {
        return configName;
    }
    
    /**
     * Returns a string representation of this port mapping
     * @return The enum constant name
     */
    @Override
    public String toString() {
        return name();
    }
}
