<?php

include "config.php"; //config


    $dbConnection = mysqli_connect($mysql_hostname, $mysql_user, $mysql_password, $mysql_database) or die("SQL ERROR: ". mysql_error());
    $dbConnection->set_charset("utf8");


if ($_POST['action'] == "getMenu"){
    getMenu($dbConnection);
}
elseif ($_POST['action'] == "getToday"){
    getToday($dbConnection);
    
    
}elseif ($_POST['action'] == "delRow"){
  
    delRow($dbConnection, $_POST['id']);   

 

}else{
    echo("Error: POST matchet ikke en funksjon");
}



/*=========================================================

                        Spørringer
                        
=========================================================*/

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




function getToday($dbConnection) {
    $sql = "SELECT idDRmeny, navn, serveringstid, studentPris,ansattPris, dag FROM DrMeny;";
    $result = mysqli_query($dbConnection, $sql);
    $data = array();
    while ($row = $result->fetch_array(MYSQLI_ASSOC)){
        $data[] = $row;
    }
    //Svar fra Datbasen echoes ut som json så ajax kan hente den
    echo json_encode($data, JSON_FORCE_OBJECT);
}



/*=========================================================

                        Slette
                        
=========================================================*/

    


function delRow($dbConnection, $id) {
    
    if ($stmt = mysqli_prepare($dbConnection, "DELETE FROM Menu WHERE idMenu = ?")) {
        //bind parameters for markers
        mysqli_stmt_bind_param($stmt, "s", $id);
        
        //execute query
        mysqli_stmt_execute($stmt); 

        
    }
}







?>