!/bin/bash
echo "[*] JAVA TEST "
set -e
echo setting working directory to $1
mvn -Dtest="$1" "$2"
