<?php
$response = array();
require_once 'connectDB.php';

// connecting to db
$db = new DB_CONNECT();

$result = mysql_query("INSERT INTO ADMIN (USERNAME,PWD,FNAME,LNAME,POSITION)
.VALUES ('test','1234','test','test','test')");

if ($result) {
        // successfully inserted into database
        $response["success"] = 1;
        $response["message"] = "Product successfully created.";
 
        // echoing JSON response
        echo json_encode($response);
    } 
else {
        // failed to insert row
        $response["success"] = 0;
        $response["message"] = "Oops! An error occurred.";
 
        // echoing JSON response
        echo json_encode($response);
    }

?>