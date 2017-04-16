<?php
include 'DB_API.php';
$db = new DB_API();


// json response array
$response = array("error" => FALSE);




if (isset($_POST['uuid']) && isset($_POST['currentPassword']) && isset($_POST['newPassword'])) {
 
    // receiving the post params
    $uuid = $_POST['uuid'];
    $currentPassword = $_POST['currentPassword'];
    $newPassword = $_POST['newPassword'];
    

    
    //checking if current password is correct
    $user = $db->getUserByUUIDAndPassword($uuid, $currentPassword);

    $uuid2 = $user["unique_id"];
    
    //user credentials is OK
    //change password to new password
    if ($user) {
        $changedUser = $db->changePassword($uuid2, $newPassword);
        
        if ($changedUser){
            $response["error"] = FALSE;
            $response["uid"] = $user["unique_id"];
            $response["user"]["email"] = $user["email"];
            $response["user"]["student"] = $user["student"];
            $response["user"]["coffee"] = $user["coffee"];
            echo json_encode($response);
            
        }else{
            // wrong password
            $response["error"] = TRUE;
            $response["error_msg"] = "Unknown error!";
            echo json_encode($response);
        }
    } else {
        // wrong password
        $response["error"] = TRUE;
        $response["error_msg"] = "Wrong password!";
        echo json_encode($response);
    }

    
} else {
    $response["error"] = TRUE;
    $response["error_msg"] = "Required parameters uuid, current password and new password is missing!";
    echo json_encode($response);
}
?>