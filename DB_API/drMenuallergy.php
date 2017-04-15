<?php
include 'DB_API.php';
$db = new DB_API();



// json response array
$response = array("error" => FALSE);

    $data = $db->getDrMenyAllergier();
    echo json_encode($data, JSON_PRETTY_PRINT);


?>