# Official Microsoft Java 17 devcontainer image
FROM mcr.microsoft.com/devcontainers/java:17

# Set Android SDK environment variables
ENV ANDROID_HOME=/opt/android-sdk \
    ANDROID_SDK_ROOT=/opt/android-sdk

# Install system dependencies (curl for API calls, jq for JSON parsing)
RUN apt-get update \
    && apt-get -y install --no-install-recommends wget unzip curl jq \
    && apt-get clean -y && rm -rf /var/lib/apt/lists/*

# Fetch latest Android SDK Command Line Tools version from Google's repository
RUN LATEST_TOOLS_URL=$(curl -s https://dl.google.com/android/repository/repository2-3.xml | \
    grep -o 'commandlinetools-linux-[0-9]*_latest\.zip' | \
    head -1) && \
    TOOLS_VERSION=$(echo $LATEST_TOOLS_URL | grep -o '[0-9]*') && \
    echo "Using Android SDK Tools version: $TOOLS_VERSION" && \
    mkdir -p ${ANDROID_SDK_ROOT}/cmdline-tools && \
    cd ${ANDROID_SDK_ROOT}/cmdline-tools && \
    wget -q https://dl.google.com/android/repository/$LATEST_TOOLS_URL && \
    unzip $LATEST_TOOLS_URL && \
    mv cmdline-tools latest && \
    rm $LATEST_TOOLS_URL

# Add Android SDK tools to PATH
ENV PATH=${PATH}:${ANDROID_SDK_ROOT}/cmdline-tools/latest/bin:${ANDROID_SDK_ROOT}/platform-tools

# Accept Android SDK licenses
RUN yes | sdkmanager --licenses

# Install Android SDK components for FTC development
# Automatically detect latest available platforms and build-tools
RUN echo "Installing latest Android SDK components..." && \
    LATEST_BUILD_TOOLS=$(sdkmanager --list | grep "build-tools" | grep -v "rc\|alpha\|beta" | tail -1 | awk '{print $1}' | cut -d';' -f2) && \
    LATEST_PLATFORM=$(sdkmanager --list | grep "platforms;android-" | grep -v "Addon\|preview" | tail -1 | awk '{print $1}') && \
    SECOND_LATEST_PLATFORM=$(sdkmanager --list | grep "platforms;android-" | grep -v "Addon\|preview" | tail -2 | head -1 | awk '{print $1}') && \
    echo "Latest platform: $LATEST_PLATFORM" && \
    echo "Second latest platform: $SECOND_LATEST_PLATFORM" && \
    echo "Build-tools: $LATEST_BUILD_TOOLS" && \
    sdkmanager "platform-tools" \
              "$LATEST_PLATFORM" \
              "$SECOND_LATEST_PLATFORM" \
              "build-tools;$LATEST_BUILD_TOOLS"

# Change ownership of Android SDK to vscode user
RUN chown -R vscode:vscode ${ANDROID_SDK_ROOT}
