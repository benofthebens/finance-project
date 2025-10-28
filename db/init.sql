CREATE PLUGGABLE DATABASE EXPENSE
ADMIN USER SPRING IDENTIFIED BY PWD
FILE_NAME_CONVERT = (
    '/opt/oracle/oradata/XE/pdbseed/',
    '/opt/oracle/oradata/XE/expense'
);

CREATE PLUGGABLE DATABASE KEYCLOAK
ADMIN USER KEYCLOAK IDENTIFIED BY PWD
FILE_NAME_CONVERT = (
    '/opt/oracle/oradata/XE/pdbseed/',
    '/opt/oracle/oradata/XE/keycloak'
);

ALTER PLUGGABLE DATABASE ALL OPEN;
