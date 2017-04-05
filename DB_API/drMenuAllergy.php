<?php
include 'DB_API.php';
$db = new DB_API();




// json response array
$response = array("error" => FALSE);



if (isset($_POST['id'])) {

    // receiving the post params
    $id = $_POST['id'];
    
    
    $data = $db->getDrMenyAllergier($id);
    echo json_encode($data, JSON_PRETTY_PRINT);
    //echo json_encode($data, JSON_FORCE_OBJECT);
    
} else {
    // required post params is missing
    $response["error"] = TRUE;
    $response["error_msg"] = "No parameters received/posted!";
    //$response["kjell"] = "no va du smart";
    echo json_encode($response);
}



?>