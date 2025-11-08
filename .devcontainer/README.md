# Dev Container Setup

This project uses a Dev Container for consistent development environments across all team members.

## Prerequisites

### Install Docker Desktop

**Windows & Mac:**
- Download from: https://www.docker.com/products/docker-desktop
- Run the installer and start Docker Desktop

**Linux:**
- Follow instructions at: https://docs.docker.com/engine/install/
- Choose your distribution from the left sidebar

## Getting Started

1. **Open Project in Container**:
   - Open this folder in VS Code
   - Press `Cmd+Shift+P` (Mac) or `Ctrl+Shift+P` (Windows/Linux)
   - Type "Dev Containers: Reopen in Container"
   - Press Enter
   - Wait for the container to build (first time takes 5-10 minutes)

## What Gets Installed

The dev container automatically installs the latest versions of:
- Java 17 (Microsoft official devcontainer image)
- Android SDK Command Line Tools (latest from Google)
- Latest Android Platform APIs and Build Tools
- Gradle support
- VS Code Java and Docker extensions

## Platform-Specific Verification

After the container builds successfully, you can verify functionality with these commands:

#### 1. Verify Environment
```bash
# Check Java and Android SDK
java -version
echo $ANDROID_SDK_ROOT
ls -la $ANDROID_SDK_ROOT

# Check Gradle
./gradlew --version
```

#### 2. Test Java Compilation (Works on ALL platforms)
```bash
# Clean and compile Java code
./gradlew clean
./gradlew :FtcRobotController:compileDebugJavaWithJavac :FtcRobotController:compileReleaseJavaWithJavac

# Verify success - should show 200+ compiled class files
find . -name "*.class" -type f | wc -l
```

#### 3. Test APK Building (Platform dependent)

**On Windows/Intel Mac/Linux x86_64:**
```bash
# These should build complete APKs
./gradlew assembleDebug
./gradlew assembleRelease
```

**On Mac Apple Silicon/Linux ARM64:**
```bash
# These will fail at packaging (expected), but Java compilation succeeds
./gradlew assembleDebug  # Fails with AAPT2 errors - this is normal
```

### Expected Results by Platform

- **Windows/Intel Mac/Linux x86_64**: ✅ Full functionality including APK builds
- **Mac Apple Silicon/Linux ARM64**: ✅ Java compilation and development, ❌ APK packaging
- **All platforms**: ✅ IntelliSense, debugging, VS Code integration

### Success Criteria

✅ Container builds without errors  
✅ Java compilation works (200+ .class files generated)  
✅ VS Code shows proper IntelliSense and error detection  
✅ APK builds work (on x86_64 platforms) OR Java compilation succeeds (on ARM64)

Your setup provides a consistent development environment for FTC robotics! 🤖
