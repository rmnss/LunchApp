<?php

include "config.php"; //config


    $dbConnection = mysqli_connect($mysql_hostname, $mysql_user, $mysql_password, $mysql_database) or die("SQL ERROR: ". mysql_error());
    $dbConnection->set_charset("utf8");





/*=========================================================

            Checking post and runs right FUNction
                        
=========================================================*/



if ($_POST['action'] == "getMenu"){
    getMenu($dbConnection);
    
}elseif ($_POST['action'] == "getToday"){
    getToday($dbConnection);
    
}elseif ($_POST['action'] == "getHours"){
    getHours($dbConnection);    
    
}elseif ($_POST['action'] == "getAllergies"){
    getAllergies($dbConnection);
    
    
}elseif ($_POST['action'] == "delRow"){
    delRow($dbConnection, $_POST['id']);   

    
    
/*-----------------------
    Today's special 
 -----------------------*/
 }elseif ($_POST['action'] == "delRowSpecial"){
  
    delRowSpecial($dbConnection, $_POST['id']);  
    
    }elseif ($_POST['action'] == "saveRow"){
  
    $idmenu = $_POST['idmenu'];
    $navn = $_POST['navn'];  
    $serveringstid = $_POST['serveringstid'];  
    $studentpris = $_POST['studentpris'];  
    $ansattpris = $_POST['ansattpris'];  
    $dag = $_POST['dag'];
    
    
    saveRow($dbConnection, $idmenu, $navn, $serveringstid, $studentpris, $ansattpris, $dag); 
    
    
   /*-----------------------
    Save opening hours
 -----------------------*/
    }elseif ($_POST['action'] == "saveRowHours"){
  
    $idHours = $_POST['idÅpningstider'];
    $hours = $_POST['openingHours'];  
    $day = $_POST['day'];  
   saveRowHours($dbConnection, $idHours, $hours, $day); 
    
    
    
    /*-----------------------
    Deleting Allergy
 -----------------------*/
    
    
    }elseif ($_POST['action'] == "delAllergy"){
  
    $allergiID = $_POST['idAlergier'];
    $drID = $_POST['idDrmeny'];  
  
    
    
    delAllergy($dbConnection, $allergiID, $drID); 
    
    
/*-----------------------
            updating picture
 -----------------------*/
    
     }elseif ($_POST['action'] == "addPicture"){
  
    $id = $_POST['drmeny_idDRmeny'];
    $pictureLink = $_POST['id'];  
  
    
    
    addPicture($dbConnection, $id, $pictureLink); 
    
    
    /*-----------------------
            Menu elements
    -----------------------*/
    }elseif ($_POST['action'] == "saveRowMenu"){
  
    $idmenu = $_POST['idmenu'];
    $kategori = $_POST['kategori'];  
    $merke = $_POST['merke'];  
    $type = $_POST['type'];  
    $studentPris = $_POST['studentPris'];  
    $ansattPris = $_POST['ansattPris'];
    
    
    saveRowMenu($dbConnection, $idmenu, $kategori, $merke, $type, $studentPris, $ansattPris); 
    
    
    /*---------------------------------------------------------------------
    Creating new row in DB -> for use of innerHTML in JS
    ---------------------------------------------------------------------*/
    }elseif ($_POST['action'] == "saveRowMenuCreateRow"){
  

    $kategori = $_POST['kategori'];  
    $merke = $_POST['merke'];  
    $type = $_POST['type'];  
    $studentPris = $_POST['studentPris'];  
    $ansattPris = $_POST['ansattPris'];
    
    
    saveRowMenuCreateRow($dbConnection, $kategori, $merke, $type, $studentPris, $ansattPris); 
    
    
    
/*---------------------------------------------------------------------
    Creating new row in db for allergies with relation "drMeny"
---------------------------------------------------------------------*/
    
    }elseif ($_POST['action'] == "addAllergies"){
  

    $idA = $_POST['allergier_idAlergier'];  
    $idD = $_POST['drmeny_idDRmeny'];  
    
    addAllergies($dbConnection, $idA, $idD); 
    
   


    
    
/*---------------------------------------------------------------------
    Change QR password 
---------------------------------------------------------------------*/
    
    }elseif ($_POST['action'] == "savePasswordQr"){
  

    $password = $_POST['password'];  
   
    savePasswordQr($dbConnection, $password); 
    
    
    
/*---------------------------------------------------------------------
    Change QR-sellString
---------------------------------------------------------------------*/
    
    }elseif ($_POST['action'] == "saveSellStringQr"){
  

    $sellString = $_POST['SellString'];  
   
    saveSellStringQr($dbConnection, $sellString); 
    
    
/*---------------------------------------------------------------------
    Change QR-sellString
---------------------------------------------------------------------*/
    
    }elseif ($_POST['action'] == "saveBuyStringQr"){
  

    $BuyString = $_POST['BuyString'];  
   
    saveBuyStringQr($dbConnection, $BuyString); 
        
    
    
   

}else{
    
    echo("Error: POST matchet ikke en funksjon");
    
}







