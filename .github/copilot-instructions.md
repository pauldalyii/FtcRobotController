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

## Header Comments and Copyright

**IMPORTANT:** Do NOT replicate FIRST's copyright header comments in team code.

FIRST Tech Challenge SDK sample files contain copyright headers like this:
```
/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * ...
 */
```

**These copyright headers should ONLY appear in FIRST-provided sample code, NOT in team code.**

### Guidelines for Team Code Headers

- Header comments are **not required** for team code
- It is **acceptable to have no header comments at all** in team code files
- If teams want to add header comments, they should:
  - Keep them simple and focused on describing the purpose of the file
  - NOT include copyright statements claiming ownership by FIRST
  - NOT replicate the BSD-style license text from FIRST samples
- Team code files can start directly with package declarations and imports

## Summary

Remember: The goal is to help students learn robotics programming while building competitive robots for FIRST Tech Challenge!