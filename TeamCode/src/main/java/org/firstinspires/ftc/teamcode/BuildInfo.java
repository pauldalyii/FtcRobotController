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
 * Utility class for accessing build information.
 * This class provides a centralized way to retrieve the build number
 * for display in TeleOp and Autonomous programs.
 */
public class BuildInfo {
    
    private static String cachedBuildNumber = null;
    
    /**
     * Gets the build number from BuildConfig.
     * The build number is cached after the first retrieval for performance.
     * 
     * @return The build number string, or "Unknown" if not available
     */
    public static String getBuildNumber() {
        if (cachedBuildNumber == null) {
            cachedBuildNumber = retrieveBuildNumber();
        }
        return cachedBuildNumber;
    }
    
    /**
     * Retrieves the build number from BuildConfig using reflection.
     * Falls back to "Unknown" if BuildConfig is not available.
     */
    private static String retrieveBuildNumber() {
        try {
            // Access BuildConfig from FtcRobotController module
            Class<?> buildConfigClass = Class.forName("com.qualcomm.ftcrobotcontroller.BuildConfig");
            java.lang.reflect.Field buildNumberField = buildConfigClass.getField("BUILD_NUMBER");
            return (String) buildNumberField.get(null);
        } catch (Exception e) {
            return "Unknown";
        }
    }
}
