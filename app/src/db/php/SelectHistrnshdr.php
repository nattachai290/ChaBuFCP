<?php
$response = array();
require_once 'connectDB.php';
// connecting to db
$db = new DB_CONNECT();
$query = "SELECT * FROM HISTRNSHDR WHERE HISTRNSTAT = 'OPEN'";

$result = mysql_query($query)  or die(mysql_error());

  if (!empty($result)) {
	   // check for empty result
	   
	$response["hishdr"] = array();
	
		 while ($row = mysql_fetch_array($result)) {
			// temp user array
				$hishdr = array();
				$hishdr["hishdrId"] = $row["HISHDRID"];
				$hishdr["hishdrTblNo"] = $row["HISHDRTBLNO"];
				$hishdr["hishdrPrice"] = $row["HISHDRTOTALPRICE"];
				$hishdr["hishdrCus"] = $row["HISTRNCUS"];				
				$hishdr["hishdrTime"] = $row["HISHDRTIMEEAT"];		
	 
			// push single product into final response array
			array_push($response["hishdr"], $hishdr);
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
	 

	
		
	
	

?>