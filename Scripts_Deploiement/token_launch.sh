#!/bin/bash

project=`sudo find / -iname "theluggage_token_v2.jar"`
try_project=`sudo find / -iname "theluggage_token_v2.jar"|wc -l`
try_addon=`sudo find / -iname "@theluggage-1.0.0.xpi"|wc -l`
try_token=`ls /dev/ttyACM*|wc -l`
token_port=`ls /dev/ttyACM*`

#Tests if all requirements are satisfied
if [ $try_token -ne 1 ]
then
	echo "[ERROR] Please plug your TCell before use this script"
	exit 1

elif [ $try_project -ne 1 ]
then
	echo "[ERROR] theluggage_token_v2.jar doesn't exist on your computer"
	exit 1
elif [ $try_addon -ne 1 ]
then
	echo "[ERROR] @theluggage-1.0.0.xpi doesn't exist on your computer"
	exit 1
fi

#Launch app JAVA
if [ $# == 1 ]
then
	if [ $1 == "Persistent" ]
	then
		echo "[INFO] Persistent mode activated"
		java -jar $project $token_port $1
	elif [ $1 == "NotPersistent" ]
	then
		echo "[INFO] NotPersistent mode activated"
		java -jar $project $token_port $1
	else
		echo "[ERROR] Please choose between 'Persistent' or 'NotPersistent'"
		exit 1
	fi
else
	echo "[ERROR] usage : $0 <persistence_mode>"
	exit 1
fi

exit 0
