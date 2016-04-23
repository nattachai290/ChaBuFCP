<?php
$response = array();
require_once 'connectDB.php';
// connecting to db
$db = new DB_CONNECT();
	
if (isset($_POST["para1"])){
	$table_number = $_POST["para1"] ;
	$customer_number = $_POST["para2"] ;
	$query = "CALL addTable('$table_number','$customer_number',@tableid)";
	$query2 = "SELECT @tableid";
	
	mysql_query($query) or die(mysql_error());
	$result = mysql_query($query2)  or die(mysql_error());
	
	  if (!empty($result)) {
		   // check for empty result		   
		   
			$result = mysql_fetch_array($result);
 
			$hishdr = array();
			$hishdr["hishdrId"] = $result["@tableid"];
			
			// success
			$response["success"] = 1;
 
			// user node
			$response["hishdr"] = array();
 
			array_push($response["hishdr"], $hishdr);
 
			// echoing JSON response
			echo json_encode($response);

	  }
	  else {
		// no product found
		$response["success"] = 0;
		$response["message"] = "Data No found2 ";
 
		// echo no users JSON
		echo json_encode($response);
	}
	 
}
else {
	// required field is missing
	$response["success"] = 0;
	$response["message"] = "Required field(s) is missing";
 
	// echoing JSON response
	echo json_encode($response);
}
		
		
	
	

?>