<?php
$response = array();
require_once 'connectDB.php';
// connecting to db
$db = new DB_CONNECT();
if (isset($_POST["para1"])){
	 
	 $param = mysql_escape_string($_POST["para1"]);	
	 $query = "UPDATE HISTRNSHDR SET HISTRNSTAT = 'CLOSE' WHERE HISHDRID = '$param'";
	 mysql_query($query)  or die(mysql_error());
}
		
	
	

?>