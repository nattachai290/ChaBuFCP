<?php
$response = array();
require_once 'connectDB.php';
// connecting to db
$db = new DB_CONNECT();
	
if (isset($_POST["name"])||isset($_POST["id"])){
		
	$param=null;
	$query = "SELECT * FROM ADMIN ";
	
	if (isset($_POST["name"])){
	 $param = mysql_escape_string($_POST['name']);	
	 $query = $query."WHERE USERNAME = '$param'";
	} 
	
	else if (isset($_POST["id"])){
	 $param = mysql_escape_string($_POST['id']);	
	 $query = $query."WHERE ADMID = '$param'";		 
	}	
	
	$result = mysql_query($query)  or die(mysql_error());
	
	  if (!empty($result)) {
		   // check for empty result
		   	$result = mysql_fetch_array($result);
 
			$admin = array();
			$admin["userId"] = $result["ADMID"];
			$admin["username"] = $result["USERNAME"];
			$admin["pwd"] = $result["PWD"];
			$admin["fname"] = $result["FNAME"];
			$admin["lname"] = $result["LNAME"];           
			$admin["position"] = $result["POSITION"];
			$admin["sex"] = $result["SEX"];
			// success
			$response["success"] = 1;
 
			// user node
			$response["admin"] = array();
 
			array_push($response["admin"], $admin);
 
			// echoing JSON response
			echo json_encode($response);

	  }
	  else {
		// no product found
		$response["success"] = 0;
		$response["message"] = "Data No found2 Post<".$_POST["name"]." >";
 
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