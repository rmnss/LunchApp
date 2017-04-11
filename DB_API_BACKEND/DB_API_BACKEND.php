<?php

include "config.php"; //config


    $dbConnection = mysqli_connect($mysql_hostname, $mysql_user, $mysql_password, $mysql_database) or die("SQL ERROR: ". mysql_error());
    $dbConnection->set_charset("utf8");


getMenu($dbConnection);

//DB-API-Backend
function getMenu($dbConnection) {
    $sql = "SELECT idMenu, kategori,  merke, type, studentPris, ansattPris FROM Menu;";
    $result = mysqli_query($dbConnection, $sql);
    $data = array();
    while ($row = $result->fetch_array(MYSQLI_ASSOC)){
        $data[] = $row;
    }
    //Svar fra Datbasen echoes ut som json så ajax kan hente den
    echo json_encode($data, JSON_FORCE_OBJECT);
}

?>