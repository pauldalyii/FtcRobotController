# Build Number System

This repository includes a build number system that embeds version information into the APK and makes it available through a diagnostic TeleOp program.

## Build Number Format

The build number follows the format: `1.0.<YYMM>.<rev>`

- `1.0` - Major version
- `<YYMM>` - Two-digit year and month (e.g., `2511` for November 2025)
- `<rev>` - Incremental revision number (GitHub workflow run number)

Example: `1.0.2511.42` represents the 42nd build in November 2025.

## Using the Diagnostic TeleOp

To view the build number on your Driver Station:

1. Deploy the APK to your Robot Controller
2. On the Driver Station app, select the OpMode list
3. Find and select **"Build Number"** under the "Diagnostic" group
4. Initialize and start the OpMode
5. The build number will be displayed on the telemetry screen

## Accessing Build Number in Your Programs

To display the build number in your TeleOp or Autonomous programs, use the `BuildInfo` utility class:

```java
import org.firstinspires.ftc.teamcode.BuildInfo;

// In your OpMode
String buildNumber = BuildInfo.getBuildNumber();
telemetry.addData("Build", buildNumber);
```

The `BuildInfo.getBuildNumber()` method returns the build number string (e.g., "1.0.2511.42") or "Unknown" if the build number is not available.

## GitHub Actions Build Pipeline

The repository includes a GitHub Actions workflow that automatically builds the APK with the build number embedded.

### Triggering a Build

The build can be triggered in three ways:

1. **Push to main/master branch** - Automatic build on push
2. **Pull Request** - Automatic build on PR creation/update
3. **Manual Trigger** - Via the GitHub Actions "workflow_dispatch" event

To manually trigger a build:
1. Go to the repository on GitHub
2. Click "Actions" tab
3. Select "Build APK with Build Number" workflow
4. Click "Run workflow" button
5. Select the branch and click "Run workflow"

### Build Artifacts

After a successful build, the APK file will be available as a GitHub Actions artifact with the name:
`FtcRobotController-<BUILD_NUMBER>-release.apk`

The artifact is retained for 30 days and can be downloaded from the workflow run page.

## Local Development

When building locally (not via GitHub Actions), the build number defaults to `1.0.0.0-dev`.

To specify a custom build number when building locally:
```bash
export BUILD_NUMBER=1.0.2511.1
./gradlew assembleRelease
```

## Implementation Details

- The build number is stored in `BuildConfig.BUILD_NUMBER` and is accessible from Java code via the `BuildInfo` utility class
- The `BuildInfo` class provides a centralized, reusable way to access the build number from any OpMode
- The APK filename includes the build number for easy identification
- The build number is calculated automatically by the GitHub Actions workflow
- The build number retrieval uses reflection and is cached for performance
