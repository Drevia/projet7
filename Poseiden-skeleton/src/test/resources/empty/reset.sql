SET REFERENTIAL_INTEGRITY FALSE;
TRUNCATE TABLE rating RESTART IDENTITY;
TRUNCATE TABLE curvepoint RESTART IDENTITY;
TRUNCATE TABLE rulename RESTART IDENTITY;
TRUNCATE TABLE bidlist RESTART IDENTITY;
TRUNCATE TABLE trade RESTART IDENTITY;
TRUNCATE TABLE users RESTART IDENTITY;
SET REFERENTIAL_INTEGRITY TRUE;