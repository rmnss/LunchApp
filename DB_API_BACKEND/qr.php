<!DOCTYPE html>
<html lang="no">
    
    <head>
        <title>Kantine App</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="style.css">
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link rel="icon" href="bilder/coffeecup.png">
        <link rel="stylesheet" type="text/css" href="style.css">
        <meta charset="utf-8" />
    </head>
    
    <body>
        <div class="container">
            
          
            
        <ul class="nav nav-tabs nav-justified ">
        <li class="nav nav-item">
        <a class="nav nav-link" href="default.php">Meny</a></li>
        <li class="nav nav nav-item">
        <a class="nav nav-link" href="todaysspecialList.php">Todays Special</a></li>
        <li class="nav nav-item">
        <a class="nav nav-link" href="allergier.php">Allergier</a></li>
        <li class="nav nav-item">
        <a class="nav nav-link"  href="bildeInput.php">Last opp bilde</a></li>
        <li class="nav nav-item">
        <a style = "color: #fff; background-color:#ff9d00;" class="nav nav-link active" href="qr.php">Endre QR/Passord</a></li>
            <li class="nav nav-item">
        <a class="nav nav-link" href="openingHoursList.php">Åpningstider</a></li>
        </ul>
            
              
            <h1>Endre QR strenger og passord til godkjenning av kjøp</h1>  
            <br>
            <br>
            
            
            <div class="form-group">
            <label for="usr"> Bytt pin-kode ved kjøp av kaffe:</label><br>
            <label for="usr"> Change pin-code when buying coffee card :</label>
            <input type="text" class="form-control" id="pw">
            <button name = "password" type="button" class="btn btn-primary btn-save-Password">Lagre</button>
            </div>
            
            <hr>
            
            <div class="form-group"> 
            <label for="usr">QR-String for ny QR kode ved salg av kaffekort:</label><br>
             <label for="usr">QR-String for new QR code when selling coffee card:</label>   
            <input type="text" class="form-control" id="coffeecard">
            <button name ="newSalesString" type="button" class="btn btn-primary btn-save-SellString">Largre</button>
            </div>
            
            <hr class ="divider">
            
            
            <div class="form-group">
            <label for="usr">QR-String for ny QR kode ved kjøp av kaffe:</label><br>
             <label for="usr">QR-String for new QR kode when buying one coffee:</label><br>
            <input type="text" class="form-control" id="buycoffee">
            <button name = "newBuyString" type="button" class="btn btn-primary btn-save-BuyString">Large</button>
            </div>
            
            
            
            
            <?php
            
            include 'footer.php'; 
            ?>

            
            
            
        </div>
    </body>
    <script src="editableTable.js"></script>
</html>  