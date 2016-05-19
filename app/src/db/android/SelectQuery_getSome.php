<?php
$response = array();
require_once 'connectDB.php';
// connecting to db
$db = new DB_CONNECT();

if (isset($_POST["tableName"])){
	switch($_POST["tableName"]){
		case 'ADMIN':
		
			if (isset($_POST["userName"])||isset($_POST["userId"])){
					
				$param=null;
				$query = "SELECT * FROM ADMIN ";
				
				if (isset($_POST["userName"])){
				 $param = mysql_escape_string($_POST['userName']);	
				 $query = $query."WHERE USERNAME = '$param'";
				} 
				
				else if (isset($_POST["userId"])){
				 $param = mysql_escape_string($_POST['userId']);	
				 $query = $query."WHERE ADMID = '$param'";		 
				}	
				
				$result = mysql_query($query)  or die(mysql_error());
				
				  if (!empty($result)) {
					   // check for empty result
					   
					   
					if (mysql_num_rows($result) > 0){
						 $result = mysql_fetch_array($result);
			 
						$member = array();
						$member["userId"] = $result["ADMID"];
						$member["username"] = $result["USERNAME"];
						$member["pwd"] = $result["PWD"];
						$member["fname"] = $result["FNAME"];
						$member["lname"] = $result["LNAME"];           
						$member["position"] = $result["POSITION"];
						// success
						$response["success"] = 1;
			 
						// user node
						$response["member"] = array();
			 
						array_push($response["member"], $member);
			 
						// echoing JSON response
						echo json_encode($response);
					}
					else {
						// no product found
						$response["success"] = 0;
						$response["message"] = "Data No found1  Post<".$_POST["userName"]." >";
			 
						// echo no users JSON
						echo json_encode($response);
					}  	 
				  }
				  else {
					// no product found
					$response["success"] = 0;
					$response["message"] = "Data No found2 Post<".$_POST["userName"]." >";
			 
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
		
		break;
		
		case 'HISTRNSHDR':
		
		break;
		
		case 'HISTRNSDTL':
		
		break;
		
		case 'ITMGNL':
		
		break;
		
		case 'TYPE':
		
		break;
	}
	
	
	
	
}
?>