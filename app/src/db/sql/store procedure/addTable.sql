DELIMITER //
CREATE PROCEDURE addTable (IN table_num int,IN cus_num int,OUT table_id int) 
BEGIN
DECLARE found int;

SELECT HISHDRTBLNO INTO found
FROM histrnshdr WHERE HISHDRTBLNO = table_num AND HISTRNSTAT = 'OPEN';

IF found IS NULL THEN
INSERT INTO  histrnshdr (HISHDRTBLNO,HISTRNCUS,HISHDRTOTALPRICE) VALUES(table_num,cus_num,cus_num*239);
SET table_id = LAST_INSERT_ID();
ELSE SET table_id = 0; 
END IF;

END; //
DELIMITER ;

#CALL addTable(1,5,@tid);
#SELECT @tid;