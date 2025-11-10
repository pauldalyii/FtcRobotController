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