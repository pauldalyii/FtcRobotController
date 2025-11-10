# GitHub Copilot Instructions

## Target Audience
The developers working with this repository are **middle school and high school students** learning robotics programming. Please provide:
- Clear, educational explanations with code suggestions
- Age-appropriate language and concepts
- Step-by-step guidance when suggesting complex implementations
- Comments that help students understand what the code does and why

## Repository Context
This is a **FIRST Tech Challenge (FTC) Robot Controller** repository based on the official template provided by FIRST Robotics Competition. The repository structure follows the standard FTC SDK layout.

## Code Organization Rules
⚠️ **IMPORTANT**: Custom team code should **ONLY** be added to:
```
TeamCode/src/main/java/org/firstinspires/ftc/teamcode/
```

### What NOT to modify:
- `FtcRobotController/` - Core SDK files (template provided by FIRST)
- Root-level Gradle files - Build configuration managed by FIRST
- Any files outside the TeamCode directory

### What you CAN modify:
- Files in `TeamCode/src/main/java/org/firstinspires/ftc/teamcode/` - **This is where all custom robot code goes**
- `TeamCode/build.gradle` - Only for adding team-specific dependencies
- Files in `TeamCode/src/main/res/` - Team resources (if needed)

## Programming Guidelines
When suggesting code:
1. **Use clear variable and method names** that describe what they do
2. **Add educational comments** explaining FTC-specific concepts
3. **Follow FTC programming patterns** (OpModes, hardware mapping, etc.)
4. **Suggest defensive programming** practices (null checks, try-catch for hardware)
5. **Explain FTC-specific concepts** like:
   - OpModes (Autonomous vs TeleOp)
   - Hardware mapping and configuration
   - Game controller input handling
   - Motor and servo control
   - Sensor reading and processing

Remember: The goal is to help students learn robotics programming while building competitive robots for FIRST Tech Challenge!

## OpMode Architecture

### Annotations: Declaring OpMode Type

Annotations declare the **type** of OpMode and control how it appears in the Driver Station:

- **`@TeleOp`**: Marks an OpMode as a teleoperated (driver-controlled) program
  - Example: `@TeleOp(name="My TeleOp", group="TeleOp")`
  - Used during the driver-controlled portion of a match

- **`@Autonomous`**: Marks an OpMode as an autonomous program
  - Example: `@Autonomous(name="My Autonomous", group="Autonomous")`
  - Used during the autonomous period at the start of a match

- **`@Disabled`**: Prevents the OpMode from appearing in the Driver Station menu
  - Used for sample code or work-in-progress OpModes

### Base Class: Use `OpMode` for This Repository

**This repository uses the `OpMode` base class exclusively.** Get comfortable with `OpMode` - it's what you'll use for all robot code in this repo.

The `OpMode` base class provides an **event-driven, iterative execution model** with lifecycle methods:

```java
@TeleOp(name="My TeleOp", group="TeleOp")
public class MyTeleOp extends OpMode {
    @Override
    public void init() {
        // Called once when INIT is pressed
    }
    
    @Override
    public void init_loop() {
        // Called repeatedly between INIT and START
    }
    
    @Override
    public void start() {
        // Called once when START is pressed
    }
    
    @Override
    public void loop() {
        // Called repeatedly during the active period
        // This is where most of your code goes
    }
    
    @Override
    public void stop() {
        // Called once when STOP is pressed
    }
}
```

**Why `OpMode` is better:**
- Non-blocking: The `loop()` method is called repeatedly, allowing for responsive control
- Better for complex state machines and real-time control
- More efficient handling of gamepad input and telemetry
- Better performance characteristics for competition use

**Note about `LinearOpMode`:** You might see `LinearOpMode` in online examples or tutorials. `LinearOpMode` uses a sequential, blocking execution model that is easier for absolute beginners to understand, but it's less responsive and harder to use for complex robot behaviors. **Do not use `LinearOpMode` in this repository.** Stick with `OpMode` - it's the better choice for competitive robotics.

## Best Practices

1. **Always use `OpMode`**: All robot code in this repository should extend `OpMode`

2. **Use Appropriate Annotations**: Choose `@TeleOp` or `@Autonomous` based on when the OpMode should run

3. **Sample Code**: When creating sample code, use the `@Disabled` annotation to prevent it from appearing in the Driver Station until ready

4. **Naming Conventions**: Follow the FTC naming conventions:
   - Basic: Minimal functional examples
   - Sensor: Sensor-specific demonstrations
   - Robot: Examples with a simple drive base
   - Concept: Demonstrations of specific programming concepts

5. **Code Organization**: Structure your code to take advantage of the `OpMode` lifecycle methods (`init()`, `loop()`, `stop()`)
