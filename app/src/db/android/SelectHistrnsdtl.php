<?php
$response = array();
require_once 'connectDB.php';
// connecting to db
$db = new DB_CONNECT();

	$query = "SELECT HISHDRTBLNO,ITMNAME,HISDTLQTY,CONCAT(FNAME,' ',LNAME) HISDTLRESPON,DATE_FORMAT(HISTIME,'%H:%i') HISTIME
			FROM HISTRNSHDR  
			JOIN HISTRNSDTL ON HISHDRID=HISDTLHDRID
			JOIN ITMGNL ON HISDTLITMID=ITMID
			JOIN ADMIN ON  ADMID = HISDTLRESPON
			WHERE HISDTLSTAT = 1 AND
			DATE_FORMAT(HISTIME,'%y-%m-%d') = CURDATE()";
	
	$result = mysql_query($query)  or die(mysql_error());
	
	  if (!empty($result)) {
		   // check for empty result
		   	$response["hisdtl"] = array();
			 while ($row = mysql_fetch_array($result)) {	
 
				$hisdtl = array();
				$hisdtl["tableNo"] = $row["HISHDRTBLNO"];
				$hisdtl["itmName"] = $row["ITMNAME"];
				$hisdtl["qty"] = $row["HISDTLQTY"];	
				$hisdtl["people"] = $row["HISDTLRESPON"];						
				$hisdtl["time"] = $row["HISTIME"];
				array_push($response["hisdtl"], $hisdtl);
			 }
			// success
			$response["success"] = 1;
 
			// user node
		
 
		
 
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