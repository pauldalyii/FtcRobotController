# PortMapping Enum

The `PortMapping` enum provides a generic way to map hardware ports when assigning motors, servos, and other devices to Control Hubs and Expansion Hubs.

## Purpose

This enum uses generic port names (e.g., `MOTOR_0`, `SERVO_0`) instead of season-specific hardware configurations, making it reusable across different seasons and robot designs.

## Port Naming Convention

The enum follows this naming convention:

- **Motors**: `MOTOR_0` through `MOTOR_7`
  - Ports 0-3: Typically used for Control Hub
  - Ports 4-7: Typically used for Expansion Hub

- **Servos**: `SERVO_0` through `SERVO_11`
  - Ports 0-5: Typically used for Control Hub
  - Ports 6-11: Typically used for Expansion Hub

- **Digital Devices**: `DIGITAL_0` through `DIGITAL_15`
  - Ports 0-7: Typically used for Control Hub
  - Ports 8-15: Typically used for Expansion Hub

- **Analog Devices**: `ANALOG_0` through `ANALOG_7`
  - Ports 0-3: Typically used for Control Hub
  - Ports 4-7: Typically used for Expansion Hub

- **I2C Devices**: `I2C_0` through `I2C_7`
  - Ports 0-3: Typically used for Control Hub
  - Ports 4-7: Typically used for Expansion Hub

## Usage

### Basic Usage

```java
// In your OpMode
DcMotor leftDrive = hardwareMap.get(DcMotor.class, PortMapping.MOTOR_0.getConfigName());
Servo claw = hardwareMap.get(Servo.class, PortMapping.SERVO_0.getConfigName());
```

### Hardware Configuration

When configuring your robot hardware in the Driver Station:

1. Use the configuration names from the enum (e.g., `motor_0`, `servo_0`)
2. These names are returned by the `getConfigName()` method
3. This ensures consistency between your code and hardware configuration

### Example OpMode

See `PortMappingExample.java` for a complete working example that demonstrates:
- How to initialize motors and servos using PortMapping
- How to display port information in telemetry
- How to configure motor directions and behaviors

## Benefits

1. **Consistency**: All team members use the same port naming convention
2. **Clarity**: Port assignments are clear and explicit in the code
3. **Maintainability**: Easy to update port assignments across seasons
4. **Documentation**: Self-documenting code that shows which physical port each device uses
5. **Reusability**: Can be used across different robot configurations and seasons

## Migration from Existing Code

If you have existing code that uses hardcoded port names:

**Before:**
```java
DcMotor leftDrive = hardwareMap.get(DcMotor.class, "left_drive");
```

**After:**
```java
DcMotor leftDrive = hardwareMap.get(DcMotor.class, PortMapping.MOTOR_0.getConfigName());
```

You'll also need to update your hardware configuration in the Driver Station to use the new port names.

## Additional Notes

- The port numbering (0-3 for Control Hub, 4-7 for Expansion Hub) is a convention, not a requirement
- You can use any port assignment that makes sense for your robot
- The enum simply provides a consistent naming scheme across your codebase
- Remember to update your Driver Station hardware configuration to match the port names used in your code
