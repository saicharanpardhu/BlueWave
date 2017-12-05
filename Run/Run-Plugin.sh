!/bin/bash
echo "[*] JAVA RUN "
set -e 
echo Setting working directory to $1
#mvn clean package 2>&1
java -jar "$1"/"$2"
echo [*] Run Complete 
