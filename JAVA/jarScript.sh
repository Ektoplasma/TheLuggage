#!/bin/bash

choice1="n"
choice2="n"
choice3="n"

echo "======================================"
echo "=             SCRIPT JAR             ="
echo "======================================"


until [ $choice1 == "o" ]
do
	echo "Nom de la classe principale (ex. Main) : "
	read class
	echo "$class ? (o/n)"
	read choice1
done

echo ""

while [ $choice2 != "o" ]; do
	echo "Chemin des librairies externes (ex. /librairies/*.jar) : "
	read chemin
	echo "$chemin ? (o/n)"
	read choice2
done

echo ""

while [ $choice3 != "o" ]; do
	echo "Nom du programme final (ex. Token.jar) : "
	read prog
	echo "$prog ? (o/n)"
	read choice3
done

echo ""
echo "======================================"
echo "===    CREATION DU MANIFEST.MF     ==="
echo "======================================"
echo ""
echo "Main-Class: $class" > MANIFEST.MF
echo "Class-Path: $chemin" >> MANIFEST.MF
echo "" >> MANIFEST.MF
echo "Done ."
echo ""
echo "======================================"
echo "===       CREATION DU .JAR         ==="
echo "======================================"
echo ""
make clean 2>/dev/null
make 2>/dev/null
jar cvmf MANIFEST.MF $prog *.class
echo ""
echo "Done."
echo ""
echo "======================================"

exit