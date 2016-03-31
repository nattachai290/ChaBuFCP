<?php
$response = array();
require_once 'connectDB.php';
// connecting to db
$db = new DB_CONNECT();
	
if (isset($_POST["name"])||isset($_POST["id"])){
		
	$param=null;
	$query = "SELECT * FROM ITMGNL ";
	
	if (isset($_POST["name"])){
	 $param = mysql_escape_string($_POST['name']);	
	 $query = $query."WHERE ITMNAME = '$param'";
	} 
	
	else if (isset($_POST["id"])){
	 $param = mysql_escape_string($_POST['id']);	
	 $query = $query."WHERE ITMID = '$param'";		 
	}	
	
	$result = mysql_query($query)  or die(mysql_error());
	
	  if (!empty($result)) {
		   // check for empty result
		   
		   
		if (mysql_num_rows($result) > 0){
			 $result = mysql_fetch_array($result);
 
			$itmgnl = array();
			$itmgnl["itmId"] = $result["ITMID"];
			$itmgnl["itmName"] = $result["ITMNAME"];
			$itmgnl["itmQty"] = $result["ITMQTY"];
			$itmgnl["itmType"] = $result["ITMTYPE"];			
			// success
			$response["success"] = 1;
 
			// user node
			$response["itmgnl"] = array();
 
			array_push($response["itmgnl"], $itmgnl);
 
			// echoing JSON response
			echo json_encode($response);
		}
		else {
			// no product found
			$response["success"] = 0;
			$response["message"] = "Data No found1 ";
 
			// echo no users JSON
			echo json_encode($response);
		}  	 
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