#!/bin/bash

# openjdk-7-jre
sudo -S apt-get install openjdk-7-jre -y

# GNU ARM Embedded Toolchain
#sudo -S apt-get install gcc-arm-none-eabi gdb-arm-none-eabi -y

# OpenOCD
#sudo -S apt-get install openocd -y

# Permissions
sudo usermod -aG dialout $USER
sudo -S sh -c 'cat <<EOF > /etc/udev/rules.d/99-stlinkv2.rules
SUBSYSTEMS=="usb", ATTRS{idVendor}=="0483", ATTRS{idProduct}=="3748", MODE="0666"
EOF'
sudo -S sh -c 'cat <<EOF > /etc/udev/rules.d/98-token.rules
SUBSYSTEM=="usb", ATTRS{idVendor}=="0483", ATTRS{idProduct}=="5740", MODE="0666", GROUP="dialout", ENV{ID_MM_DEVICE_IGNORE}="1"
EOF'
sudo -S udevadm control --reload-rules

#Directory for the project
mkdir $HOME/TCell_Project/
cd $HOME/TCell_Project/

#Download project on dropbox (token)
wget https://www.dropbox.com/s/6lyhdk9plylgs4i/theluggage_token_v2.jar

#Download project on dropbox (add-on)
wget https://www.dropbox.com/s/3nyq5yg3qozkuy9/%40theluggage-1.0.0.xpi

#Download firefox and untar it
wget -O firefox.tar.bz2 "https://download.mozilla.org/?product=firefox-latest&os=linux64&lang=en-US" 
tar xvf firefox.tar.bz2

#Turn xpinstall.signatures.required to false
if [ `cat $HOME/.mozilla/firefox/*.default/prefs.js|grep "xpinstall.signatures.required"|wc -l` -ne 1 ]
then
	echo 'user_pref("xpinstall.signatures.required", false);' >> $HOME/.mozilla/firefox/*.default/prefs.js
fi

#Info
echo ""
echo "===================================INFOS==================================="
echo ""
echo "DONE"
echo ""
echo "[!] Reboot in 10 s ..."
echo ""
echo "To launch the project after the reboot :"
echo "	1) Plug your TCell"
echo "	2) $ bash addon_launch.sh"
echo "	3) (In an other terminal) $ bash token_launch.sh <persistence_mode>"
echo "	4) Enjoy"
echo ""
echo "==========================================================================="
echo ""

#Reboot
sleep 10
sudo reboot
