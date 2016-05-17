<?php
$response = array();
require_once 'connectDB.php';
// connecting to db
$db = new DB_CONNECT();

$query = "SELECT HISHDRTBLNO,ITMNAME,HISDTLQTY,HISTIME,HISDTLRESPON 
FROM HISTRNSDTL 
JOIN ADMIN ON HISDTLRESPON=ADMID 
JOIN HISTRNSHDR ON HISHDRID=HISDTLHDRID 
JOIN ITMGNL ON ITMID=HISDTLITMID
WHERE HISDTLSTAT = 1
ORDER BY HISTIME ASC"
;
	
	$result = mysql_query($query)  or die(mysql_error());
	
	  if (!empty($result)) {
		   // check for empty result
		   
			$result = mysql_fetch_array($result);
 
			$history = array();
			$history["tableNo"] = $result["HISHDRTBLNO"];
			$history["itemName"] = $result["ITMNAME"];
			$history["qty"] = $result["HISDTLQTY"];
			$history["time"] = $result["HISTIME"];	
			$history["officer"] = $result["HISDTLRESPON"];					
			
			// success
			$response["success"] = 1;
 
			// user node
			$response["history"] = array();
 
			array_push($response["history"], $history);
 
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