INSERT INTO "TOKENS" (ID, PASSWORD, SECURE_TOKEN, EMAIL, CREATED_DATE, EXPIRE_DATE, ACTIVE) VALUES(1, '123','yquiwyeiquwe' ,   '111@yahoo.com', TO_DATE('2017-02-11', 'yyyy-mm-dd'), TO_DATE('2017-03-11', 'yyyy-mm-dd'), true);
INSERT INTO "TOKENS" (ID, PASSWORD, SECURE_TOKEN, EMAIL, CREATED_DATE, EXPIRE_DATE, ACTIVE) VALUES(2, '123','yquiwy232eiquwe', '222@yahoo.com', TO_DATE('2017-02-12', 'yyyy-mm-dd'), TO_DATE('2017-03-11', 'yyyy-mm-dd'), true);
INSERT INTO "TOKENS" (ID, PASSWORD, SECURE_TOKEN, EMAIL, CREATED_DATE, EXPIRE_DATE, ACTIVE) VALUES(3, '123','yqu345iwyeiquwe', '333@yahoo.com', TO_DATE('2017-02-13', 'yyyy-mm-dd'), TO_DATE('2017-03-11', 'yyyy-mm-dd'), true);
COMMIT;