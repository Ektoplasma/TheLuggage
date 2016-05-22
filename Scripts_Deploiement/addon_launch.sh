#!/bin/bash

addon=`sudo find / -iname "@theluggage-1.0.0.xpi"`

#Launch add-on
if [ $addon ]
then
	if [ -d "$HOME/TCell_Project/firefox/" ]
	then
		$HOME/TCell_Project/firefox/firefox $addon
	else
		echo "[ERROR] Missing directory (TCell_Project or firefox)"
		exit 1
	fi
else
	echo "[ERROR] Missing add-on (@theluggage-1.0.0.xpi)"
	exit 1
fi

exit 0

