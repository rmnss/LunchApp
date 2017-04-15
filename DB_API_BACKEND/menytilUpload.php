<?php

include "config.php"; //config


    $dbConnection = mysqli_connect($mysql_hostname, $mysql_user, $mysql_password, $mysql_database) or die("SQL ERROR: ". mysql_error());
    $dbConnection->set_charset("utf8");




   $sql = "SELECT navn, idDRmeny FROM DrMeny;";
    $result = mysqli_query($dbConnection, $sql);
    $data = array();



echo("<h3>Dish</h3>");
    echo '<select button class="btn dropdown-toggle knapp" type="button" data-toggle="dropdown" name= "Dish" id="dish">';
    while ($row = $result->fetch_array(MYSQLI_ASSOC)){
    echo "<option id = '". $row['idDRmeny'] . "' value='" . $row['navn'] . "'>" . $row['navn'] . "</option>";
        
        
    }
echo "</select>";





        
?>

