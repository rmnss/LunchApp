<?php

include 'DB_API.php';
$db = new DB_API();


// json response array
$response = array("error" => FALSE);


if (isset($_POST['uuid']) && isset($_POST['studentStatus'])) {
    
    // receiving the post params
    $uuid = $_POST['uuid'];
    $isStudent= $_POST['studentStatus'];
    
    
    $setStudent = $db->setStudentStatus($isStudent, $uuid);
    
    if ($setStudent == $isStudent){
        
        $response["error"] = FALSE;
        $response["studentStatus"] = $setStudent;
        echo json_encode($response);
      
    }else{
        $response["error"] = TRUE;
        $response["error_msg"] = "Something went wrong!";
        echo json_encode($response);
    }
    

} else {
    // required post params is missing
    $response["error"] = TRUE;
    $response["error_msg"] = "Required parameters uuid or studentStatus is missing!";
    echo json_encode($response);
}



?>