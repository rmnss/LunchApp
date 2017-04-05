<?php
include 'DB_API.php';
$db = new DB_API();

// json response array
//$response = array("error" => FALSE);

// get the user by email and password
$menu = $db->getDrMeny();

echo json_encode($menu, JSON_PRETTY_PRINT);

?>