<?php
include 'DB_API.php';
$db = new DB_API();


$menu = $db->getOpeninghours();
echo json_encode($menu, JSON_PRETTY_PRINT);

?>