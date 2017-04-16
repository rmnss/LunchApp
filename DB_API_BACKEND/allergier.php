<!DOCTYPE html>
<html lang="no">
    
    
    <head>
        <title>Kantine App</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
    
    
    
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="style.css">
        <link rel="icon" href="bilder/coffeecup.png">
        
        <meta charset="utf-8" />
        
    </head>
    
    <body>
        
        
        <div class="container">
            
            
        <ul class="nav nav-tabs nav-justified ">
        <li class="nav nav-item">
        <a class="nav nav-link" href="default.php">Meny</a></li>
        <li class="nav nav-item">
        <a class="nav-link" href="todaysspecialList.php">Todays Special</a></li>
        <li class="nav nav-item">
        <a style = "color: #fff; background-color:#ff9d00;" class="nav nav-link active"  href="allergier.php">Allergier</a></li>
            <li class="nav nav-item">
        <a class="nav nav-link"  href="bildeInput.php">Last opp bilde</a></li>
             <li class="nav nav-item">
        <a class="nav nav-link"  href="qr.php">Endre QR/Passord</a></li> 
             <li class="nav nav-item">
        <a class="nav nav-link" href="openingHoursList.php">Åpningstider</a></li>
    </ul>
            <h1>Allergier</h1>  
            
            
    

  <div id="table" class="table-editable">
      
    <table class="table table-hover table-responsive " id ="listAllergies">
        <thead>
      <tr>
        <th>Navn</th>
        <th>Allergi</th>
        <th>Dag</th>
      </tr>
        </thead>
        <tbody id="listeBody">
      
      
        </tbody>
    </table>
  
<div class = "form-group"> 
    
     <div class="col-md-push-3">

      
  <?php
include "dropdown.php";     
?>

<button type="button" class="btn btn-primary save-btn col-md-3">Lagre</button>
    
    
    </div>
</div>      

        
            
          <?php
            include 'footer.php'; 
            ?>
  
             </div>
        </div>
        
       
    </body>
        
<script src="editableTable.js"></script>
<script src="fillAllergies.JS"></script>
</html>  