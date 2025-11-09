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
 * ExpansionHubPorts provides a generic way to map hardware ports specifically
 * for the Expansion Hub.
 * 
 * This enum uses generic port names (e.g., MOTOR_0, SERVO_0) instead of
 * season-specific hardware configurations, making it reusable across
 * different seasons and robot designs.
 * 
 * Usage Example:
 * <pre>
 * DcMotor rightDrive = hardwareMap.get(DcMotor.class, ExpansionHubPorts.MOTOR_0.toString());
 * Servo gripper = hardwareMap.get(Servo.class, ExpansionHubPorts.SERVO_0.toString());
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
}
