<?php
$response = array();
require_once 'connectDB.php';
// connecting to db
$db = new DB_CONNECT();
	
if (isset($_POST["name"])||isset($_POST["id"])){
		
	$param=null;
	$query = "SELECT * FROM HISTRNSDTL ";
	
	if (isset($_POST["name"])){
	 $param = mysql_escape_string($_POST['name']);	
	 $query = $query."WHERE HISHDRID = '$param'";
	} 
	
	else if (isset($_POST["id"])){
	 $param = mysql_escape_string($_POST['id']);	
	 $query = $query."WHERE HISDTLID = '$param'";		 
	}	
	
	$result = mysql_query($query)  or die(mysql_error());
	
	  if (!empty($result)) {
		   // check for empty result
		   
			$result = mysql_fetch_array($result);
 
			$hisdtl = array();
			$hisdtl["hisdtlId"] = $result["HISDTLID"];
			$hisdtl["hisdtlHdrId"] = $result["HISHDRID"];
			$hisdtl["hisdtlItmId"] = $result["HISDTLITMID"];
			$hisdtl["hisdtlQty"] = $result["HISDTLQTY"];	
			$hisdtl["hisdtlPrice"] = $result["HISDTLPRICE"];						
			$hisdtl["hisdtlTime"] = $result["HISTIME"];
			// success
			$response["success"] = 1;
 
			// user node
			$response["hisdtl"] = array();
 
			array_push($response["hisdtl"], $hisdtl);
 
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