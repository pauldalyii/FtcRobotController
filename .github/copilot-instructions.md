# GitHub Copilot Instructions for FTC Robot Controller

## OpMode Architecture Overview

When developing OpModes for FTC Robot Controller, it's crucial to understand the distinction between **annotations** and **base classes**.

### Annotations: Declaring OpMode Type

Annotations are used to declare the **type** of OpMode and control how it appears in the Driver Station:

- **`@TeleOp`**: Marks an OpMode as a teleoperated (driver-controlled) program
  - Example: `@TeleOp(name="My TeleOp", group="TeleOp")`
  - Used during the driver-controlled portion of a match

- **`@Autonomous`**: Marks an OpMode as an autonomous program
  - Example: `@Autonomous(name="My Autonomous", group="Autonomous")`
  - Used during the autonomous period at the start of a match

- **`@Disabled`**: Prevents the OpMode from appearing in the Driver Station menu
  - Used for sample code or work-in-progress OpModes

### Base Classes: Choosing the Implementation Pattern

The base class determines **how your OpMode executes**. This is a separate decision from choosing the annotation:

#### `OpMode` (Event-Driven/Iterative) - **RECOMMENDED FOR MOST IMPLEMENTATIONS**

The `OpMode` base class provides an **event-driven, iterative execution model**:

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

**Advantages of `OpMode`:**
- Non-blocking: The `loop()` method is called repeatedly, allowing for responsive control
- Better for complex state machines and real-time control
- More efficient handling of gamepad input and telemetry
- Recommended by FTC for production robot code
- Better performance characteristics for competition use

**Use `OpMode` for:**
- Competition robot code (TeleOp and Autonomous)
- Complex robot control logic
- State machine implementations
- Any code requiring responsive, real-time control

#### `LinearOpMode` (Sequential) - **FOR BASIC DIAGNOSTIC PROGRAMS ONLY**

The `LinearOpMode` base class provides a **sequential, blocking execution model**:

```java
@TeleOp(name="My Linear TeleOp", group="Linear")
public class MyLinearTeleOp extends LinearOpMode {
    @Override
    public void runOpMode() {
        // Setup code
        
        waitForStart(); // Blocks until START is pressed
        
        // Sequential code - executes line by line
        while (opModeIsActive()) {
            // Loop body
        }
    }
}
```

**Limitations of `LinearOpMode`:**
- Blocking: Code executes sequentially, which can lead to unresponsive behavior
- Harder to implement complex control logic
- Can miss gamepad input or sensor updates if code blocks too long
- Not recommended for production competition code

**Use `LinearOpMode` ONLY for:**
- Simple diagnostic programs
- Basic sensor testing
- Educational examples for beginners
- Quick prototypes and experiments

### Key Principle: Annotations and Base Classes are Independent

Remember that annotations and base classes serve different purposes and can be combined:

- `@TeleOp` can be used with **either** `OpMode` or `LinearOpMode`
- `@Autonomous` can be used with **either** `OpMode` or `LinearOpMode`

**However, for production code, strongly prefer:**
- `@TeleOp` with `OpMode` for teleoperated programs
- `@Autonomous` with `OpMode` for autonomous programs

Reserve `LinearOpMode` for the most basic diagnostic and testing scenarios.

## Best Practices

1. **Default to `OpMode`**: Unless you have a specific reason to use `LinearOpMode` (basic diagnostics), always extend `OpMode`

2. **Use Appropriate Annotations**: Choose `@TeleOp` or `@Autonomous` based on when the OpMode should run, not based on the base class

3. **Sample Code**: When creating sample code, use the `@Disabled` annotation to prevent it from appearing in the Driver Station until ready

4. **Naming Conventions**: Follow the FTC naming conventions:
   - Basic: Minimal functional examples
   - Sensor: Sensor-specific demonstrations
   - Robot: Examples with a simple drive base
   - Concept: Demonstrations of specific programming concepts

5. **Code Organization**: Structure your code to take advantage of the `OpMode` lifecycle methods (`init()`, `loop()`, `stop()`)
