#!/bin/bash
source /home/oracle/.bashrc

echo "Waiting for Oracle to be available..."
until echo "SELECT 1 FROM dual;" | sqlplus -s sys/${ORACLE_PWD}@localhost:1521/XEPDB1 as sysdba > /dev/null; do
  echo "Still waiting..."
  sleep 5
done

echo "Running init.sql..."
sqlplus sys/${ORACLE_PWD}@localhost:1521/XEPDB1 as sysdba @/opt/oracle/init.sql

# Keep container running (optional, depending on your app)
tail -f /dev/null