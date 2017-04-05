<?php
include 'DB_API.php';
$db = new DB_API();


// json response array
$response = array("error" => FALSE);




if (isset($_POST['email']) && isset($_POST['password']) && isset($_POST['student'])) {
 
    // receiving the post params
    $email = $_POST['email'];
    $password = $_POST['password'];
    $student = $_POST['student'];
    
    
    
    // check if user is already existed with the same email
    if ($db->isUserExisted($email)) {
        // user already existed
        $response["error"] = TRUE;
        $response["error_msg"] = "User already existed with " . $email;
        echo json_encode($response);
        
        
    } else {
        // create a new user
        $user = $db->storeUser($email, $password, $student);
        if ($user) {
            // user stored successfully
            $response["error"] = FALSE;
            $response["uid"] = $user["unique_id"];
            $response["user"]["email"] = $user["email"];
            $response["user"]["student"] = $user["student"];
            $response["user"]["coffee"] = $user["coffee"];
            echo json_encode($response);
        } else {
            // user failed to store
            $response["error"] = TRUE;
            $response["error_msg"] = "Unknown error occurred in registration!";
            echo json_encode($response);
        }
    }
} else {
    $response["error"] = TRUE;
    $response["error_msg"] = "Required parameters (email, password or student) is missing!";
    echo json_encode($response);
}
?>