<?php

class DB_API {

    
protected $dbConnection;

//************************//
//    Connection INFO     //
//************************//
function __construct($username){

    
    include 'config.php';
    

    
  
    $this->dbConnection = mysqli_connect($mysql_hostname, $mysql_user, $mysql_password, $mysql_database) or die("SQL ERROR: ". mysql_error());
    $this->dbConnection->set_charset("utf8");
}
    
    
    
    
    
                                        //************************//
                                        //  FUNctions n queries   //
                                        //************************//

    
    
    

    
    
//************************************************//
// Fetching PW,E-MAIL and fixing salt             //
//************************************************//    
    
    
    
function storeUser($email, $password, $student) {
    $uuid = uniqid('', true);
    $hash = $this->hashSSHA($password);
    
    $encrypted_password = $hash["encrypted"]; // encrypted password
    $salt = $hash["salt"]; // salt
    
    
    
    $sql = "INSERT INTO users(unique_id, email, encrypted_password, salt, student, coffee) VALUES(?, ?, ?, ?, ?, 0)";
    
    if ($stmt = mysqli_prepare($this->dbConnection, $sql)) {
        //bind parameters for markers
        mysqli_stmt_bind_param($stmt, "sssss", $uuid, $email, $encrypted_password, $salt, $student);

        //execute query
        $result = mysqli_stmt_execute($stmt);
        
        
        
        // check for successful store
        if ($result) {
            $stmt = $this->dbConnection->prepare("SELECT * FROM users WHERE email = ?");
            $stmt->bind_param("s", $email);
            $stmt->execute();
            $user = $stmt->get_result()->fetch_assoc();
            $stmt->close();
            return $user;
        } else {
            return false;
        }
    }
}
    

function getUserByEmailAndPassword($email, $password) {
    $sql = "SELECT * FROM users WHERE email = '" .$email ."'";
    $result = mysqli_query($this->dbConnection, $sql);
    
    //$user;
    while ($row = $result->fetch_array(MYSQLI_ASSOC)){
        $user = $row;
    }
    
    $salt = $user["salt"];
    $encrypted_password = $user['encrypted_password'];
    $hash = $this->checkhashSSHA($salt, $password);
    
    if ($hash == $encrypted_password){
        return $user;
    }else{
        return null;
    }
}
     
    
function isUserExisted($email) {
    $sql = "SELECT email from users WHERE email = '" .$email ."'";
    $result = mysqli_query($this->dbConnection, $sql);
    

    $data = array();
    
    
    while ($row = $result->fetch_array(MYSQLI_ASSOC)){
        $data[] = $row;
    }
    if (count($data) > 0){
        return true;
    }else {
        return false;
    }
}

    
function hashSSHA($password) {

    $salt = sha1(rand());
    $salt = substr($salt, 0, 10);
    $encrypted = base64_encode(sha1($password . $salt, true) . $salt);
    $hash = array("salt" => $salt, "encrypted" => $encrypted);
    return $hash;
}
    
    
public function checkhashSSHA($salt, $password) {

    $hash = base64_encode(sha1($password . $salt, true) . $salt);

    return $hash;
}
    
    
    
    
    
//************************************************//
// Fetching menu for both students and employees  //
//************************************************//
    
    
    
 //======Fetching Menu students======
function getMenu() {
    $sql = "SELECT idMenu, merke, type, studentPris, kategori FROM Menu";
    $result = mysqli_query($this->dbConnection, $sql);
    $data = array();
    while ($row = $result->fetch_array(MYSQLI_ASSOC)){
        $data[] = $row;
    }
    //Returnerer en array med spørring.
    return $data;
}
    
    
    
//======Fetching DrMeny students======
function getDrMenu() {
    $sql = "SELECT idDRmeny,navn, serveringstid,studentPris,dag FROM DrMeny";
    $result = mysqli_query($this->dbConnection, $sql);

    $data = array();
    while ($row = $result->fetch_array(MYSQLI_ASSOC)){
        $data[] = $row;
    }

    //Returnerer en array med spørring.
    return $data;
}
    
    
 //======Fetching Menu employees======
function getMenuEmployee() {
    $sql = "SELECT idDRmeny,navn, serveringstid,ansattPris,dag FROM DrMeny";
    $result = mysqli_query($this->dbConnection, $sql);
    $data = array();
    while ($row = $result->fetch_array(MYSQLI_ASSOC)){
        $data[] = $row;
    }
    //Returnerer en array med spørring.
    return $data;
}
    
    
    
//======Fetching DrMeny employees======
function getDrMenyEmployee() {
    $sql = "SELECT idMenu, merke, type, ansattPris, kategori FROM Menu";
    $result = mysqli_query($this->dbConnection, $sql);

    $data = array();
    while ($row = $result->fetch_array(MYSQLI_ASSOC)){
        $data[] = $row;
    }

    //Returnerer en array med spørring.
    return $data;
}    
    

    
    
    
    
//*****************************************************//
// Fetching allergies for both menu and daily menu     //
//*****************************************************//
    
    
    
//get allergies on menu
function getMenuAllergier($id) {
    
    $sql = "SELECT Allergier.navn FROM Allergier, Allergier_has_Menu where allergier_idAlergier = idAlergier AND menu_iDMenu = ?";
    
    if ($stmt = mysqli_prepare ($this->dbConnection, $sql)){    
    mysqli_stmt_bind_param($stmt, "s" ,$id);
    mysqli_stmt_execute($stmt);
    $result = $stmt->get_result();
    $data = array();
        
        while ($row = $result->fetch_array(MYSQLI_ASSOC)){
            $data[] = $row;   
        }
        //Returning array.
        return $data;
    }
    
}
    
    
    
//get allergies for dailymenu
function getDrMenyAllergier($id) {
    
    $sql = "SELECT Allergier.navn FROM Allergier, Allergier_has_DrMeny where allergier_idAlergier = idAlergier AND drmeny_idDRmeny = ?";
    
    if ($stmt = mysqli_prepare ($this->dbConnection, $sql)){    
    mysqli_stmt_bind_param($stmt, "s" ,$id);
    mysqli_stmt_execute($stmt);
    $result = $stmt->get_result();
    $data = array();
        
        while ($row = $result->fetch_array(MYSQLI_ASSOC)){
            $data[] = $row;   
        }
        //Returning array.
        return $data;
    } 

}
    




}
    
?>