/*=========================================================

                        Queries
                        
=========================================================*/

//Menu
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




//Todays special
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


//Opening hours
function getHours($dbConnection) {
    $sql = "SELECT idÅpningstider, openingHours, day FROM OpeningHours;";
    $result = mysqli_query($dbConnection, $sql);
    $data = array();
    while ($row = $result->fetch_array(MYSQLI_ASSOC)){
        $data[] = $row;
    }
    //Svar fra Datbasen echoes ut som json så ajax kan hente den
    echo json_encode($data, JSON_FORCE_OBJECT);
}



//Allergies 
function getAllergies($dbConnection) {
    $sql = "SELECT navn,allergi,dag,idDrmeny, allergier_idAlergier, drmeny_idDRmeny, idAlergier FROM DrMeny, Allergier_has_DrMeny, Allergier
WHERE idDrmeny = drmeny_idDRmeny AND allergier_idAlergier = idalergier
order by navn;";
    $result = mysqli_query($dbConnection, $sql);
    $data = array();
    while ($row = $result->fetch_array(MYSQLI_ASSOC)){
        $data[] = $row;
    }
    //Svar fra Datbasen echoes ut som json så ajax kan hente den
    echo json_encode($data, JSON_FORCE_OBJECT);
}


/*=========================================================

                        Delete
                        
=========================================================*/

    


function delRow($dbConnection, $id) {
    
    if ($stmt = mysqli_prepare($dbConnection, "DELETE FROM Menu WHERE idMenu = ?")) {
        //bind parameters for markers
        mysqli_stmt_bind_param($stmt, "s", $id);
        
        //execute query
        mysqli_stmt_execute($stmt); 

        
    }
}



function delRowSpecial($dbConnection, $id) {
    
    if ($stmt = mysqli_prepare($dbConnection, "DELETE FROM Menu WHERE idMenu = ?")) {
        //bind parameters for markers
        mysqli_stmt_bind_param($stmt, "s", $id);
        
        //execute query
        mysqli_stmt_execute($stmt); 

        
    }
}


function delAllergy($dbConnection, $allergiID, $drID) {
    
    if ($stmt = mysqli_prepare($dbConnection, "DELETE FROM Allergier_has_DrMeny WHERE allergier_idAlergier = ? AND drmeny_idDRmeny = ?")) {
        //bind parameters for markers
        mysqli_stmt_bind_param($stmt, "ss", $allergiID, $drID);
        
        //execute query
        mysqli_stmt_execute($stmt); 

        
    }
}




/*=========================================================

                        Update statements
                        
=========================================================*/
  
/*-----------------------
        QR password
    -----------------------*/
function savePasswordQr ($dbConnection, $password){
    if ($stmt = mysqli_prepare($dbConnection, "UPDATE Coffee SET pin = ? WHERE description = 'refill';")){
        
        //binder parameterne
        mysqli_stmt_bind_param($stmt,"s", $password);
        
        //kjører
        mysqli_stmt_execute($stmt);
    }
}




        

/*-----------------------
        QR sellString
    -----------------------*/
function saveSellStringQr($dbConnection, $sellString){
    if ($stmt = mysqli_prepare($dbConnection, "UPDATE Coffee SET qrString = ? WHERE description = 'refill';")){
        
        //binder parameterne
        mysqli_stmt_bind_param($stmt,"s", $sellString);
        
        //kjører
        mysqli_stmt_execute($stmt);
    }
}



