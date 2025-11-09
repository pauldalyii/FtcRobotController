# GitHub Actions Workflows

This directory contains automated workflows for the FtcRobotController project.

## Build and Test APKs Workflow

**File:** `build-apks.yml`

### Purpose

This workflow automates the verification of building both debug and release APKs for the FTC Robot Controller application. It ensures that both build variants can be successfully compiled without errors.

### When It Runs

The workflow is triggered on:
- **Push** events to the `master` or `main` branches
- **Pull request** events targeting the `master` or `main` branches
- **Manual trigger** via the GitHub Actions interface (workflow_dispatch)

### What It Does

1. **Checkout Code**: Retrieves the latest code from the repository
2. **Set up JDK 17**: Configures Java Development Kit 17 (Temurin distribution) with Gradle caching
3. **Grant Execute Permission**: Ensures the Gradle wrapper script can be executed
4. **Build Debug APK**: Executes `./gradlew assembleDebug` to build the debug variant
5. **Verify Debug APK**: Confirms the debug APK file was created successfully
6. **Build Release APK**: Executes `./gradlew assembleRelease` to build the release variant
7. **Verify Release APK**: Confirms the release APK file was created successfully
8. **Upload Artifacts**: Saves both APKs as GitHub Actions artifacts for 7 days
9. **Build Summary**: Generates a summary with APK details in the workflow run

### Success Criteria

The workflow is considered successful when:
- ✅ The debug APK is built without errors
- ✅ The debug APK file exists in the build output directory
- ✅ The release APK is built without errors
- ✅ The release APK file exists in the build output directory

### Artifacts

The workflow uploads the following artifacts:
- **debug-apk**: The compiled debug APK file
- **release-apk**: The compiled release APK file

Artifacts are retained for 7 days and can be downloaded from the workflow run page.

### Viewing Results

To view the workflow results:
1. Go to the repository's "Actions" tab
2. Select the "Build and Test APKs" workflow
3. Click on a specific workflow run to see details
4. The summary will show APK sizes and locations
5. Download artifacts if needed

### Troubleshooting

If the workflow fails:
1. Check the workflow logs for specific error messages
2. Verify that all dependencies are correctly specified
3. Ensure the Gradle build configuration is valid
4. Check for any merge conflicts or syntax errors in build files

### Local Testing

To test the build locally before pushing:
```bash
# Build debug APK
./gradlew assembleDebug

# Build release APK
./gradlew assembleRelease

# Verify APKs exist
find . -name "*-debug.apk" -o -name "*-release.apk"
```

### Requirements

- Java 17 or compatible version
- Android SDK (handled automatically by Gradle)
- Gradle wrapper (included in repository)
