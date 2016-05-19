<?php
$response = array();
require_once 'connectDB.php';
// connecting to db
$db = new DB_CONNECT();
	
if (isset($_POST["para1"])){
		
	$hdrId = $_POST["para1"] ;
	$itmId = $_POST["para2"];
	$qty = $_POST["para3"];
	$respond = $_POST["para4"];
	
	$query = "INSERT INTO HISTRNSDTL (HISDTLHDRID,HISDTLITMID,HISDTLQTY,HISDTLRESPON) 
				VALUES('$hdrId','$itmId','$qty','$respond')";
	
	mysql_query($query) or die(mysql_error());	
	 
}
else {
	// required field is missing
	$response["success"] = 0;
	$response["message"] = "Required field(s) is missing";
 
	// echoing JSON response
	echo json_encode($response);
}
	

?>