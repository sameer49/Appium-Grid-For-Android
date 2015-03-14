#!/bin/bash

#  ADB Install.sh

echo "This will install ADB and Fastboot on your computer."
echo "Root Permissions required. Please type your password."
sudo cd ~ #Gives Superuser permissions
cd "`dirname "$0"`" #CDs to script directory
echo "Changed directory to `pwd`" #Informs user of path change
echo "Moving ADB"
sudo mv Mac/adb_Mac /usr/bin/adb #Moves adb
echo "ADB Moved to /usr/bin/adb"
echo "moving Fastboot"
sudo mv Mac/fastboot_Mac /usr/bin/fastboot #Moves Fastboot
echo "Fastboot moved to /usr/bin/fastboot"
echo "You may now run Android Debug Bridge and Fastboot commands"
echo "Adding appium to /usr/bin/appium"
sudo ln -s /path/to/appium.js /usr/bin/appium
echo "Adding node to /usr/bin/node"
sudo ln -s /usr/local/bin/node /usr/bin/node
echo "Adding npm to /usr/bin/npm"
sudo ln -s /usr/local/bin/npm /usr/bin/npm 
echo "Have a nice day."