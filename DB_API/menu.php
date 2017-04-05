<?php
include 'DB_API.php';
$db = new DB_API();

$menu = $db->getMenu();
echo json_encode($menu, JSON_PRETTY_PRINT);
?>