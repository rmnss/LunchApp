<?php

include 'DB_API.php';
$db = new DB_API();




// json response array
$response = array("error" => FALSE);



if (isset($_POST['email']) && isset($_POST['password'])) {
    
    // receiving the post params
    $email = $_POST['email'];
    $password = $_POST['password'];
    
    
    $user = $db->getUserByEmailAndPassword($email, $password);
    
    
    if ($user != false) {
        $response["error"] = FALSE;
        $response["uid"] = $user["unique_id"];
        $response["user"]["email"] = $user["email"];
        $response["user"]["coffee"] = $user["coffee"];
        $response["user"]["student"] = $user["student"];
        echo json_encode($response);
    } else {
    // user is not found with the credentials
        $response["error"] = TRUE;
        $response["error_msg"] = "Login credentials are wrong. Please try again!";
        echo json_encode($response);
    }
    

} else {
    // required post params is missing
    $response["error"] = TRUE;
    $response["error_msg"] = "Required parameters email or password is missing!";
    echo json_encode($response);
}



?>