/*-----------------------
        QR BuyString
    -----------------------*/
function saveBuyStringQr($dbConnection, $BuyString){
    if ($stmt = mysqli_prepare($dbConnection, "UPDATE Coffee SET qrString = ? WHERE description = 'buy';")){
        
        //binder parameterne
        mysqli_stmt_bind_param($stmt,"s", $BuyString);
        
        //kjører
        mysqli_stmt_execute($stmt);
    }
}



/*-----------------------
        Today's special
    -----------------------*/
function saveRow ($dbConnection, $idmenu, $navn, $serveringstid, $studentpris, $ansattpris, $dag){
    
 if ($stmt = mysqli_prepare($dbConnection, "UPDATE DrMeny SET navn= ?, serveringstid= ?, studentPris= ?, ansattPris=? WHERE idDRmeny= ?;")) {
        //bind parameters for markers
        mysqli_stmt_bind_param($stmt, "sssss", $navn, $serveringstid, $studentpris, $ansattpris, $idmenu);
        
        //execute query
        mysqli_stmt_execute($stmt); 

        
    }
}



/*-----------------------
       Saving opening hours
    -----------------------*/   
    

function saveRowHours($dbConnection, $idHours, $hours, $day){
    
 if ($stmt = mysqli_prepare($dbConnection, "UPDATE OpeningHours SET openingHours = ? WHERE idÅpningstider = ?;")) {
        //bind parameters for markers
        mysqli_stmt_bind_param($stmt, "ss", $hours, $idHours);
        
        //execute query
        mysqli_stmt_execute($stmt); 

        
    }
}


/*-----------------------
        Menu
    -----------------------*/
function saveRowMenu ($dbConnection, $idmenu, $kategori, $merke, $type, $studentPris, $ansattPris){
    
 if ($stmt = mysqli_prepare($dbConnection, "UPDATE Menu SET kategori= ?, merke= ?, type= ?, studentPris=?, ansattPris=? WHERE idMenu= ?;")) {
        //bind parameters for markers
        mysqli_stmt_bind_param($stmt, "ssssss", $kategori, $merke, $type, $studentPris, $ansattPris, $idmenu);
        
        //execute query
        mysqli_stmt_execute($stmt); 

        
    }
}



/*-----------------------
        Picture
-----------------------*/

function  addPicture($dbConnection, $id, $pictureLink){
    
 if ($stmt = mysqli_prepare($dbConnection, "UPDATE DrMeny SET picture = ? WHERE idDRmeny = ?;")) {
        //bind parameters for markers
        mysqli_stmt_bind_param($stmt, "ss", $pictureLink ,$id);
        
        //execute query
        mysqli_stmt_execute($stmt); 

        
    }
}



/*=========================================================

                        Insert statements
                        
=========================================================*/


/*--------------------------------------------------------------------------------------------
    TODO: Fikse elementer som ikke har id fra før av. (Sette ID fra $ID på TR??? med JS)
--------------------------------------------------------------------------------------------*/

function saveRowMenuCreateRow ($dbConnection, $kategori, $merke, $type, $studentPris, $ansattPris){
    
 if ($stmt = mysqli_prepare($dbConnection, "INSERT INTO Menu (merke, type, studentPris, ansattPris, kategori) values (?, ?, ?, ?, ?);")) {
        //bind parameters for markers
        mysqli_stmt_bind_param($stmt, "sssss", $merke, $type, $studentPris, $ansattPris, $kategori);
        
        //execute query
        mysqli_stmt_execute($stmt); 

        
    }
}



function addAllergies ($dbConnection, $idA, $idD){
    
 if ($stmt = mysqli_prepare($dbConnection, "INSERT INTO Allergier_has_DrMeny (allergier_idAlergier, drmeny_idDRmeny) values (?, ?);")) {
        //bind parameters for markers
        mysqli_stmt_bind_param($stmt, "ss", $idA, $idD);
        
        //execute query
        mysqli_stmt_execute($stmt); 

        
    }
}





    
//slutt







?>