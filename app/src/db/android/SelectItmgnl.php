<?php
$response = array();
require_once 'connectDB.php';
// connecting to db
$db = new DB_CONNECT();
	
if (isset($_POST["name"])||isset($_POST["id"])||isset($_POST["type"])){
	mysql_query("SET NAMES UTF8");
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
	else if (isset($_POST["type"])){
    	 $param = mysql_escape_string($_POST['type']);
    	 $query = $query."WHERE ITMTYPE = '$param'";
    }
	$result = mysql_query($query)  or die(mysql_error());
	
	  if (!empty($result)) {
		   // check for empty result
		  $response["itmgnl"] = array();
			
			 while ($row = mysql_fetch_array($result)) {
				// temp user array
					$itmgnl = array();
					$itmgnl["itmId"] = $row["ITMID"];
					$itmgnl["itmName"] = $row["ITMNAME"];
					$itmgnl["itmQty"] = $row["ITMQTY"];
					$itmgnl["itmType"] = $row["ITMTYPE"];	
		 
				// push single product into final response array
				array_push($response["itmgnl"], $itmgnl);
			}
			
			// success
			$response["success"] = 1;
 
		
 
			
 
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