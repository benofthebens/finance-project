DECLARE
    pdb_exists INTEGER;
BEGIN
    SELECT COUNT(*) INTO pdb_exists
    FROM CDB_PDBS
    WHERE PDB_NAME = 'KEYCLOAK';

    IF pdb_exists = 0 THEN
        EXECUTE IMMEDIATE 'CREATE PLUGGABLE DATABASE KEYCLOAK
        ADMIN USER KEYCLOAK IDENTIFIED BY PWD
        FILE_NAME_CONVERT = (
            ''/opt/oracle/oradata/XE/pdbseed/'',
            ''/opt/oracle/oradata/XE/keycloak/''
        )';
        DBMS_OUTPUT.PUT_LINE('Created KEYCLOAK PDB');
    END IF;

    SELECT COUNT(*) INTO pdb_exists
    FROM CDB_PDBS
    WHERE PDB_NAME = 'EXPENSE';

    IF pdb_exists = 0 THEN
        EXECUTE IMMEDIATE 'CREATE PLUGGABLE DATABASE EXPENSE
            ADMIN USER SPRING IDENTIFIED BY PWD
            FILE_NAME_CONVERT = (
                ''/opt/oracle/oradata/XE/pdbseed/'',
                ''/opt/oracle/oradata/XE/expense/''
                )';
        DBMS_OUTPUT.PUT_LINE('Created Expense PDB');
    END IF;

    EXECUTE IMMEDIATE 'ALTER PLUGGABLE DATABASE ALL OPEN';
END;
/