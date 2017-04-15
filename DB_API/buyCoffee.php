<?php

include 'DB_API.php';
$db = new DB_API();




// json response array
$response = array("error" => FALSE);



if (isset($_POST['uuid']) && isset($_POST['qrString'])) {
    
    // receiving the post params
    $uuid = $_POST['uuid'];
    $qrString = $_POST['qrString'];
    
    
    $qr = $db->checkBuyQR($qrString);
    
    if ($qr["qrString"] == $qrString){
    
    
        $user = $db->checkCoffee($uuid);
    
        $coffee = $user["coffee"];

        if($coffee > 0){

            $newCoffee = $db->setCoffee($coffee-1, $uuid);

           // echo($newCoffee);
            
            
            //echo($buy["coffee"]);
            $response["error"] = FALSE;
            $response["coffee"] = $newCoffee;
            echo json_encode($response);

        }else {
            $response["error"] = TRUE;
            $response["error_msg"] = "You don't have any coffee left!";
            $response["coffee"] = 0;
            echo json_encode($response);
        }
    }else{
        $response["error"] = TRUE;
        $response["error_msg"] = "Not correct QR!";
        echo json_encode($response);
    }
} else {
    // required post params is missing
    $response["error"] = TRUE;
    $response["error_msg"] = "Required parameters uuid or qrString is missing!";
    echo json_encode($response);
}



?>