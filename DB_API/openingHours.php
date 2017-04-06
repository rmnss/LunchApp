<?php
include 'DB_API.php';
$db = new DB_API();

echo("hei");
$menu = $db->getOpeningHours();
echo json_encode($menu, JSON_PRETTY_PRINT);

?>