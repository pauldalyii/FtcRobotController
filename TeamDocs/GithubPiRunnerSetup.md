# Setting Up a Raspberry Pi as a GitHub Runner for FTC/Rev Robotics Control Hubs
This guide will walk you through the steps to set up a brand new Raspberry Pi as a GitHub runner capable of deploying APKs to FTC/Rev Robotics Control Hubs.

## Step 1: Download and Install the Latest Raspbian OS Image

1. **Download Raspberry Pi Imager**:
  - Visit the [Raspberry Pi website](https://www.raspberrypi.org/software/) and download the Raspberry Pi Imager for your operating system.

2. **Install Raspberry Pi Imager**:
  - Follow the installation instructions for your operating system to install the Raspberry Pi Imager.

3. **Run Raspberry Pi Imager**:
  - Open the Raspberry Pi Imager application.

4. **Choose OS**:
  - Click on "Choose OS" and select "Raspberry Pi OS (64-bit)" or the latest recommended version.

5. **Choose Storage**:
  - Insert your microSD card into your computer and select it under "Choose Storage".

6. **Set Username and Password**:
  - In the Raspberry Pi Imager, click on the settings icon (gear) and set a username and password for your team.

7. **Enable SSH**:
  - In the same settings menu, navigate to the SSH tab and enable SSH.

7. **Write the Image**:
  - Click "Write" to begin writing the Raspbian OS image to the microSD card. This process may take a few minutes.

8. **Insert microSD Card**:
  - Once the writing process is complete, safely eject the microSD card from your computer and insert it into your Raspberry Pi.

## Step 2: Boot the Raspberry Pi and Find its IP Address

1. **Boot the Raspberry Pi**:
  - Connect your Raspberry Pi to a power source. If you are using a headless setup (no monitor or keyboard), ensure it is connected to your network via Ethernet or Wi-Fi.
  2. **Find the IP Address**:
    - Use a network scanning tool like `nmap` or check your router's connected devices list to find the IP address assigned to your Raspberry Pi.
    - **Warning**: Using `nmap` on certain networks may violate network policies and could get you blocked. Always ensure you have permission to scan the network.

## Step 3: SSH into the Raspberry Pi

1. **SSH into the Device**:
  - Open a terminal on your computer and run the following command, replacing `<IP_ADDRESS>` with the IP address of your Raspberry Pi:

    ```sh
    ssh pi@<IP_ADDRESS>
    ```

2. **Default Password**:
  - When prompted, enter the default password `raspberry`.

## Step 4: Update and Upgrade the System

```sh
sudo apt update
sudo apt full-upgrade -y
```

## Step 5: Set Hostname

```sh
sudo nmcli general hostname botsmiths-github-runner-01
sudo shutdown -r now
```

## Step 6: Configure Network Connections

### Create a Connection Profile for `eth0` with DHCP

```sh
sudo nmcli connection add type ethernet con-name eth0-dhcp ifname eth0 ipv4.method auto
```

### Create a Connection Profile for `eth0` with a Static IP

```sh
sudo nmcli connection add type ethernet con-name eth0-static ifname eth0 ipv4.addresses "192.168.186.93/24" ipv4.method manual
```

### Create Prioritized Wi-Fi Connections for `wlan0`
Note: The lower the number, the higher the priority.

```sh
sudo nmcli connection add type wifi con-name "<connection1-name>" ifname wlan0 ssid "<ssid>"
sudo nmcli connection modify "<connection1-name>" wifi-sec.key-mgmt wpa-psk wifi-sec.psk "<password>" connection.autoconnect-priority 40 ipv4.method auto

#add a backup connection
sudo nmcli connection add type wifi con-name "<connection2-name>" ifname wlan0 ssid "<ssid>"
sudo nmcli connection modify "<connection2-name>" wifi-sec.key-mgmt wpa-psk wifi-sec.psk "<password>" connection.autoconnect-priority 50 ipv4.method auto
```

### Enable NetworkManager and Disable `dhcpcd`

```sh
sudo systemctl enable NetworkManager
sudo systemctl disable dhcpcd
sudo nmcli connection up "eth0-static" && sudo nmcli connection up "<connection1-name>"
```

## Step 7: Install Android ADB Tool

```sh
sudo apt install -y adb
```

## Step 8: Install GitHub Runner

1. **Create Directories**:

   ```sh
   mkdir -p ~/Scripts/GitHub/actions-runner
   cd ~/Scripts/GitHub/actions-runner
   ```

2. **Download GitHub Runner**:

  - Visit the [GitHub Actions Runner releases page](https://github.com/actions/runner/releases) to find the latest version for your architecture. For Raspberry Pi, you will typically need the `arm64` version.
  - Replace the version number and URL in the commands below with the latest version from the releases page.

  ```sh
  curl -o actions-runner-linux-arm64-<VERSION>.tar.gz -L https://github.com/actions/runner/releases/download/v<VERSION>/actions-runner-linux-arm64-<VERSION>.tar.gz
  echo "<SHA256_CHECKSUM>  actions-runner-linux-arm64-<VERSION>.tar.gz" | shasum -a 256 -c
  tar xzf ./actions-runner-linux-arm64-<VERSION>.tar.gz
  ```

  - Ensure you replace `<VERSION>` with the actual version number and `<SHA256_CHECKSUM>` with the corresponding checksum provided on the releases page.

3. **Configure GitHub Runner**:

   ```sh
   ./config.sh --url https://github.com/<ORGANIZATION_NAME>/<REPOSITORY_NAME> --token <TOKEN> --name $HOSTNAME --labels adb --work _work --unattended --replace
   ```

## Step 9: Create a Systemd Service for GitHub Runner

1. **Create Service File**:

   ```sh
   sudo nano /etc/systemd/system/github-runner.service
   ```

2. **Add the Following Content**:

   ```ini
   [Unit]
   Description=GitHub Actions Runner
   After=network-online.target
   Wants=network-online.target

   [Service]
   Type=simple
   User=botsmiths
   WorkingDirectory=/home/botsmiths/Scripts/GitHub/actions-runner
   ExecStart=/home/botsmiths/Scripts/GitHub/actions-runner/run.sh
   Restart=always
   RestartSec=10

   [Install]
   WantedBy=multi-user.target
   ```

3. **Reload Systemd and Enable Service**:

   ```sh
   sudo systemctl daemon-reload
   sudo systemctl enable github-runner.service
   sudo systemctl start github-runner.service
   sudo systemctl status github-runner.service
   ```

Your Raspberry Pi is now set up as a GitHub runner and ready to deploy APKs to FTC/Rev Robotics Control Hubs.

## Step 10: Verify GitHub Runner is Working

1. **Check Runner Status on GitHub**:
  - Navigate to your GitHub repository.
  - Go to **Settings** > **Actions** > **Runners**.
  - Ensure your runner appears in the list and is marked as `Online`.

2. **Run a Test Workflow**:
  - Create a simple GitHub Actions workflow in your repository to test the runner. For example, create a file named `.github/workflows/test-runner.yml` with the following content:

    ```yaml
    name: Test Runner

    on: [push]

    jobs:
     test:
      runs-on: [self-hosted, adb]
      steps:
        - name: Check out repository
         uses: actions/checkout@v2
        - name: Run a command
         run: echo "Runner is working!"
    ```

3. **Push Changes to GitHub**:
  - Commit and push the new workflow file to your repository. This will trigger the workflow and use your self-hosted runner.

4. **Monitor Workflow Execution**:
  - Go to the **Actions** tab in your GitHub repository.
  - Select the running workflow to see the logs and ensure it completes successfully.

If the workflow runs successfully and you see the expected output, your GitHub runner is working correctly.
