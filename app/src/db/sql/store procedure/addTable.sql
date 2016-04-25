DELIMITER //
CREATE PROCEDURE addTable (IN table_num int,IN cus_num int,OUT table_id int) 
BEGIN
DECLARE found_check int;
SELECT HISHDRTBLNO INTO found_check
FROM histrnshdr WHERE HISHDRTBLNO = table_num AND HISTRNSTAT = 'OPEN';

IF found_check IS NULL THEN
INSERT INTO  histrnshdr (HISHDRTBLNO,HISTRNCUS,HISHDRTOTALPRICE,HISHDRRESPON,HISHDRTIMEEAT) VALUES(table_num,cus_num,cus_num*239,respons,CONCAT(DATE_FORMAT(CURRENT_TIME(),'%H:%i'),' - ',DATE_FORMAT(DATE_ADD(CURRENT_TIME(),INTERVAL 2 HOUR),'%H:%i') ));
SET table_id = LAST_INSERT_ID();

ELSE SET table_id = 0; 
END IF;

END; //
DELIMITER ;

#CALL addTable(1,5,@tid);
#SELECT @tid;