<?php
$response = array();
require_once 'connectDB.php';
// connecting to db
$db = new DB_CONNECT();
if (isset($_POST["para1"])){
	 
	 $param = mysql_escape_string($_POST["para1"]);	
	 $sql = "SELECT HISHDRID
	 FROM HISTRNSHDR
	 WHERE HISHDRID = '$param' 
	 AND HISTRNSTAT = 'OPEN'
	 AND DATE_FORMAT(DATE_ADD(`HISHDRTIME`,INTERVAL 2 HOUR),'%H:%i:%s') < CURTIME() ";
	 $result =  mysql_query($sql)  or die(mysql_error());
	 
	 if (!empty($result)) {
		$response["success"] = 1;
	  }
	  
	 $query = "UPDATE HISTRNSHDR SET HISTRNSTAT = 'CLOSE' WHERE HISHDRID = '$param'";
	 mysql_query($query)  or die(mysql_error());
}
		
	
	

?>