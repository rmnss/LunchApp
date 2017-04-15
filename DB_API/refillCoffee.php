<?php

include 'DB_API.php';
$db = new DB_API();




// json response array
$response = array("error" => FALSE);



if (isset($_POST['uuid']) && isset($_POST['qrString']) && isset($_POST['pin'])) {
    
    // receiving the post params
    $uuid = $_POST['uuid'];
    $qrString = $_POST['qrString'];
    $pin = $_POST['pin'];
    
    
    $qr = $db->checkRefillQR($qrString, $pin);
    
    if ($qr["qrString"] == $qrString && $qr["pin"] == $pin){
        //refill coffee
        
        $user = $db->checkCoffee($uuid);
    
        $coffee = $user["coffee"];

        $buy = $db->setCoffee($coffee+12, $uuid);

        $response["error"] = FALSE;
        $response["coffee"] = $coffee+12;
        echo json_encode($response);
        
        
    }else{
        $response["error"] = TRUE;
        $response["error_msg"] = "Not correct QR or PIN!";
        echo json_encode($response);
    }
    
    
    
    

} else {
    // required post params is missing
    $response["error"] = TRUE;
    $response["error_msg"] = "Required parameters uuid, qrstring and pin is missing!";
    echo json_encode($response);
}



?>