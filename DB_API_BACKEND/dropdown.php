<?php

include "config.php"; //config


    $dbConnection = mysqli_connect($mysql_hostname, $mysql_user, $mysql_password, $mysql_database) or die("SQL ERROR: ". mysql_error());
    $dbConnection->set_charset("utf8");




   $sql = "SELECT navn, idDRmeny FROM DrMeny;";
    $result = mysqli_query($dbConnection, $sql);
    $data = array();


echo("<h2>Har måltidet allergier, legg det til her:</h2>");

    echo '<select button class="btn  btn-md col-md-3 knapp" type="button" data-toggle="dropdown" name= "Dish" id="dish">';
    while ($row = $result->fetch_array(MYSQLI_ASSOC)){
    echo "<option id = '". $row['idDRmeny'] . "' value='" . $row['navn'] . "'>" . $row['navn'] . "</option>";
        
        
    }
echo "</select>";

 $sql = "SELECT  allergi, idAlergier FROM Allergier;";
    $result = mysqli_query($dbConnection, $sql);
    $data = array();


    echo '<select button class="btn knapp btn-md col-md-3"" type="button" data-toggle="dropdown" name= "Allergies" id = "allergies">';
    while ($row = $result->fetch_array(MYSQLI_ASSOC)){
         echo "<option id = '". $row['idAlergier'] . "'value='" . $row['allergi'] . "'>" . $row['allergi'] . "</option>";
        
    }

echo "</select>";


        
?>

