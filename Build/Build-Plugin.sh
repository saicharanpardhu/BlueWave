!/bin/bash

echo "[*] JAVA BUILD "

set -e 

echo Setting working directory to $1

cd "$1"

#mvn clean package 2>&1
mvn clean package -DskipTests=true

echo [*] Build Done 