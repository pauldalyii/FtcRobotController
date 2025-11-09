# Control Hub and Expansion Hub Port Mapping Enums

The `ControlHubPorts` and `ExpansionHubPorts` enums provide a generic way to map hardware ports when assigning motors, servos, and other devices to Control Hubs and Expansion Hubs.

## Purpose

These enums use generic port names with clear hub identification (e.g., `ControlHubPorts.MOTOR_0`, `ExpansionHubPorts.MOTOR_0`) instead of season-specific hardware configurations, making them reusable across different seasons and robot designs.

## Key Benefits

1. **Clear Device Identification**: The enum name immediately tells you which hub the port belongs to
2. **Separate Namespaces**: Control Hub and Expansion Hub ports are in separate enums, preventing confusion
3. **Configuration Name Prefix**: Port names include "ch_" (Control Hub) or "eh_" (Expansion Hub) prefixes
4. **Consistency**: All team members use the same port naming convention

## Port Naming Convention

### Control Hub Ports (`ControlHubPorts`)

Configuration names are prefixed with `ch_`:

- **Motors**: `MOTOR_0` through `MOTOR_3` (config names: `ch_motor_0` to `ch_motor_3`)
- **Servos**: `SERVO_0` through `SERVO_5` (config names: `ch_servo_0` to `ch_servo_5`)
- **Digital Devices**: `DIGITAL_0` through `DIGITAL_7` (config names: `ch_digital_0` to `ch_digital_7`)
- **Analog Devices**: `ANALOG_0` through `ANALOG_3` (config names: `ch_analog_0` to `ch_analog_3`)
- **I2C Devices**: `I2C_0` through `I2C_3` (config names: `ch_i2c_0` to `ch_i2c_3`)

### Expansion Hub Ports (`ExpansionHubPorts`)

Configuration names are prefixed with `eh_`:

- **Motors**: `MOTOR_0` through `MOTOR_3` (config names: `eh_motor_0` to `eh_motor_3`)
- **Servos**: `SERVO_0` through `SERVO_5` (config names: `eh_servo_0` to `eh_servo_5`)
- **Digital Devices**: `DIGITAL_0` through `DIGITAL_7` (config names: `eh_digital_0` to `eh_digital_7`)
- **Analog Devices**: `ANALOG_0` through `ANALOG_3` (config names: `eh_analog_0` to `eh_analog_3`)
- **I2C Devices**: `I2C_0` through `I2C_3` (config names: `eh_i2c_0` to `eh_i2c_3`)

## Usage

### Basic Usage

```java
// In your OpMode - Control Hub devices
DcMotor leftDrive = hardwareMap.get(DcMotor.class, ControlHubPorts.MOTOR_0.getConfigName());
Servo claw = hardwareMap.get(Servo.class, ControlHubPorts.SERVO_0.getConfigName());

// Expansion Hub devices
DcMotor armMotor = hardwareMap.get(DcMotor.class, ExpansionHubPorts.MOTOR_0.getConfigName());
Servo gripper = hardwareMap.get(Servo.class, ExpansionHubPorts.SERVO_1.getConfigName());
```

### Hardware Configuration

When configuring your robot hardware in the Driver Station:

1. For Control Hub devices, use names like: `ch_motor_0`, `ch_servo_0`, etc.
2. For Expansion Hub devices, use names like: `eh_motor_0`, `eh_servo_0`, etc.
3. The prefixes make it immediately clear which hub each device is connected to
4. This ensures consistency between your code and hardware configuration

### Example OpMode

See `PortMappingExample.java` for a complete working example that demonstrates:
- How to initialize motors and servos using both enums
- How to clearly organize devices by hub
- How to display port information in telemetry grouped by hub
- How to configure motor directions and behaviors

## Migration from Old Single Enum

If you were using the previous single `PortMapping` enum:

**Before:**
```java
DcMotor leftDrive = hardwareMap.get(DcMotor.class, PortMapping.MOTOR_0.getConfigName());
DcMotor armMotor = hardwareMap.get(DcMotor.class, PortMapping.MOTOR_4.getConfigName());
```

**After:**
```java
// Now it's clear which hub each device is on
DcMotor leftDrive = hardwareMap.get(DcMotor.class, ControlHubPorts.MOTOR_0.getConfigName());
DcMotor armMotor = hardwareMap.get(DcMotor.class, ExpansionHubPorts.MOTOR_0.getConfigName());
```

You'll also need to update your hardware configuration in the Driver Station to use the new prefixed names.

## Design Rationale

The separation into two enums provides several advantages:

1. **Type Safety**: You can't accidentally mix Control Hub and Expansion Hub ports
2. **Self-Documenting**: Code is more readable and immediately shows hub assignments
3. **Namespace Clarity**: Each hub has its own `MOTOR_0`, `SERVO_0`, etc., matching physical labels
4. **Configuration Safety**: The `ch_` and `eh_` prefixes in config names prevent naming conflicts

## Additional Notes

- Both hubs support the same number of each port type (4 motors, 6 servos, etc.)
- Port numbering (0-3, 0-5, 0-7) matches the physical port labels on the hubs
- You can use any port assignment that makes sense for your robot
- The enums simply provide a consistent naming scheme across your codebase
- Remember to update your Driver Station hardware configuration to match the port names used in your code
