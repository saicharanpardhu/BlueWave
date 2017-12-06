!/bin/bash
echo "[*] JAVA TEST "
echo $3
echo $2
echo $1
cd Task_Source/"$3"/
set -e
echo setting working directory to $1
mvn -Dtest="$1" "$2"
